// src/components/Filters.tsx
import React, { useState, useEffect } from 'react';
import type {SortType} from '../types';

const filterStyles = {
    container: { padding: '16px', backgroundColor: 'white', borderBottom: '1px solid #f0f0f0', transition: 'all 0.3s ease-in-out', zIndex: 10, position: 'sticky' as const, top: 0, },
    row: { display: 'flex' as const, gap: '8px', marginBottom: '12px', alignItems: 'center' as const, },
    dateInput: { flex: 1, padding: '8px', border: '1px solid #ccc', borderRadius: '4px', fontSize: '14px' },
    select: { flex: 2, padding: '8px', border: '1px solid #ccc', borderRadius: '4px', fontSize: '14px' },
    checkboxWrapper: { display: 'flex' as const, alignItems: 'center' as const, fontSize: '14px' },
};

interface FilterProps {
    onFilterChange: (filters: any) => void;
}

export const Filters: React.FC<FilterProps> = ({ onFilterChange }) => {
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [sort, setSort] = useState<SortType>('최신순');
    const [hideSold, setHideSold] = useState(false);

    useEffect(() => {
        onFilterChange({ startDate, endDate, sort, hideSold });
    }, [startDate, endDate, sort, hideSold, onFilterChange]);

    return (
        <div style={filterStyles.container}>
            <div style={filterStyles.row}>
                <input type="date" style={filterStyles.dateInput} value={startDate} onChange={e => setStartDate(e.target.value)} />
                <span>~</span>
                <input type="date" style={filterStyles.dateInput} value={endDate} onChange={e => setEndDate(e.target.value)} />
            </div>
            <div style={filterStyles.row}>
                <select style={filterStyles.select} value={sort} onChange={e => setSort(e.target.value as SortType)}>
                    <option value="최신순">최신순</option>
                    <option value="오래된순">오래된순</option>
                    <option value="고가순">고가순</option>
                </select>
                <div style={filterStyles.checkboxWrapper}>
                    <input type="checkbox" id="hide-sold" checked={hideSold} onChange={e => setHideSold(e.target.checked)} />
                    <label htmlFor="hide-sold">판매완료 안보기</label>
                </div>
            </div>
        </div>
    );
};