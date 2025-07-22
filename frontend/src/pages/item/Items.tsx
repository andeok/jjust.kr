import { useEffect, useState } from 'react'
import { ItemCard } from '@/components/ItemCard'
import { getItems } from '@/api/item'
import type { ItemListResponse } from '@/types/response/Item'
import { useOutletContext } from 'react-router-dom'

export default function ItemListPage() {
  const [data, setData] = useState<ItemListResponse | null>(null)
  const { setHeaderData } = useOutletContext<{ setHeaderData: (data: any) => void }>()

  useEffect(() => {
    getItems('2025-05-01').then((result) => {
      setData(result)
      setHeaderData({
        purchase: result.purchasePrice,
        sales: result.salesPrice,
        sum: result.sumPrice,
      })
    })
  }, [])

  if (!data) return <div>로딩 중...</div>

  return (
    <div className='p-4'>
      <div className='flex flex-col gap-4'>
        {/* 카드가 한 줄에 하나씩! */}
        {data.items.map((item) => (
          <ItemCard key={item.itemId} item={item} />
        ))}
      </div>
    </div>
  )
}
