package kr.end.backend.auth.controller;


import jakarta.validation.Valid;
import kr.end.backend.auth.dto.request.SignupRequest;
import kr.end.backend.auth.service.AuthService;
import kr.end.backend.member.dto.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 회원 가입 요청을 처리하고 성공 시 회원 정보를 반환합니다.
     *
     * @param request 유효성 검사가 적용된 회원 가입 요청 데이터
     * @return 생성된 회원 정보가 포함된 HTTP 200 OK 응답
     */
    @PostMapping("/signup")
    public ResponseEntity<MemberResponse> signup(@Valid @RequestBody SignupRequest request) {
        return ResponseEntity.ok(authService.signupMember(request));
    }

}
