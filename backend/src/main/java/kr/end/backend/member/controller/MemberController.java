package kr.end.backend.member.controller;

import java.util.List;
import kr.end.backend.member.domain.Member;
import kr.end.backend.member.dto.request.SignupRequest;
import kr.end.backend.member.dto.response.MemberResponse;
import kr.end.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping
  public ResponseEntity<List<MemberResponse>> getAllMembers() {
    return ResponseEntity.ok(memberService.getAllMembers());
  }

  @PostMapping
  public ResponseEntity<MemberResponse> signup(@RequestBody SignupRequest request) {
    return ResponseEntity.ok(memberService.signupMember(request));
  }
}
