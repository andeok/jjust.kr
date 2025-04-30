package kr.end.backend;

import kr.end.backend.member.domain.Member;
import kr.end.backend.member.dto.request.SignupRequest;
import kr.end.backend.member.repository.MemberRepository;
import kr.end.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

  private final MemberService memberService;

  @Override
  public void run(String... args) throws Exception {
    SignupRequest test1 = new SignupRequest("test1@naver.com", "1234", "test1");
    memberService.signupMember(test1);

    SignupRequest test2 = new SignupRequest("test2@naver.com", "1234", "test2");
    memberService.signupMember(test2);

  }
}
