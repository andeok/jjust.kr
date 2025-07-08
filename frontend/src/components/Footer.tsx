import { Button } from '@/components/ui/button'
import { CalendarDays, BarChart3, MoreHorizontal } from 'lucide-react'
import { cn } from '@/lib/utils'

const menu = [
  { label: '오늘 날짜', icon: <CalendarDays className='h-5 w-5' />, key: 'today' },
  { label: '통계', icon: <BarChart3 className='h-5 w-5' />, key: 'stats' },
  { label: '더보기', icon: <MoreHorizontal className='h-5 w-5' />, key: 'more' },
]

export default function Footer({ active = 'today' }: { active?: string }) {
  return (
    <footer className='w-full fixed bottom-0 left-1/2 -translate-x-1/2 z-10 bg-white border-t flex justify-around items-center h-16 max-w-[430px]'>
      {menu.map((item) => (
        <Button
          key={item.key}
          variant='ghost'
          className={cn(
            'flex flex-col items-center justify-center text-xs font-medium gap-1 rounded-none h-full w-full',
            active === item.key ? 'text-primary' : 'text-muted-foreground',
          )}
        >
          {item.icon}
          <span>{item.label}</span>
        </Button>
      ))}
    </footer>
  )
}
