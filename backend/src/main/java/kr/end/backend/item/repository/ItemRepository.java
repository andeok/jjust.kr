package kr.end.backend.item.repository;

import java.util.List;
import kr.end.backend.item.domain.Item;
import kr.end.backend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE i.member = :member AND FUNCTION('YEAR', i.purchaseDate) = :year AND FUNCTION('MONTH', i.purchaseDate) = :month")
    List<Item> findByMember(Member member, int year, int month);
}
