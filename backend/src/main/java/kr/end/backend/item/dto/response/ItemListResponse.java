package kr.end.backend.item.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import kr.end.backend.item.domain.Item;

public record ItemListResponse(
    int purchasePrice,
    int salesPrice,
    int sumPrice,
    int purchaseCount,
    int salesCount,
    Map<LocalDate, List<ItemResponse>> items) {

    public ItemListResponse(List<Item> items) {
        this(
            items.stream().mapToInt(Item::getPrice).sum(),
            items.stream()
                .mapToInt(item -> item.getSale() != null && item.getSale().getPrice() != null
                    ? item.getSale().getPrice()
                    : 0)
                .sum(),
            items.stream()
                .mapToInt(item -> item.getSale() != null && item.getSale().getPrice() != null
                    ? item.getSale().getPrice()
                    : 0)
                .sum()
                - items.stream().mapToInt(Item::getPrice).sum(),
            items.size(),
            (int) items.stream().filter(item -> item.getSale() != null).count(),
            groupItemsByDate(items));
    }

    private static Map<LocalDate, List<ItemResponse>> groupItemsByDate(List<Item> items) {
        return items.stream()
            .collect(Collectors.groupingBy(
                Item::getPurchaseDate, // Item의 구매일자 기준
                Collectors.mapping(ItemResponse::new, Collectors.toList())
            ));
    }


}
