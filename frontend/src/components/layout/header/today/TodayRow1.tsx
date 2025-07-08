import {Button} from "@/components/ui/button.tsx";
import {Filter} from "lucide-react";

export default function TodayRow1() {
  return (
      <div className='flex items-center justify-between h-12 px-4 border-b'>
        <button>
          <Button className='h-6 w-6 text-gray-700'/>
        </button>
        <span className='text-lg font-bold'>거래내역</span>
        <button>
          <Filter className='h-6 w-6 text-gray-700'/>
        </button>
      </div>

  )
}