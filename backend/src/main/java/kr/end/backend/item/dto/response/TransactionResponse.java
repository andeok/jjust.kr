package kr.end.backend.item.dto.response;

import java.time.LocalDateTime;
import kr.end.backend.item.domain.Transaction;
import kr.end.backend.item.domain.TransactionType;

public record TransactionResponse(
    long transactionId,
    TransactionType transactionType,
    LocalDateTime transactionDate,
    Integer quantity,
    Integer price
) {

  public TransactionResponse(Transaction transaction) {
    this(
        transaction.getId(),
        transaction.getTransactionType(),
        transaction.getTransactionDate(),
        transaction.getQuantity(),
        transaction.getPrice());
  }
}
