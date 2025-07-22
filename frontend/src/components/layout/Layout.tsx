import Footer from './Footer.tsx'
import TodayHeader from './header/TodayHeader.tsx'
import {Outlet, useLocation} from "react-router-dom";
import StatsHeader from "@/components/layout/header/StatsHeader.tsx";

export default function MainLayout() {
    function HeaderByRoute() {
        const location = useLocation();
        if (location.pathname === "/") return <TodayHeader/>;
        if (location.pathname === "/stats") return <StatsHeader/>;
        if (location.pathname === "/more") return <TodayHeader/>;
        return null; // 기본 헤더가 필요하면 여기에 넣으세요
    }

    return (
        <>
            <HeaderByRoute/>
            <main className='overflow-y-auto pt-[176px] pb-16 h-screen'>
                <Outlet/>
            </main>
            <Footer/>
        </>
    )
}
