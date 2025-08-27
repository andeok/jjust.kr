// src/pages/ItemsPage.tsx
import React, { useState, useEffect, useRef } from 'react';
import { ItemCard } from '../components/ItemCard';
import { Filters } from '../components/Filters';
import { SummaryCard } from '../components/SummaryCard';
import type {ItemsApiResponse} from '../types';
import * as itemApi from '../api/items';


const itemsPageStyles = {
    listContainer: { padding: '16px', display: 'flex' as const, flexDirection: 'column' as const, gap: '12px' },
    filterContainer: { position: 'sticky' as const, top: 0, zIndex: 10, transition: 'transform 0.3s ease-out' },
};
const loadingErrorStyles = { padding: '40px', textAlign: 'center' as const, color: '#888' };

export const ItemsPage: React.FC = () => {
    // 👇 [수정] 상태를 ItemsApiResponse | null 로 변경
    const [data, setData] = useState<ItemsApiResponse | null>(null);
    const [filters, setFilters] = useState({});
    const [isLoading, setIsLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    const [isFilterVisible, setIsFilterVisible] = useState(true);
    const lastScrollY = useRef(0);

    const handleScroll = () => {
        const currentScrollY = window.scrollY;
        if (currentScrollY > lastScrollY.current && currentScrollY > 100) {
            setIsFilterVisible(false);
        } else {
            setIsFilterVisible(true);
        }
        lastScrollY.current = currentScrollY;
    };

    useEffect(() => {
        window.addEventListener('scroll', handleScroll, { passive: true });
        return () => window.removeEventListener('scroll', handleScroll);
    }, []);

    useEffect(() => {
        const loadItems = async () => {
            try {
                setIsLoading(true);
                setError(null);
                const fetchedData = await itemApi.fetchItems(filters); // API 응답 전체를 받음
                setData(fetchedData);
            } catch (err) {
                console.error(err);
                setError('데이터를 불러오는 데 실패했습니다.');
            } finally {
                setIsLoading(false);
            }
        };
        loadItems();
    }, [filters]);

    const renderContent = () => {
        if (isLoading) return <div style={loadingErrorStyles}>로딩 중...</div>;
        if (error) return <div style={loadingErrorStyles}>{error}</div>;
        if (!data) return <div style={loadingErrorStyles}>데이터가 없습니다.</div>;

        return (
            <>
                {/* 통계 카드 렌더링 */}
                <SummaryCard {...data} />

                {/* 아이템 목록 렌더링 */}
                {data.items.length > 0 ? (
                    data.items.map(item => <ItemCard key={item.itemId} item={item} />)
                ) : (
                    <div style={{...loadingErrorStyles, padding: '20px' }}>표시할 아이템이 없습니다.</div>
                )}
            </>
        );
    };

    return (
        <div>
            <div style={{...itemsPageStyles.filterContainer, transform: isFilterVisible ? 'translateY(0)' : 'translateY(-100%)'}}>
                <Filters onFilterChange={setFilters} />
            </div>
            <div style={itemsPageStyles.listContainer}>
                {renderContent()}
            </div>
        </div>
    );
};