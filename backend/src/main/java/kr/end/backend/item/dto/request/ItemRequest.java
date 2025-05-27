package kr.end.backend.item.dto.request;

import java.time.LocalDate;
import kr.end.backend.item.domain.Item;
import kr.end.backend.member.domain.Member;

public record ItemRequest(
    String name,
    boolean used,
    Integer purchasePrice,
    LocalDate purchaseDate,
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