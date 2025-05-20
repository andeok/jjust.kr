package kr.end.backend.auth.service;

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
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 모든 회원 정보를 조회하여 MemberResponse 리스트로 반환합니다.
     *
     * @return 모든 회원의 정보를 담은 MemberResponse 객체들의 리스트
     */
    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll().stream().map(data -> new MemberResponse(data)).toList();
    }

    /**
     * 새로운 회원을 등록합니다.
     *
     * 이메일 중복 여부를 확인한 후, 비밀번호를 암호화하여 회원 정보를 저장하고 등록된 회원 정보를 반환합니다.
     *
     * @param request 회원 가입 요청 정보
     * @return 등록된 회원의 응답 DTO
     * @throws EndException 이미 등록된 이메일인 경우 발생
     */
    @Transactional
    public MemberResponse signupMember(SignupRequest request) {

        duplicateEmail(request.email());

        String encode = generatePassword(request.password());
        Member member = request.toEntity(request, encode);
        Member result = memberRepository.save(member);

        return new MemberResponse(result);
    }

    /**
     * 주어진 이메일이 이미 등록되어 있는지 확인하고, 중복일 경우 예외를 발생시킵니다.
     *
     * @param email 중복 여부를 검사할 이메일 주소
     * @throws EndException 이메일이 이미 등록된 경우 발생
     */
    private void duplicateEmail(String email) {
        boolean exists = memberRepository.findByEmail(email).isPresent();
        if (exists) {
            throw new EndException(ErrorCode.ALREADY_REGISTERED_EMAIL);
        }
    }

    /**
     * 주어진 비밀번호를 암호화하여 반환합니다.
     *
     * @param password 암호화할 원본 비밀번호
     * @return 암호화된 비밀번호 문자열
     */
    private String generatePassword(String password) {
        String rawPassword = password;
        return passwordEncoder.encode(rawPassword);
    }

}
