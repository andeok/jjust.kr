package kr.end.backend.item.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import kr.end.backend.global.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Transaction extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "transaction_item_id")
  private TransactionItem transactionItem;

  @Enumerated(EnumType.STRING)
  private TransactionType transactionType;

  private Integer quantity;

  private Integer price;

  private LocalDateTime transactionDate;

  public Transaction(TransactionItem transactionItem, TransactionType transactionType,
      Integer quantity, Integer price, LocalDateTime transactionDate) {
    this.transactionItem = transactionItem;
    this.transactionType = transactionType;
    this.quantity = quantity;
    this.price = price;
    this.transactionDate = transactionDate;
  }


  public void change(Transaction transaction) {
    this.quantity = transaction.quantity;
    this.price = transaction.price;
    this.transactionDate = transaction.transactionDate;
  }
}
