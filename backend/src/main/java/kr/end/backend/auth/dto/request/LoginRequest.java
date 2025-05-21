package kr.end.backend.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import kr.end.backend.member.domain.Member;

public record LoginRequest(

    @Email
    @NotBlank(message = "이메일을 입력하세요.")
    String email,

    @NotBlank(message = "비밀번호를 입력하세요.")
    String password

) {

  public Member toEntity(LoginRequest request, String encode) {
    return Member.builder()
        .email(request.email())
        .password(encode)
        .build();
  }
}
