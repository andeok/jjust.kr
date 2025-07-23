import {Filter} from 'lucide-react'
import {Button} from '@/components/ui/button.tsx'

export default function MoreHeader() {
  return (
      <header
          className='fixed top-0 left-1/2 -translate-x-1/2 z-20 w-full max-w-[430px] bg-white border-b'>
        {/* 1줄: 돋보기 - 거래내역 - 필터 */}
        <div className='flex items-center justify-between h-12 px-4 border-b'>
          <button>
            <Button className='h-6 w-6 text-gray-700'/>
          </button>
          <span className='text-lg font-bold'>더보기</span>
          <button>
            <Filter className='h-6 w-6 text-gray-700'/>
          </button>
        </div>
        {/* 2줄: ← - 2025년 07월 - → */}
        {/*<div className='flex items-center justify-between h-8 px-4'>*/}
        {/*  <button>*/}
        {/*    <ChevronLeft className='h-5 w-5 text-gray-700' />*/}
        {/*  </button>*/}
        {/*  <span className='text-base font-medium'>2025년 07월</span>*/}
        {/*  <button>*/}
        {/*    <ChevronRight className='h-5 w-5 text-gray-700' />*/}
        {/*  </button>*/}
        {/*</div>*/}
        {/*<div className='flex items-center justify-between h-8 px-4'>*/}
        {/*  <button>*/}
        {/*    <ChevronLeft className='h-5 w-5 text-gray-700' />*/}
        {/*    <ChevronLeft className='h-5 w-5 text-gray-700' />*/}
        {/*  </button>*/}
        {/*  <button>*/}
        {/*    <ChevronLeft className='h-5 w-5 text-gray-700' />*/}
        {/*    <ChevronLeft className='h-5 w-5 text-gray-700' />*/}
        {/*  </button>*/}
        {/*  <button>*/}
        {/*    <ChevronRight className='h-5 w-5 text-gray-700' />*/}
        {/*    <ChevronRight className='h-5 w-5 text-gray-700' />*/}
        {/*  </button>*/}
        {/*</div>*/}
      </header>
  )
}
