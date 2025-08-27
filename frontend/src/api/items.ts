import {API} from '@/api/API.ts'


import type {ItemsApiResponse, SortType} from '../types';
import { mockApiResponse } from '../data/mockData';

interface FetchItemsParams {
    startDate?: string;
    endDate?: string;
    sort?: SortType;
    hideSold?: boolean;
}

export const fetchItems = async (params: FetchItemsParams): Promise<ItemsApiResponse> => {
    console.log('Fetching items with params:', params);
    await new Promise(resolve => setTimeout(resolve, 500));
    return mockApiResponse;
};


export async function getItems() {
  const response = await API.get('/v1/items')
  return response.data
}

export async function createItem(request: any) {
  const response = await API.post('/v1/items', {
    condition: request.condition,
    itemName: request.itemName,
    purchaseDate: request.purchaseDate,
    price: Number(request.price),
    saleRequest: request.saleRequest ? {
      saleDate: request.saleRequest.saleDate,
      price: request.saleRequest.price,
    } : null,
  })
  return response.data
}

export async function updateItem(itemId: number, request: any) {
  const response = await API.put(`/v1/items/${itemId}`, {
    condition: request.condition,
    itemName: request.itemName,
    purchaseDate: request.purchaseDate,
    price: Number(request.price),
    saleRequest: request.saleRequest ? {
      saleDate: request.saleRequest.saleDate,
      price: Number(request.saleRequest.price),
    } : null,
  })
  return response.data
}

// 단건 조회
export async function getItemById(itemId: number) {
  const res = await API.get(`/v1/items/${itemId}`, {withCredentials: true});
  return res.data;
}

// 삭제
export async function deleteItem(itemId: number) {
    const res = await API.delete(`/v1/items/${itemId}`, {withCredentials: true});
    return res.data;
}
