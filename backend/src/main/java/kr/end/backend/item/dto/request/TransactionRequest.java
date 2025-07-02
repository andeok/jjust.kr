package kr.end.backend.item.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import kr.end.backend.item.domain.TransactionType;

public record TransactionRequest(
    @NotNull(message = "판매 또는 구매를 입력하세요.")
    TransactionType transactionType,
    LocalDateTime transactionDate,
    int quantity,
    int price
) {

}
