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

  /**
   * 회원가입 요청 정보를 기반으로 암호화된 비밀번호를 포함한 Member 엔티티로 변환합니다.
   *
   * @param request 회원가입 요청 데이터
   * @param encode 암호화된 비밀번호 문자열
   * @return 변환된 Member 엔티티
   */
  public Member toEntity(SignupRequest request, String encode) {
    return Member.builder()
        .email(request.email())
        .password(encode)
        .nickname(request.nickname())
        .build();
  }
}
