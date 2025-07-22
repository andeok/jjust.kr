package kr.end.backend.item.repository;

import java.util.Optional;
import kr.end.backend.item.domain.Item;
import kr.end.backend.item.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    void deleteByItem(Item item);

    Optional<Sale> findByItemId(Long itemId);
}
