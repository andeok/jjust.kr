package kr.end.backend.trade.repository;

import kr.end.backend.trade.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {

}
