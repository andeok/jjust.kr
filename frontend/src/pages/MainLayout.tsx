// src/pages/MainLayout.tsx
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // 👈 [추가]
import type {Tab} from '../types';
import { ItemsPage } from './ItemsPage';
import { PlaceholderPage } from './PlaceholderPage';
import { Footer } from '../components/Footer';
import { FloatingActionButton } from '../components/FloatingActionButton';
import { checkLogin } from '../api/login'; // 👈 [추가]

export const MainLayout: React.FC = () => {
    const [activeTab, setActiveTab] = useState<Tab>('items');
    const navigate = useNavigate(); // 👈 [추가]

    const renderContent = () => {
        switch (activeTab) {
            case 'items': return <ItemsPage />;
            case 'stats': return <PlaceholderPage title="통계" />;
            case 'more': return <PlaceholderPage title="더보기" />;
            default: return <ItemsPage />;
        }
    };

    // 👇 [수정] 로그인 체크 로직을 FAB 클릭 핸들러에 다시 추가
    const handleFabClick = async () => {
        const isLoggedIn = await checkLogin();
        if (isLoggedIn) {
            alert('로그인 확인! 새 아이템 등록 로직을 진행합니다.');
        } else {
            navigate('/login'); // 로그인 페이지로 이동
        }
    };

    return (
        <>
            <main style={{ flex: 1, overflowY: 'auto' }}>
                {renderContent()}
            </main>
            <Footer activeTab={activeTab} onTabChange={setActiveTab} />
            {activeTab === 'items' && (
                <FloatingActionButton onClick={handleFabClick} />
            )}
        </>
    );
};