import type {Item} from "@/types/response/item";

interface ItemCardProps {
  item: Item;
}

export function ItemCard({item}: ItemCardProps) {
  return (
      <div className="border rounded p-4 flex flex-col gap-2">
        <div>
          <strong>{item.itemName}</strong> ({item.condition})
        </div>
        <div>구매일: {item.purchaseDate}</div>
        <div>구매가: ₩ {item.price.toLocaleString()}</div>
        {item.saleResponse ? (
            <div className="text-green-700">
              판매일: {item.saleResponse.saleDate} / 판매가: ₩ {item.saleResponse.price.toLocaleString()}
            </div>
        ) : (
            <div className="text-gray-500">아직 판매되지 않음</div>
        )}
      </div>
  );
}