package kr.end.backend.item.repository;

import kr.end.backend.item.domain.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {

}
