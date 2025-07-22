package kr.end.backend.item.dto.response;

import java.time.LocalDate;
import kr.end.backend.item.domain.Condition;
import kr.end.backend.item.domain.Item;

public record ItemResponse(
    long itemId,
    String itemName,
    LocalDate purchaseDate,
    Condition condition,
    int price,
    SaleResponse saleResponse
) {

    public ItemResponse(Item item) {
        this(item.getId(),
            item.getItemName(),
            item.getPurchaseDate(),
            item.getCondition(),
            item.getPrice(),
            item.getSale() != null ? new SaleResponse(item.getSale()) : null
        );
    }
}
