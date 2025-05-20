package kr.end.backend.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import kr.end.backend.member.domain.Member;

public record SignupRequest(

    @Email
    @NotBlank(message = "이메일을 입력하세요.")
    String email,

    @NotBlank(message = "비밀번호를 입력하세요.")
    String password,

    @NotBlank(message = "닉네임을 입력하세요.")
    String nickname
) {

  public Member toEntity(SignupRequest request, String encode) {
    return Member.builder()
        .email(request.email())
        .password(encode)
        .nickname(request.nickname())
        .build();
  }
}
