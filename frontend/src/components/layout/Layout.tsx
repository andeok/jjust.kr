import Footer from './Footer.tsx'
import TodayHeader from './header/TodayHeader.tsx'
import {Outlet, useLocation} from 'react-router-dom'
import StatsHeader from '@/components/layout/header/StatsHeader.tsx'
import {useState} from 'react'
import Login from "@/pages/login/Login.tsx";

export default function MainLayout() {
  const [headerData, setHeaderData] = useState({
    purchase: 0,
    sales: 0,
    sum: 0,
  })

  function HeaderByRoute() {
    const location = useLocation()
    if (location.pathname === '/') return <TodayHeader {...headerData} />
    if (location.pathname === '/stats') return <StatsHeader/>
    if (location.pathname === '/more') return <TodayHeader {...headerData} />
    if (location.pathname === '/login') return <Login/>
    return null // 기본 헤더가 필요하면 여기에 넣으세요
  }

  return (
      <>
        <HeaderByRoute/>
        <main className='overflow-y-auto pt-[176px] pb-16 h-screen'>
          <Outlet context={{setHeaderData}}/>
        </main>
        <Footer/>
      </>
  )
}
