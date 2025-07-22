package kr.end.backend.item.service;

import kr.end.backend.global.exception.ErrorCode;
import kr.end.backend.global.exception.JJUSTException;
import kr.end.backend.item.domain.Item;
import kr.end.backend.item.domain.Sale;
import kr.end.backend.item.repository.ItemRepository;
import kr.end.backend.item.repository.SaleRepository;
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

    private final SaleRepository saleRepository;
    private final ItemRepository itemRepository;

    public Item readItem(Member member, Long id) {

        Item item = itemRepository.findById(id).orElseThrow(
            () -> new JJUSTException(ErrorCode.NOT_FOUND_ITEM));

        validateItemOwnership(member, item);
        return item;
    }

    public Sale readSale(Long id) {
        Sale sale = saleRepository.findByItemId(id).orElse(null);
        return sale;
    }

    private void validateItemOwnership(Member member, Item item) {
        if (!item.isOwnedBy(member)) {
            throw new JJUSTException(ErrorCode.FORBIDDEN_ITEM_NOT_OWNED);
        }
    }
}
