package kr.end.backend.item.service;

import java.util.List;
import kr.end.backend.global.exception.ErrorCode;
import kr.end.backend.global.exception.JJUSTException;
import kr.end.backend.item.domain.Item;
import kr.end.backend.item.dto.request.ItemRequest;
import kr.end.backend.item.dto.response.ItemResponse;
import kr.end.backend.item.repository.ItemRepository;
import kr.end.backend.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

  private final ItemRepository itemRepository;

  @Transactional
  public ItemResponse createItem(Member member, ItemRequest request) {

    Item item = request.toEntity(member);

    Item saveItem = itemRepository.save(item);

    return new ItemResponse(saveItem);
  }


  public ItemResponse getItem(Member member, Long id) {

    Item item = itemRepository.findByMemberAndId(member, id)
        .orElseThrow(() -> new JJUSTException(ErrorCode.NOT_FOUND_ITEM));

    return new ItemResponse(item);
  }


  public List<ItemResponse> getItems(Member member) {
    List<Item> item = itemRepository.findByMember(member)
        .orElseThrow(() -> new JJUSTException(ErrorCode.NOT_FOUND_ITEM));

    return item.stream()
        .map(ItemResponse::new)
        .toList();
  }

  @Transactional
  public void deleteItem(Member member, Long id) {
    Item item = itemRepository.findByMemberAndId(member, id)
        .orElseThrow(() -> new JJUSTException(ErrorCode.NOT_FOUND_ITEM));

    itemRepository.delete(item);
  }

  @Transactional
  public void updateItem(Member member, Long id, ItemRequest request) {
    Item item = itemRepository.findById(id)
        .orElseThrow(() -> new JJUSTException(ErrorCode.NOT_FOUND_ITEM));

    item.update(
        request.name(),
        request.used(),
        request.purchasePrice(),
        request.purchaseDate(),
        request.salePrice(),
        request.saleDate(),
        request.memo()
    );
  }
}
