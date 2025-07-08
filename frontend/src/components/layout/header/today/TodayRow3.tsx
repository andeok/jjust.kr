import {ChevronLeft, ChevronRight} from "lucide-react";

export default function TodayRow3() {
  return (
      <div className='flex items-center justify-between h-8 px-4'>
        <button>
          <ChevronLeft className='h-5 w-5 text-gray-700'/>
          <ChevronLeft className='h-5 w-5 text-gray-700'/>
        </button>
        <button>
          <ChevronLeft className='h-5 w-5 text-gray-700'/>
          <ChevronLeft className='h-5 w-5 text-gray-700'/>
        </button>
        <button>
          <ChevronRight className='h-5 w-5 text-gray-700'/>
          <ChevronRight className='h-5 w-5 text-gray-700'/>
        </button>
      </div>

  )
}