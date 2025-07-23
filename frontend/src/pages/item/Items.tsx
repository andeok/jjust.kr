import {useEffect, useState} from 'react'
import {ItemCard} from '@/components/ItemCard'
import {getItems} from '@/api/item'
import type {ItemListResponse} from '@/types/response/Item'
import {useOutletContext} from 'react-router-dom'

export default function ItemListPage() {
  const [data, setData] = useState<ItemListResponse | null>(null)
  const {setHeaderData, selectedDate} = useOutletContext<{
    setHeaderData: (data: any) => void
    selectedDate: Date
    setSelectedDate: (date: Date) => void
  }>()

  useEffect(() => {
    const year = selectedDate.getFullYear()
    const month = String(selectedDate.getMonth() + 1).padStart(2, '0')
    const dateString = `${year}-${month}-01`
    getItems(dateString).then((result) => {
      setData(result)
      setHeaderData({
        purchase: result.purchasePrice,
        sales: result.salesPrice,
        sum: result.sumPrice,
        purchaseCount: result.purchaseCount,
        salesCount: result.salesCount
      })
    })
  }, [selectedDate])

  if (!data) return <div>로딩 중...</div>


  return (
      <div className='p-4'>
        {Object.keys(data.items).length === 0 ? (
            <div>등록 된 아이템이 없습니다</div>
        ) : (
            <div className='flex flex-col gap-6'>
              {Object.entries(data.items).map(([date, items]) => (
                  <section key={date}>
                    <div className="font-bold text-lg mb-2">{date}</div>
                    <div className="flex flex-col gap-4">
                      {items.map(item => (
                          <ItemCard key={item.itemId} item={item}/>
                      ))}
                    </div>
                  </section>
              ))}
            </div>
        )}
      </div>
  )

}
