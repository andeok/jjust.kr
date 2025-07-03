package kr.end.backend.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import kr.end.backend.member.domain.Member;
import kr.end.backend.member.domain.RoleType;

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
    return
        new Member(request.email, request.nickname, encode, "", RoleType.ROLE_USER);
  }

}
