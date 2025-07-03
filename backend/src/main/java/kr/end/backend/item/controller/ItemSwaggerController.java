package kr.end.backend.item.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.List;
import kr.end.backend.item.dto.request.ItemRequest;
import kr.end.backend.item.dto.response.ItemResponse;
import kr.end.backend.member.domain.Member;
import org.springframework.http.ResponseEntity;

@Tag(name = "물품 관련 API")
public interface ItemSwaggerController {

  @Operation(summary = "물품 등록", responses = {
      @ApiResponse(responseCode = "201", description = "물품 등록 성공")})
  ResponseEntity<Void> createItem(@Parameter(hidden = true) Member member,
      ItemRequest request);

  @Operation(summary = "선택한 물품 수정", responses = {
      @ApiResponse(responseCode = "200", description = "물품 수정 성공")})
  ResponseEntity<Void> updateItem(@Parameter(hidden = true) Member member,
      long id, ItemRequest request);

  @Operation(summary = "선택한 물품 조회", responses = {
      @ApiResponse(responseCode = "200", description = "물품 조회 성공",
          content = @Content(schema = @Schema(implementation = ItemResponse.class)))})
  ResponseEntity<ItemResponse> getItem(@Parameter(hidden = true) Member member, Long id);

  @Operation(summary = "내 물품 전체 조회", responses = {
      @ApiResponse(responseCode = "200", description = "물품 조회 성공",
          content = @Content(schema = @Schema(implementation = ItemResponse[].class)))})
  ResponseEntity<List<ItemResponse>> getItems(@Parameter(hidden = true) Member member, LocalDate searchDate);

  @Operation(summary = "선택한 물품 삭제", responses = {
      @ApiResponse(responseCode = "200", description = "물품 삭제 성공")})
  ResponseEntity<Void> deleteItem(@Parameter(hidden = true) Member member, Long id);


}
