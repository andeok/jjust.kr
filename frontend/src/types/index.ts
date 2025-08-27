// src/types/index.ts

export type Condition = 'NEW' | 'USED';

// 👇 [수정] SaleResponse 인터페이스를 명확하게 정의
export interface SaleResponse {
    saleId: number;
    saleDate: string;
    price: number;
}

export interface ItemDetail {
    itemId: number;
    itemName: string;
    purchaseDate: string;
    condition: Condition;
    price: number;
    saleResponse: SaleResponse | null;
}

export interface ItemsApiResponse {
    purchasePrice: number;
    salesPrice: number;
    sumPrice: number;
    purchaseCount: number;
    salesCount: number;
    items: ItemDetail[];
}

export type SortType = '최신순' | '오래된순' | '고가순';
export type Tab = 'items' | 'stats' | 'more';