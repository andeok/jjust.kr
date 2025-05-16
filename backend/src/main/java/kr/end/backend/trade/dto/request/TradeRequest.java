package kr.end.backend.trade.dto.request;

import java.time.LocalDate;
import kr.end.backend.member.domain.Member;
import kr.end.backend.trade.domain.Trade;
import kr.end.backend.trade.domain.TradeType;

public record TradeRequest(
    TradeType tradeType,
    String itemName,
    String memo,
    int price,
    LocalDate tradeDate,
    Long parentId
) {

    public Trade toEntity(Trade trade, Member member) {
        return Trade.builder()
            .tradeType(tradeType())
            .itemName(itemName())
            .memo(memo())
            .price(price())
            .date(tradeDate())
            .parent(trade)
            .member(member)
            .build();
    }
}
