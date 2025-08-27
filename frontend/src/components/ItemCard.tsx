// src/components/ItemCard.tsx
import React from 'react';
import type {ItemDetail} from '../types';
import { ImagePlaceholder } from './ImagePlaceholder';

const cardStyles = {
    card: { border: '1px solid #e0e0e0', borderRadius: '12px', padding: '16px', backgroundColor: 'white', display: 'flex' as const, gap: '16px', position: 'relative' as const, overflow: 'hidden', },
    imageWrapper: { width: '80px', height: '80px', flexShrink: 0 },
    image: { width: '100%', height: '100%', objectFit: 'cover' as const, borderRadius: '8px' },
    content: { display: 'flex' as const, flexDirection: 'column' as const, justifyContent: 'center', flexGrow: 1 },
    name: { fontSize: '16px', fontWeight: 'bold' as const, margin: '0 0 8px 0' },
    price: { fontSize: '15px', color: '#333', margin: 0 },
    date: { fontSize: '13px', color: '#888', margin: '8px 0 0 0' },
    statusBadge: { position: 'absolute' as const, top: '12px', right: '12px', backgroundColor: '#555', color: 'white', padding: '4px 8px', borderRadius: '16px', fontSize: '11px', fontWeight: '500' as const },
    saleInfoWrapper: { display: 'flex' as const, flexDirection: 'column' as const, gap: '4px' },
    priceFlow: { fontSize: '14px', color: '#666', margin: 0, },
    profit: { fontSize: '15px', fontWeight: 'bold' as const, margin: 0 },
};

interface ItemCardProps {
    item: ItemDetail;
}

export const ItemCard: React.FC<ItemCardProps> = ({ item }) => {
    const formatPrice = (price: number) => new Intl.NumberFormat('ko-KR').format(price);

    // 👇 [수정] saleResponse.salePrice -> saleResponse.price
    const profit = item.saleResponse ? item.saleResponse.price - item.price : 0;
    const profitColor = profit > 0 ? '#1a73e8' : (profit < 0 ? '#d93025' : '#333');

    return (
        <div style={cardStyles.card}>
            <div style={cardStyles.imageWrapper}>
                <ImagePlaceholder />
            </div>
            <div style={cardStyles.content}>
                <h3 style={cardStyles.name}>{item.itemName}</h3>
                {item.saleResponse ? (
                    <div style={cardStyles.saleInfoWrapper}>
                        <p style={cardStyles.priceFlow}> {formatPrice(item.price)}원 → <strong>{formatPrice(item.saleResponse.price)}원</strong> </p>
                        <p style={{ ...cardStyles.profit, color: profitColor }}> {profit >= 0 ? `+${formatPrice(profit)}` : formatPrice(profit)}원 </p>
                    </div>
                ) : (
                    <p style={cardStyles.price}>{formatPrice(item.price)}원</p>
                )}
                <p style={cardStyles.date}>{item.purchaseDate}</p>
            </div>
            {item.saleResponse && <div style={cardStyles.statusBadge}>판매완료</div>}
        </div>
    );
};