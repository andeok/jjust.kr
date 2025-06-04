package kr.end.backend.item.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import kr.end.backend.global.BaseEntity;
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
public class Item extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private boolean used;

  // 구매 정보
  private Integer purchasePrice;       // 구매 가격 (null 가능)
  private LocalDate purchaseDate;      // 구매 일자 (null 가능)

  // 판매 정보
  private Integer salePrice;           // 판매 가격 (null 가능)
  private LocalDate saleDate;          // 판매 일자 (null 가능)

  // 비고
  private String memo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  public void update(String name, boolean used, Integer purchasePrice, LocalDate purchaseDate,
      Integer salePrice, LocalDate saleDate, String memo) {
    this.name = name;
    this.used = used;
    this.purchasePrice = purchasePrice;
    this.purchaseDate = purchaseDate;
    this.salePrice = salePrice;
    this.saleDate = saleDate;
    this.memo = memo;
  }


  public Integer getProfit() {
    if (this.salePrice == null || this.purchasePrice == null) {
      return null;
    }
    return this.salePrice - this.purchasePrice;
  }

  public Long getPeriodUse() {
    if (this.saleDate == null || this.purchaseDate == null) {
      return null;
    }
    return ChronoUnit.DAYS.between(this.purchaseDate, this.saleDate);
  }

  public Integer getDailyCost() {
    if (this.saleDate == null) {
      return null;
    }
    Integer profit = this.getProfit();
    if (profit == null) {
      return null;
    }

    Long period = this.getPeriodUse();
    if (period == null) {
      return null;
    }

    if (period == 0) {
      period = 1L; // 당일 구매/판매의 경우 1일로 계산
    }

    return Math.toIntExact(profit / period);
  }
}
