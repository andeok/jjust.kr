package kr.end.backend.auth.controller;


import jakarta.validation.Valid;
import kr.end.backend.auth.dto.request.LoginRequest;
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
public class AuthController implements AuthSwaggerController{

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponse> signup(@Valid @RequestBody SignupRequest request) {
        return ResponseEntity.ok(authService.signupMember(request));
    }

    @PostMapping("/login")
    public ResponseEntity<MemberResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok().build();
    }



}
