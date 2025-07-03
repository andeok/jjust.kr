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
import jakarta.persistence.OneToMany;
import java.util.List;
import kr.end.backend.global.BaseEntity;
import kr.end.backend.item.dto.request.ItemRequest;
import kr.end.backend.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionItem extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String itemName;

  @Enumerated(EnumType.STRING)
  private Condition condition;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToMany(mappedBy = "transactionItem", cascade = CascadeType.ALL)
  private List<Transaction> transactions;

  public TransactionItem(String itemName, Condition condition, Member member) {
    this.itemName = itemName;
    this.condition = condition;
    this.member = member;
  }

  public boolean isOwnedBy(Member member) {
    return this.member.equals(member);
  }

  public void change(ItemRequest request) {
    this.itemName = request.itemName();
    this.condition = request.condition();
  }
}
