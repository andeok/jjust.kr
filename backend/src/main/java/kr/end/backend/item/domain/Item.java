package kr.end.backend.item.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import kr.end.backend.global.BaseEntity;
import kr.end.backend.item.dto.request.ItemRequest;
import kr.end.backend.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private LocalDate purchaseDate;

    private int price;

    @Enumerated(EnumType.STRING)
    private Condition condition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Sale sale;

    public Item(String itemName, LocalDate purchaseDate, int price, Condition condition,
        Member member) {
        this.itemName = itemName;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.condition = condition;
        this.member = member;
    }

    public boolean isOwnedBy(Member member) {
        return this.member.getId().equals(member.getId());
    }

    public void change(ItemRequest request) {
        this.itemName = request.itemName();
        this.purchaseDate = request.purchaseDate();
        this.price = request.price();
        this.condition = request.condition();
    }
}
