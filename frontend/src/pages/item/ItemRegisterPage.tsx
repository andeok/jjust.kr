import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";
import {
    Select,
    SelectContent,
    SelectItem,
    SelectTrigger,
    SelectValue
} from "@/components/ui/select";
import {Label} from "@/components/ui/label";
import {format} from "date-fns";
import {ChevronLeft} from "lucide-react";
import {createItem, deleteItem, getItemById, updateItem} from "@/api/item.ts";
import type {ItemCondition} from "@/types/request/ItemRequest.ts";
import * as React from "react";

export default function ItemRegisterPage() {
    const navigate = useNavigate();
    const {itemId} = useParams<{ itemId?: string }>();

    // 기본값 세팅
    const [itemName, setItemName] = useState("");
    const [purchaseDate, setPurchaseDate] = useState(format(new Date(), "yyyy-MM-dd"));
    const [condition, setCondition] = useState<ItemCondition>("NEW");
    const [price, setPrice] = useState<number | "">("");
    const [hasSale, setHasSale] = useState(false);
    const [saleDate, setSaleDate] = useState(format(new Date(), "yyyy-MM-dd"));
    const [salePrice, setSalePrice] = useState<number | "">("");

    // 아이템 데이터 불러오기: 수정 모드일 때
    useEffect(() => {
        if (!itemId) return;
        getItemById(Number(itemId)).then(item => {
            setItemName(item.itemName);
            setPurchaseDate(item.purchaseDate);
            setCondition(item.condition);
            setPrice(item.price);
            setInputValue(item.price.toLocaleString());
            if (item.saleResponse) {
                setHasSale(true);
                setSaleDate(item.saleResponse.saleDate);
                setSalePrice(item.saleResponse.price);
                setInputSalePrice(item.saleResponse.price.toLocaleString());
            } else {
                setHasSale(false);
                setSaleDate(format(new Date(), "yyyy-MM-dd"));
                setSalePrice("");
            }
        });
    }, [itemId]);

    async function handleSubmit(e: React.FormEvent) {
        e.preventDefault();
        const data = {
            itemName,
            purchaseDate,
            condition,
            price: typeof price === "string" ? 0 : price,
            saleRequest: hasSale
                ? {
                    saleDate,
                    price: typeof salePrice === "string" ? 0 : salePrice,
                }
                : null,
        };

        if (itemId) {
            await updateItem(Number(itemId), data); // 수정
        } else {
            await createItem(data); // 등록
        }
        navigate("/");
    }

    async function handleDelete() {
        await deleteItem(Number(itemId))
    }

    const [inputValue, setInputValue] = useState(""); // 콤마가 포함된 문자열
    const [inputSalePrice, setInputSalePrice] = useState(""); // 콤마가 포함된 문자열

    const handleChange = (e: any) => {
        // 입력값에서 숫자만 추출
        const rawValue = e.target.value.replace(/,/g, "");
        // 숫자가 아니면 빈값 처리
        if (rawValue === "") {
            setInputValue("");
            setPrice('');
            return;
        }
        // 숫자 변환
        const numberValue = Number(rawValue);
        if (isNaN(numberValue)) return; // 숫자 아닌 값은 무시

        // 콤마 추가된 문자열 생성
        const formattedValue = numberValue.toLocaleString();
        if (e.target.id === 'price') {
            setInputValue(formattedValue);
            setPrice(numberValue);
        } else if (e.target.id === 'salePrice') {
            setInputSalePrice(formattedValue);
            setSalePrice(numberValue);
        }
    };

    return (
        <div className="max-w-[430px] mx-auto px-4 py-8">
            {/* 좌측 상단 뒤로가기 버튼 */}
            <Button
                variant="ghost"
                size="icon"
                className="absolute top-2 left-2"
                onClick={() => navigate(-1)}
                aria-label="뒤로가기"
            >
                <ChevronLeft className="w-6 h-6"/>
            </Button>
            <form className="space-y-6" onSubmit={handleSubmit}>
                <div>
                    <Label htmlFor="itemName">상품명</Label>
                    <Input id="itemName" value={itemName} onChange={e => setItemName(e.target.value)}
                           required/>
                </div>
                <div>
                    <Label htmlFor="purchaseDate">구매일</Label>
                    <Input id="purchaseDate" type="date" value={purchaseDate}
                           onChange={e => setPurchaseDate(e.target.value)} required/>
                </div>
                <div>
                    <Label htmlFor="condition">상태</Label>
                    <Select value={condition}
                            onValueChange={v => setCondition(v as ItemCondition)}>
                        <SelectTrigger id="condition">
                            <SelectValue placeholder="상태 선택"/>
                        </SelectTrigger>
                        <SelectContent>
                            <SelectItem value="NEW">새 제품</SelectItem>
                            <SelectItem value="USED">중고</SelectItem>
                        </SelectContent>
                    </Select>
                </div>
                <div>
                    <Label htmlFor="price">구매가</Label>
                    <Input
                        id="price"
                        type="text"
                        min={0}
                        value={inputValue}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <Label htmlFor="hasSale">
                        <input
                            id="hasSale"
                            type="checkbox"
                            className="mr-2 accent-blue-600"
                            checked={hasSale}
                            onChange={e => setHasSale(e.target.checked)}
                        />
                        판매 정보 입력
                    </Label>
                </div>
                {hasSale && (
                    <div className="grid grid-cols-2 gap-4">
                        <div>
                            <Label htmlFor="saleDate">판매일</Label>
                            <Input
                                id="saleDate"
                                type="date"
                                value={saleDate}
                                onChange={e => setSaleDate(e.target.value)}
                            />
                        </div>
                        <div>
                            <Label htmlFor="salePrice">판매가</Label>
                            <Input
                                id="salePrice"
                                type="text"
                                min={0}
                                value={inputSalePrice}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                )}
                <Button type="submit" className="w-full">
                    {itemId ? "수정하기" : "등록하기"}
                </Button>

                {itemId ? <Button onClick={handleDelete} className="w-full bg-red-500">
                        삭제하기
                    </Button>
                    : ""}
            </form>
        </div>
    );
}