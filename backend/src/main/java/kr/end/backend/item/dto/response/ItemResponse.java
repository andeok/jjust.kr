package kr.end.backend.item.dto.response;

import java.util.Comparator;
import java.util.List;
import kr.end.backend.item.domain.Transaction;
import kr.end.backend.item.domain.TransactionItem;
import kr.end.backend.item.domain.TransactionType;

public record ItemResponse(
    long itemId,
    String itemName,
    List<TransactionResponse> transactions
) {

  public ItemResponse(TransactionItem item) {
    this(item.getId(),
        item.getItemName(),
        getTransactions(item.getTransactions()));
  }

  private static List<TransactionResponse> getTransactions(List<Transaction> transactions) {

    return transactions.stream()
        .map(transaction -> new TransactionResponse(transaction))
        .sorted(Comparator.comparing(TransactionResponse::transactionDate))
        .toList();
  }
}
