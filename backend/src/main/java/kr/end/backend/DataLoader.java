package kr.end.backend;

import kr.end.backend.auth.dto.request.SignupRequest;
import kr.end.backend.auth.service.AuthService;
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
    //    private final ItemManageService itemManageService;
    private final MemberService memberService;

    @Override
    public void run(String... args) throws Exception {
        SignupRequest test1 = new SignupRequest("test1@naver.com", "1234", "test1");
        authService.signupMember(test1);

        SignupRequest test2 = new SignupRequest("test2@naver.com", "1234", "test2");
        authService.signupMember(test2);

        Member member = memberService.getMember(1L);
//        ItemRequest itemRequest;
//
//        for (int i = 0; i < 10001; i++) {
//            SaleRequest sales = new SaleRequest(LocalDate.now(), 40000);
//
//            if (i % 2 == 0) {
//                itemRequest = new ItemRequest("name-" + i, LocalDate.now(),
//                    Condition.NEW,
//                    50000, null);
//            } else {
//                itemRequest = new ItemRequest("name-" + i, LocalDate.now(),
//                    Condition.USED, 50000, sales);
//            }
//
//            itemManageService.createItem(member, itemRequest);
//        }

    }
}
