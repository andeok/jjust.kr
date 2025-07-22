import TodayRow1 from '@/components/layout/header/today/TodayRow1.tsx'
import TodayRow2 from '@/components/layout/header/today/TodayRow2.tsx'

interface TodayHeaderProps {
  purchase: number
  sales: number
  sum: number
}

export default function TodayHeader({ purchase, sales, sum }: TodayHeaderProps) {
  return (
    <header className='fixed top-0 left-1/2 -translate-x-1/2 z-20 w-full max-w-[430px] bg-white border-b'>
      <TodayRow1 />
      <TodayRow2 purchasePrice={purchase} salesPrice={sales} sumPrice={sum} />
    </header>
  )
}
