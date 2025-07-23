import {Button} from '@/components/ui/button.tsx'
import {BarChart3, CalendarDays, MoreHorizontal} from 'lucide-react'
import {cn} from '@/lib/utils.ts'
import {Link} from 'react-router-dom'


const today = new Date()

function formatDate(date: Date): string {
  const year = date.getFullYear();
  const month = date.getMonth() + 1; // 월은 0부터 시작하므로 +1
  const day = date.getDate();
  return `${year}. ${month}. ${day}.`;
}

const menu = [
  {label: formatDate(today), icon: <CalendarDays className='h-5 w-5'/>, key: 'today', path: '/'},
  {label: '통계(개발중)', icon: <BarChart3 className='h-5 w-5'/>, key: 'stats', path: '/stats'},
  {label: '더보기(개발중)', icon: <MoreHorizontal className='h-5 w-5'/>, key: 'more', path: '/more'},
]

export default function Footer({active = 'today'}: { active?: string }) {
  return (
      <footer
          className='w-full fixed bottom-0 left-1/2 -translate-x-1/2 z-10 bg-white border-t flex justify-around items-center h-16 max-w-[430px]'>
        {menu.map((item) => (
            <Link to={item.path} className='flex-1'>
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
            </Link>
        ))}
      </footer>
  )
}
