import {ChevronLeft, ChevronRight} from "lucide-react";

export default function TodayRow2() {
  return (
      <div className='flex items-center justify-between h-8 px-4'>
        <button>
          <ChevronLeft className='h-5 w-5 text-gray-700' />
        </button>
        <span className='text-base font-medium'>2025년 07월</span>
        <button>
          <ChevronRight className='h-5 w-5 text-gray-700' />
        </button>
      </div>

  )
}