package kr.end.backend.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.end.backend.auth.dto.response.MemberResponse;
import kr.end.backend.member.domain.Member;
import org.springframework.http.ResponseEntity;

@Tag(name = "내정보 API")
public interface MemberSwaggerController {

  @Operation(summary = "내 정보 조회", responses = {
      @ApiResponse(responseCode = "200", description = "내 정보 조회 성공", content = @Content(schema = @Schema(implementation = MemberResponse.class)))})
  public ResponseEntity<MemberResponse> readUserInfo(@Parameter(hidden = true) Member member);
}