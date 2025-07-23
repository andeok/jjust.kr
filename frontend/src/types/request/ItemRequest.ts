// 아이템 등록 요청 타입
export type ItemCondition = "NEW" | "USED"

export interface SaleRequest {
  saleDate: string; // yyyy-MM-dd
  price: number;
}

export interface ItemRegisterRequest {
  itemName: string;
  purchaseDate: string; // yyyy-MM-dd
  condition: ItemCondition;
  price: number;
  saleRequest: SaleRequest | null;
}

// 아이템 등록 응답 타입(예시)
export interface ItemRegisterResponse {
  itemId: number;
  message: string;
}