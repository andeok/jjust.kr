// src/components/SummaryCard.tsx
import React from 'react';
import type {ItemsApiResponse} from '../types';
// ... 스타일 및 컴포넌트 코드는 이전 답변과 동일 ...
const summaryStyles = {
    card: { padding: '16px', backgroundColor: 'white', borderRadius: '12px', boxShadow: '0 4px 6px rgba(0, 0, 0, 0.05)', marginBottom: '16px' },
    title: { margin: '0 0 12px 0', fontSize: '18px', fontWeight: 'bold' },
    grid: { display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '12px' },
    item: { display: 'flex', flexDirection: 'column' as const },
    label: { fontSize: '14px', color: '#888', marginBottom: '4px' },
    value: { fontSize: '16px', fontWeight: '600' },
};
type SummaryCardProps = Omit<ItemsApiResponse, 'items'>;
const formatPrice = (price: number) => new Intl.NumberFormat('ko-KR').format(price);
export const SummaryCard: React.FC<SummaryCardProps> = ({ purchasePrice, salesPrice, sumPrice, purchaseCount, salesCount, }) => {
    const sumPriceColor = sumPrice > 0 ? '#1a73e8' : (sumPrice < 0 ? '#d93025' : '#333');

    return (
        <div style={summaryStyles.card}>
            <h3 style={summaryStyles.title}>요약</h3>
            <div style={summaryStyles.grid}>
                <div style={summaryStyles.item}>
                    <span style={summaryStyles.label}>총 구매</span>
                    <span style={summaryStyles.value}>{formatPrice(purchasePrice)}원 ({purchaseCount}개)</span>
                </div>
                <div style={summaryStyles.item}>
                    <span style={summaryStyles.label}>총 판매</span>
                    <span style={summaryStyles.value}>{formatPrice(salesPrice)}원 ({salesCount}개)</span>
                </div>
                <div style={summaryStyles.item}>
                    <span style={summaryStyles.label}>총 손익</span>
                    <span style={{ ...summaryStyles.value, color: sumPriceColor }}>
            {sumPrice >= 0 ? `+${formatPrice(sumPrice)}` : formatPrice(sumPrice)}원
          </span>
                </div>
            </div>
        </div>
    );

};