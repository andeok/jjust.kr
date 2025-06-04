package kr.end.backend.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.end.backend.auth.dto.request.LoginRequest;
import kr.end.backend.auth.dto.request.SignupRequest;
import kr.end.backend.member.dto.response.MemberResponse;
import org.springframework.http.ResponseEntity;

@Tag(name = "회원 관련 API")
public interface AuthSwaggerController {

  @Operation(summary = "회원가입", responses = {
      @ApiResponse(responseCode = "200", description = "회원가입 성공", content = @Content(schema = @Schema(implementation = MemberResponse.class)))})
  public ResponseEntity<MemberResponse> signup(SignupRequest request);

  @Operation(summary = "로그인", responses = {
      @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = MemberResponse.class)))})
  public ResponseEntity<MemberResponse> login(LoginRequest request);
}
