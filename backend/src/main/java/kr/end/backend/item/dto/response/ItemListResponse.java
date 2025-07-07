package kr.end.backend.item.dto.response;

import java.util.List;
import kr.end.backend.item.domain.Transaction;
import kr.end.backend.item.domain.TransactionItem;
import kr.end.backend.item.domain.TransactionType;

public record ItemListResponse(
    int purchasePrice,
    int salesPrice,
    int sumPrice,
    List<ItemResponse> items) {

  public ItemListResponse(List<TransactionItem> transactionItems) {
    this(
        getPurchasePrice(transactionItems, TransactionType.PURCHASE),
        getPurchasePrice(transactionItems, TransactionType.SALES),
        getPurchasePrice(transactionItems, TransactionType.SALES) - getPurchasePrice(
            transactionItems, TransactionType.PURCHASE),
        getItems(transactionItems));
  }

  private static List<ItemResponse> getItems(List<TransactionItem> item) {
    return item.stream()
        .map(ItemResponse::new)
        .toList();
  }

  private static int getPurchasePrice(List<TransactionItem> transactions, TransactionType type) {

    return transactions.stream()
        .flatMap(item -> item.getTransactions().stream())
        .filter(transaction -> transaction.getTransactionType().equals(type))
        .mapToInt(Transaction::getPrice)
        .sum();
  }

}
