package kr.end.backend.trade.controller;

import kr.end.backend.trade.dto.request.TradeRequest;
import kr.end.backend.trade.dto.response.TradeResponse;
import kr.end.backend.trade.service.TradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/trades")
@RequiredArgsConstructor
public class TradeController {

    private final TradeService tradeService;


    @PostMapping
    public ResponseEntity<TradeResponse> createTrade(@RequestBody TradeRequest request) {
        return ResponseEntity.ok(tradeService.createTrade(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TradeResponse> getTrade(@PathVariable Long id) {

        return null;
    }

}
