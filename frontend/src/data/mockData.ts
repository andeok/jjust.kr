// src/data/mockData.ts
// src/data/mockData.ts
import type {ItemsApiResponse} from '../types';

export const mockApiResponse: ItemsApiResponse = {
    purchasePrice: 4849000,
    salesPrice: 2950000,
    sumPrice: -1899000,
    purchaseCount: 4,
    salesCount: 2,
    items: [
        {
            itemId: 1,
            itemName: '에어팟 맥스',
            purchaseDate: '2025-07-15',
            condition: 'NEW',
            price: 720000,
            // 👇 [수정] SaleResponse 구조 변경
            saleResponse: { saleId: 101, saleDate: '2025-08-10', price: 650000 }
        },
        {
            itemId: 3,
            itemName: 'M3 Macbook Pro 14',
            purchaseDate: '2025-06-20',
            condition: 'NEW',
            price: 2190000,
            // 👇 [수정] SaleResponse 구조 변경
            saleResponse: { saleId: 102, saleDate: '2025-09-01', price: 2300000 }
        },
        {
            itemId: 2,
            itemName: '매직 키보드',
            purchaseDate: '2025-08-01',
            condition: 'USED',
            price: 449000,
            saleResponse: null
        },
        {
            itemId: 4,
            itemName: '아이폰 17 Pro',
            purchaseDate: '2025-09-25',
            condition: 'NEW',
            price: 1800000,
            saleResponse: null
        },
    ]
};