package kr.end.backend.item.dto.request;

import java.time.LocalDate;
import kr.end.backend.item.domain.Condition;
import kr.end.backend.item.domain.Item;
import kr.end.backend.member.domain.Member;

public record ItemRequest(String itemName,
                          LocalDate purchaseDate,
                          Condition condition,
                          int price,
                          SaleRequest saleRequest) {

    public Item toEntity(Member member) {
        return new Item(itemName, purchaseDate, price, condition, member);
    }
}