import TodayRow1 from '@/components/layout/header/today/TodayRow1.tsx'
import TodayRow2 from '@/components/layout/header/today/TodayRow2.tsx'
import TodayRow3 from "@/components/layout/header/today/TodayRow3.tsx";

interface TodayHeaderProps {
  purchase: number
  sales: number
  sum: number
  purchaseCount: number
  salesCount: number
}

export default function TodayHeader({
                                      purchase,
                                      sales,
                                      sum,
                                      purchaseCount,
                                      salesCount,
                                      selectedDate,
                                      setSelectedDate
                                    }: TodayHeaderProps & {
  selectedDate: Date,
  setSelectedDate: (date: Date) => void
}) {


  // CalendarHeader에서 월이 바뀔 때 호출
  const handleMonthChange = (date: Date) => setSelectedDate(date);

  return (
      <header
          className='fixed top-0 left-1/2 -translate-x-1/2 z-20 w-full max-w-[430px] bg-white border-b'>
        <TodayRow1/>
        <TodayRow2 purchasePrice={purchase} salesPrice={sales} sumPrice={sum}
                   purchaseCount={purchaseCount} saleCount={salesCount}/>
        <TodayRow3 initialMonth={selectedDate} onMonthChange={handleMonthChange}/>
      </header>
  )
}
