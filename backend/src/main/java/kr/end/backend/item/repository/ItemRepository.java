package kr.end.backend.item.repository;

import java.util.List;
import java.util.Optional;
import kr.end.backend.member.domain.Member;
import kr.end.backend.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

  Optional<Item> findByMemberAndId(Member member, Long id);

  List<Item> findByMember(Member member);

  void deleteByMemberAndId(Member member, Long id);
}
