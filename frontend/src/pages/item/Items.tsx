import {useEffect, useState} from 'react'
import {ItemCard} from '@/components/ItemCard'
import {getItems} from '@/api/item'
import type {Item, ItemListResponse} from '@/types/response/Item'
import {useNavigate, useOutletContext} from 'react-router-dom'
import {API} from "@/api/API.ts";
import {login} from "@/api/login.ts";

export async function checkLogin(): Promise<boolean> {
    try {
        await API.get("/v1/members/me");
        return true // 401이면 false
    } catch {
        return false;
    }
}


export default function ItemListPage() {
    const [data, setData] = useState<ItemListResponse | null>(null)
    const {setHeaderData, selectedDate} = useOutletContext<{
        setHeaderData: (data: any) => void
        selectedDate: Date
        setSelectedDate: (date: Date) => void
    }>()
    const [isLoggedIn, setIsLoggedIn] = useState<boolean | null>(null);
    const [error, setError] = useState<string | null>(null);

    const navigate = useNavigate();

    // 로그인 여부 체크 (최초 1회)
    useEffect(() => {
        checkLogin().then(setIsLoggedIn);
    }, []);

    function handleItemClick(item: Item) {
        navigate(`/item/${item.itemId}`);
    }

    // + 버튼 클릭 시 로그인 체크
    const handlePlusClick = async () => {
        if (isLoggedIn === null) {
            const result = await checkLogin();
            if (!result) {
                navigate("/login");
                return;
            }
            navigate("/item/register");
            return;
        }

        if (!isLoggedIn) {
            // navigate("/login"); // TODO : 우선 고정 아이디로 로그인 처리...
            const email = 'test1@naver.com'
            const password = '1234'
            await login({email, password})
            navigate("/item/register");
            return;
        }
        navigate("/item/register");
    };


    // 아이템 목록 가져오기
    useEffect(() => {
        setError(null);
        setData(null); // 로딩 중 표시
        const year = selectedDate.getFullYear()
        const month = String(selectedDate.getMonth() + 1).padStart(2, '0')
        const dateString = `${year}-${month}-01`
        getItems(dateString).then((result) => {
            setData(result)
            setHeaderData({
                purchase: result.purchasePrice,
                sales: result.salesPrice,
                sum: result.sumPrice,
                purchaseCount: result.purchaseCount,
                salesCount: result.salesCount
            })
        })
            .catch((err) => {
                if (err?.response?.status === 401 || err?.status === 401 || err?.statusCode === 401) {
                    setError("로그인이 필요합니다. +버튼을 누르면 로그인할 수 있습니다.");
                } else {
                    setError("아이템 목록을 불러오지 못했습니다.");
                }
            })
    }, [selectedDate, setHeaderData]);

    return (
        <div className='p-4 relative'>
            {/* 에러 메시지 */}
            {error ? (
                <div className="text-center text-red-500 py-8">{error}</div>
            ) : !data ? (
                <div>로딩 중...</div>
            ) : Object.keys(data.items).length === 0 ? (
                <div>등록 된 아이템이 없습니다</div>
            ) : (
                <div className='flex flex-col gap-6'>
                    {Object.entries(data.items).map(([date, items]) => (
                        <section key={date}>
                            <div className="font-bold text-lg mb-2">{date}</div>
                            <div className="flex flex-col gap-4">
                                {items.map(item => (
                                    <ItemCard key={item.itemId} item={item} onClick={handleItemClick}/>
                                ))}
                            </div>
                        </section>
                    ))}
                </div>
            )}

            {/* --------- 우측 하단 + 버튼 --------- */}
            <button
                className="fixed bottom-24 z-30 bg-blue-600 text-white rounded-full shadow-lg w-16 h-16 flex items-center justify-center text-3xl hover:bg-blue-700 transition"
                style={{
                    right: 'max(1rem, calc(50vw - 215px))', // 최소 1rem, 최대 컨테이너 우측
                }}
                onClick={handlePlusClick}
                aria-label="아이템 등록"
            >
                +
            </button>
            {/* ----------------------------------- */}
        </div>
    )
}