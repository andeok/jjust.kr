import Footer from './Footer.tsx'
import TodayHeader from './header/TodayHeader.tsx'
import {Outlet, useLocation} from 'react-router-dom'
import StatsHeader from '@/components/layout/header/StatsHeader.tsx'
import {useState} from 'react'
import Login from "@/pages/login/Login.tsx";
import MoreHeader from "@/components/layout/header/MoreHeader.tsx";

export default function MainLayout() {
  const [headerData, setHeaderData] = useState({
    purchase: 0,
    sales: 0,
    sum: 0,
    purchaseCount: 0,
    salesCount: 0,
  })

  const [selectedDate, setSelectedDate] = useState(new Date());

  function HeaderByRoute() {
    const location = useLocation()
    if (location.pathname === '/') return <TodayHeader {...headerData}
                                                       selectedDate={selectedDate}
                                                       setSelectedDate={setSelectedDate}/>
    if (location.pathname === '/stats') return <StatsHeader/>
    if (location.pathname === '/more') return <MoreHeader/>
    if (location.pathname === '/login') return <Login/>
    return null // 기본 헤더가 필요하면 여기에 넣으세요
  }

  return (
      <>
        <HeaderByRoute/>
        <main className='overflow-y-auto pt-[176px] pb-16 h-screen'>
          <Outlet context={{setHeaderData, selectedDate, setSelectedDate}}/>
        </main>
        <Footer/>
      </>
  )
}
