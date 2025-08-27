// src/components/Footer.tsx
import React from 'react';
import type {Tab} from '../types';

const footerStyles = {
    footer: {
        height: '60px',
        borderTop: '1px solid #ddd',
        backgroundColor: 'white',
        display: 'flex',
        justifyContent: 'space-around',
        alignItems: 'center',
        flexShrink: 0,
    },
    tabButton: {
        background: 'none',
        border: 'none',
        color: '#888',
        cursor: 'pointer',
        fontSize: '14px',
        padding: '10px',
    },
    activeTab: {
        color: '#1a73e8',
        fontWeight: 'bold' as const,
    }
};

interface FooterProps {
    activeTab: Tab;
    onTabChange: (tab: Tab) => void;
}

export const Footer: React.FC<FooterProps> = ({ activeTab, onTabChange }) => {
    return (
        <footer style={footerStyles.footer}>
            <button
                style={{ ...footerStyles.tabButton, ...(activeTab === 'items' && footerStyles.activeTab) }}
                onClick={() => onTabChange('items')}
            >아이템</button>
            <button
                style={{ ...footerStyles.tabButton, ...(activeTab === 'stats' && footerStyles.activeTab) }}
                onClick={() => onTabChange('stats')}
            >통계</button>
            <button
                style={{ ...footerStyles.tabButton, ...(activeTab === 'more' && footerStyles.activeTab) }}
                onClick={() => onTabChange('more')}
            >더보기</button>
        </footer>
    );
};