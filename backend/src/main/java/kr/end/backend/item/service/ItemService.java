package kr.end.backend.item.service;

import kr.end.backend.global.exception.ErrorCode;
import kr.end.backend.global.exception.JJUSTException;
import kr.end.backend.item.domain.TransactionItem;
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
public class ItemService {

  private final TransactionRepository transactionRepository;
  private final TransactionItemRepository itemRepository;

  public TransactionItem readTransaction(Member member, Long id) {
    TransactionItem transactionItem = itemRepository.findById(id).orElse(null);
    validateItemOwnership(member, transactionItem);
    return transactionItem;
  }

  private void validateItemOwnership(Member member, TransactionItem transactionItem) {
    if (!transactionItem.isOwnedBy(member)) {
      throw new JJUSTException(ErrorCode.FORBIDDEN_ITEM_NOT_OWNED);
    }
  }
}
