package kr.end.backend.item.service;

import java.util.List;
import kr.end.backend.item.domain.Transaction;
import kr.end.backend.item.domain.TransactionItem;
import kr.end.backend.item.dto.request.ItemRequest;
import kr.end.backend.item.repository.TransactionItemRepository;
import kr.end.backend.item.repository.TransactionRepository;
import kr.end.backend.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemManageService {

  private final ItemService itemService;
  private final TransactionRepository transactionRepository;
  private final TransactionItemRepository itemRepository;

  @Transactional
  public Long createItem(Member member, ItemRequest request) {

    TransactionItem item = itemRepository.save(request.toEntity(member));

    createTransaction(request, item);

    return item.getId();
  }

  private void createTransaction(ItemRequest request, TransactionItem item) {
    List<Transaction> list = request.transaction().stream()
        .map(transaction -> new Transaction(item, transaction))
        .toList();

    transactionRepository.saveAll(list);
  }

  public void updateItem(Member member, long id, ItemRequest request) {

    TransactionItem transactionItem = itemService.readTransaction(member, id);


  }

//  public ItemResponse getItem(Member member, Long id) {
//
//    TransactionItem transactionItem = productionRepository.findByMemberAndId(member, id)
//        .orElseThrow(() -> new JJUSTException(ErrorCode.NOT_FOUND_ITEM));
//
//    return new ItemResponse(transactionItem);
//  }
//
//
//  public List<ItemResponse> getItems(Member member) {
//    List<TransactionItem> transactionItem = productionRepository.findByMember(member);
//
//    return transactionItem.stream()
//        .map(ItemResponse::new)
//        .toList();
//  }
//
//  @Transactional
//  public void deleteItem(Member member, Long id) {
//    TransactionItem transactionItem = productionRepository.findByMemberAndId(member, id)
//        .orElseThrow(() -> new JJUSTException(ErrorCode.NOT_FOUND_ITEM));
//
//    productionRepository.delete(transactionItem);
//  }
//
//  @Transactional
//  public void updateItem(Member member, Long id, ItemRequest request) {
//    TransactionItem transactionItem = productionRepository.findByMemberAndId(member, id)
//        .orElseThrow(() -> new JJUSTException(ErrorCode.NOT_FOUND_ITEM));
//
//    transactionItem.update(
//        request.name(),
//        request.used(),
//        request.purchasePrice(),
//        request.purchaseDate(),
//        request.salePrice(),
//        request.saleDate(),
//        request.memo()
//    );
//  }
}
