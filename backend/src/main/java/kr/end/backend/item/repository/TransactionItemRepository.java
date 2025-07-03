package kr.end.backend.item.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import kr.end.backend.item.domain.TransactionItem;
import kr.end.backend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {

  @Query("""
      select distinct i from TransactionItem i
      join fetch i.transactions t
      where i.member = ?1
        and exists (
          select 1 from i.transactions t2
          where t2.transactionType = 'PURCHASE'
            and YEAR(t2.transactionDate) = ?2
            and MONTH(t2.transactionDate) = ?3
        )
      """)
  List<TransactionItem> findByMember(Member member, int year, int month);

  @Query("select i from TransactionItem i join fetch i.transactions c where i.member = ?1 and i.id = ?2")
  Optional<TransactionItem> findByMemberAndId(Member member, Long id);
}
