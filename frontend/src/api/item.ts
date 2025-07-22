import { API } from '@/api/API.ts'

export async function getItems(searchDate: string) {
  const response = await API.get('/v1/items', {
    params: {
      searchDate,
    },
  })
  return response.data
}
