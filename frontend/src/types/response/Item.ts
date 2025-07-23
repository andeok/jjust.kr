export interface SaleResponse {
  saleId: number
  saleDate: string
  price: number
}

export interface Item {
  itemId: number
  itemName: string
  purchaseDate: string
  condition: string
  price: number
  saleResponse: SaleResponse | null
}

export interface ItemListResponse {
  purchasePrice: number
  salesPrice: number
  sumPrice: number
  purchaseCount: number
  salesCount: number
  items: {
    [date: string]: Item[];
  }
}
