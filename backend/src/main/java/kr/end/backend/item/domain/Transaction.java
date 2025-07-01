package kr.end.backend.item.domain;

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
import kr.end.backend.item.dto.request.TransactionRequest;
import kr.end.backend.member.domain.Member;
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

  private int quantity;

  private int price;

  @Enumerated(EnumType.STRING)
  private TransactionType transactionType;

  private LocalDateTime transactionDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "transaction_item_id")
  private TransactionItem transactionItem;

  public Transaction(TransactionItem item, TransactionRequest transaction) {
    this.quantity = transaction.quantity();
    this.price = transaction.price();
    this.transactionType = transaction.transactionType();
    this.transactionDate = transaction.transactionDate();
    this.transactionItem = item;
  }


}
