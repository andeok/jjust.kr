package kr.end.backend.item.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import kr.end.backend.item.domain.Condition;
import kr.end.backend.item.domain.TransactionItem;
import kr.end.backend.member.domain.Member;

public record ItemRequest(
    String itemName,
    Condition condition,
    @Valid @NotNull(message = "판매 또는 구매를 입력하세요.") List<TransactionRequest> transaction
) {


  public TransactionItem toEntity(Member member) {
    return new TransactionItem(itemName, condition, member);
  }
}