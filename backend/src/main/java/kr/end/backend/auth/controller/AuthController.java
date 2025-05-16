package kr.end.backend.auth.controller;


import kr.end.backend.auth.dto.request.SignupRequest;
import kr.end.backend.member.dto.response.MemberResponse;
import kr.end.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponse> signup(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(memberService.signupMember(request));
    }

}
