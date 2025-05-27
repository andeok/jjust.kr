package kr.end.backend.item.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import kr.end.backend.item.domain.Item;
import kr.end.backend.member.domain.Member;

public record ItemRequest(
    @NotBlank(message = "상품명은 필수입니다")
    String name,
    boolean used,

    @PositiveOrZero(message = "구매가는 0 이상이어야 합니다")
    Integer purchasePrice,
    LocalDate purchaseDate,

    @PositiveOrZero(message = "판매가는 0 이상이어야 합니다")
    Integer salePrice,
    LocalDate saleDate,
    String memo
) {

  public Item toEntity(Member member) {
    return Item.builder()
        .name(name)
        .used(used)
        .purchasePrice(purchasePrice)
        .purchaseDate(purchaseDate)
        .salePrice(salePrice)
        .saleDate(saleDate)
        .memo(memo)
        .member(member)
        .build();
  }
}