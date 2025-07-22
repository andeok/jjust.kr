package kr.end.backend.item.dto.response;

import java.util.List;
import kr.end.backend.item.domain.Item;

public record ItemListResponse(
    int purchasePrice,
    int salesPrice,
    int sumPrice,
    List<ItemResponse> items) {

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
            getItems(items));

    }

    private static List<ItemResponse> getItems(List<Item> item) {
        return item.stream()
            .map(ItemResponse::new)
            .toList();
    }

}
