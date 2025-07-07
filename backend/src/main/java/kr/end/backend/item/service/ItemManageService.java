package kr.end.backend.item.service;

import java.time.LocalDate;
import java.util.List;
import kr.end.backend.global.exception.ErrorCode;
import kr.end.backend.global.exception.JJUSTException;
import kr.end.backend.item.domain.Transaction;
import kr.end.backend.item.domain.TransactionItem;
import kr.end.backend.item.dto.request.ItemRequest;
import kr.end.backend.item.dto.response.ItemListResponse;
import kr.end.backend.item.dto.response.ItemResponse;
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
    List<Transaction> list = request.transactions().stream()
        .map(transaction -> new Transaction(
            item,
            transaction.transactionType(),
            transaction.quantity(),
            transaction.price(),
            transaction.transactionDate()
        ))
        .toList();

    transactionRepository.saveAll(list);
  }

  @Transactional
  public void updateItem(Member member, long id, ItemRequest request) {

    TransactionItem transactionItem = itemService.readTransactionItem(member, id);

    transactionItem.change(request);

    updateProcessTransaction(request, transactionItem);

  }

  private void updateProcessTransaction(ItemRequest request, TransactionItem item) {
    List<Transaction> transactions = item.getTransactions();
    List<Transaction> updateTransactions = request.transactions().stream()
        .map(transaction -> new Transaction(
            item,
            transaction.transactionType(),
            transaction.quantity(),
            transaction.price(),
            transaction.transactionDate()
        ))
        .toList();

    updateTransaction(transactions, updateTransactions);
  }

  private void updateTransaction(List<Transaction> transactions,
      List<Transaction> updateTransactions) {

    for (int i = 0; i < transactions.size(); i++) {
      transactions.get(i).change(updateTransactions.get(i));
    }
    transactionRepository.saveAll(transactions);
  }

  public ItemListResponse getItems(Member member, LocalDate searchDate) {

    int year = searchDate.getYear();
    int month = searchDate.getMonthValue();

    List<TransactionItem> items = itemRepository.findByMember(member, year, month);

    return new ItemListResponse(items);
  }

  public ItemResponse getItem(Member member, Long id) {

    TransactionItem transactionItem = itemRepository.findByMemberAndId(member, id)
        .orElseThrow(() -> new JJUSTException(ErrorCode.NOT_FOUND_ITEM));

    return new ItemResponse(transactionItem);
  }


  @Transactional
  public void deleteItem(Member member, Long id) {
    TransactionItem transactionItem = itemRepository.findByMemberAndId(member, id)
        .orElseThrow(() -> new JJUSTException(ErrorCode.NOT_FOUND_ITEM));

    itemRepository.delete(transactionItem);
  }
}
