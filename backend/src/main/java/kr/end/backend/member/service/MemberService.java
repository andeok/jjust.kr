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

    /**
     * 주어진 ID로 회원 정보를 조회합니다.
     *
     * @param memberId 조회할 회원의 ID
     * @return 해당 ID에 해당하는 회원 엔티티
     * @throws EndException 회원을 찾을 수 없는 경우 발생
     */
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new EndException(ErrorCode.NOT_FOUND_MEMBER));
    }

}
