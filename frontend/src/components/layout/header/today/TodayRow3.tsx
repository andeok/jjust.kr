import {ChevronLeft, ChevronRight} from 'lucide-react'
import {useState} from "react";

// 날짜 포맷 유틸
function formatYearMonth(date: Date) {
  return `${date.getFullYear()}년 ${String(date.getMonth() + 1).padStart(2, "0")}월`;
}

export default function TodayRow3({
                                    initialMonth, // 초기값: Date 객체
                                    onMonthChange, // 월이 바뀔 때 호출 (선택)
                                  }: {
  initialMonth?: Date;
  onMonthChange?: (date: Date) => void;
}) {
  const [currentDate, setCurrentDate] = useState(initialMonth ?? new Date());

  const handlePrevMonth = () => {
    const prev = new Date(currentDate);
    prev.setMonth(prev.getMonth() - 1);
    setCurrentDate(prev);
    onMonthChange?.(prev);
  };

  // 다음달로
  const handleNextMonth = () => {
    const next = new Date(currentDate);
    next.setMonth(next.getMonth() + 1);
    setCurrentDate(next);
    onMonthChange?.(next);
  };

  return (
      <div className='flex items-center justify-between h-12 px-4 border-b'>
        <button onClick={handlePrevMonth}>
          <ChevronLeft className='h-6 w-6 text-gray-700'/>
        </button>
        <span className='text-lg font-bold'>
          {formatYearMonth(currentDate)}
        </span>
        <button onClick={handleNextMonth}>
          <ChevronRight className='h-6 w-6 text-gray-700'/>
        </button>
      </div>
  )
}
