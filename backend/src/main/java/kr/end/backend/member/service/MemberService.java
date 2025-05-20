package kr.end.backend.member.service;

import java.util.List;
import kr.end.backend.auth.dto.request.SignupRequest;
import kr.end.backend.global.exception.EndException;
import kr.end.backend.global.exception.ErrorCode;
import kr.end.backend.member.domain.Member;
import kr.end.backend.member.dto.response.MemberResponse;
import kr.end.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new EndException(ErrorCode.NOT_FOUND_MEMBER));
    }

}
