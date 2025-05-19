package kr.end.backend.trade.dto.response;

import java.time.LocalDate;
import kr.end.backend.trade.domain.Trade;
import kr.end.backend.trade.domain.TradeType;

public record TradeResponse(

    Long id, TradeType tradeType, String itemName, String memo, int price, LocalDate tradeDate, Long parentId) {

    public static TradeResponse of(Trade save) {
        Long parentId = (save.getParent() != null) ? save.getParent().getId() : null;

        return new TradeResponse(save.getId(), save.getTradeType(), save.getItemName(), save.getMemo(), save.getPrice(),
            save.getTradeDate(), parentId);
    }


}
