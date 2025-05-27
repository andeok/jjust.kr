package kr.end.backend.item.dto.response;

import java.time.LocalDate;
import kr.end.backend.item.domain.Item;

public record ItemResponse(
    Long id,
    String name,
    boolean used,
    Integer purchasePrice,
    LocalDate purchaseDate,
    Integer salePrice,
    LocalDate saleDate,
    String memo

) {


  public ItemResponse(Item item) {
    this(item.getId(),
        item.getName(),
        item.isUsed(),
        item.getPurchasePrice(),
        item.getPurchaseDate(),
        item.getSalePrice(),
        item.getSaleDate(),
        item.getMemo());
  }
}


