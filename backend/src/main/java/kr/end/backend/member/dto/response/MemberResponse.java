package kr.end.backend.member.dto.response;

import kr.end.backend.member.domain.Member;

public record MemberResponse(

    Long id,
    String email,
    String nickname
) {

  public MemberResponse(Member m) {
    this(
        m.getId(),
        m.getEmail(),
        m.getNickname()
    );
  }
}
