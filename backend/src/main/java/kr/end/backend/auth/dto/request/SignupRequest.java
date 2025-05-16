package kr.end.backend.auth.dto.request;

import kr.end.backend.member.domain.Member;

public record SignupRequest(
        String email,
        String password,
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
