package kr.end.backend;

import kr.end.backend.auth.dto.request.SignupRequest;
import kr.end.backend.auth.service.AuthService;
import kr.end.backend.member.domain.RoleType;
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

  @Override
  public void run(String... args) throws Exception {
    SignupRequest test1 = new SignupRequest("test1@naver.com", "1234", "test1");
    authService.signupMember(test1);

    SignupRequest test2 = new SignupRequest("test2@naver.com", "1234", "test2");
    authService.signupMember(test2);

  }
}
