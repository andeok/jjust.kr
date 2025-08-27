// src/pages/PlaceholderPage.tsx
import React from 'react';

interface PlaceholderPageProps {
    title: string;
}

export const PlaceholderPage: React.FC<PlaceholderPageProps> = ({ title }) => (
    <div style={{ padding: '20px', textAlign: 'center' }}>
        <h1>{title}</h1>
        <p>이 페이지는 현재 개발 중입니다.</p>
    </div>
);