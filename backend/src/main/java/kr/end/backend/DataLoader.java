package kr.end.backend;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import kr.end.backend.auth.dto.request.SignupRequest;
import kr.end.backend.auth.service.AuthService;
import kr.end.backend.item.domain.Condition;
import kr.end.backend.item.domain.TransactionType;
import kr.end.backend.item.dto.request.ItemRequest;
import kr.end.backend.item.dto.request.TransactionRequest;
import kr.end.backend.item.service.ItemManageService;
import kr.end.backend.member.domain.Member;
import kr.end.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("!prod")
public class DataLoader implements CommandLineRunner {

  private final AuthService authService;
  private final ItemManageService itemManageService;
  private final MemberService memberService;

  @Override
  public void run(String... args) throws Exception {
    SignupRequest test1 = new SignupRequest("test1@naver.com", "1234", "test1");
    authService.signupMember(test1);

    SignupRequest test2 = new SignupRequest("test2@naver.com", "1234", "test2");
    authService.signupMember(test2);

    Member member = memberService.getMember(1L);

    for (int i = 0; i < 10001; i++) {
      TransactionRequest sales = new TransactionRequest(TransactionType.SALES,
          LocalDateTime.now(), i, 50000);

      TransactionRequest purchase = new TransactionRequest(TransactionType.PURCHASE,
          LocalDateTime.now(), i, 50000);

      List<TransactionRequest> transactions = new ArrayList<>();
      transactions.add(sales);
      transactions.add(purchase);

      ItemRequest itemRequest = new ItemRequest("name-" + i, Condition.NEW, transactions);


      itemManageService.createItem(member, itemRequest);
    }

  }
}
