package kr.end.backend.item.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import kr.end.backend.item.domain.Item;
import kr.end.backend.item.domain.Sale;
import kr.end.backend.item.dto.request.ItemRequest;
import kr.end.backend.item.dto.response.ItemListResponse;
import kr.end.backend.item.dto.response.ItemResponse;
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
public class ItemManageService {

    private final ItemService itemService;
    private final SaleRepository saleRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long createItem(Member member, ItemRequest request) {

        Item item = itemRepository.save(request.toEntity(member));

        if (request.saleRequest() != null) {
            Sale sale = request.saleRequest().toEntity(item);
            saleRepository.save(sale);
        }

        return item.getId();
    }

    @Transactional
    public void updateItem(Member member, long id, ItemRequest request) {

        Item item = itemService.readItem(member, id);

        item.change(request);

        updateProcessTransaction(request, item);

    }

    private void updateProcessTransaction(ItemRequest request, Item item) {
        Sale sale = itemService.readSale(item.getId());

        if (sale != null) {
            if (request.saleRequest() != null) {
                Sale updateSale = request.saleRequest().toEntity(item);
                sale.change(updateSale);
            } else {
                saleRepository.delete(sale);
            }
        } else {
            if (request.saleRequest() != null) {
                Sale newSale = request.saleRequest().toEntity(item);
                saleRepository.save(newSale);
            }
        }
    }

    public ItemListResponse getItems(Member member, LocalDate searchDate) {
        String year = String.valueOf(searchDate.getYear());
        String month = String.valueOf(searchDate.getMonthValue());
        if (month.length() == 1) {
            month = "0" + month; // 월이 한 자리 수인 경우 앞에 0을 추가
        }

        // 정렬은 구매일 기준 내림차순, 판매일 기준 오름차순
        List<Item> items = itemRepository.findByMember(member.getId(), year, month)
            .stream()
            .sorted(Comparator.comparing(Item::getPurchaseDate).reversed()
                .thenComparing((Item p) -> p.getSale() != null ? p.getSale().getSaleDate() : null,
                    Comparator.nullsLast(Comparator.naturalOrder())
                )
            )
            .toList();

        return new ItemListResponse(items);
    }

    public ItemResponse getItem(Member member, Long id) {

        Item item = itemService.readItem(member, id);

        return new ItemResponse(item);
    }

    @Transactional
    public void deleteItem(Member member, Long id) {
        log.info("um...{}", id);

        Item item = itemService.readItem(member, id);
        itemRepository.delete(item);
    }
}
