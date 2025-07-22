package kr.end.backend.item.repository;

import java.util.List;
import kr.end.backend.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(
        value = "SELECT * FROM item i WHERE i.member_id = :memberId " +
            "AND TO_CHAR(i.purchase_date, 'YYYY') = :year " +
            "AND TO_CHAR(i.purchase_date, 'MM') = :month",
        nativeQuery = true
    )
    List<Item> findByMember(@Param("memberId") Long memberId, @Param("year") String year,
        @Param("month") String month);
}
