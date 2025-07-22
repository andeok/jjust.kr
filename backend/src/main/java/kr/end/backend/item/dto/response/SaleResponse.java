package kr.end.backend.item.dto.response;

import java.time.LocalDate;
import kr.end.backend.item.domain.Sale;

public record SaleResponse(
    long saleId,
    LocalDate saleDate,
    int price
) {

    public SaleResponse(Sale sale) {
        this(
            sale.getId(),
            sale.getSaleDate(),
            sale.getPrice()
        );
    }
}
