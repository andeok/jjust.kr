package kr.end.backend.item.dto.request;

import java.time.LocalDate;
import kr.end.backend.item.domain.Item;
import kr.end.backend.item.domain.Sale;

public record SaleRequest(
    LocalDate saleDate,
    int price
) {

    public Sale toEntity(Item item) {
        return new Sale(item, price, saleDate);
    }
}
