package kr.end.backend.item.controller;

import java.util.List;
import kr.end.backend.auth.config.AuthRequiredPrincipal;
import kr.end.backend.item.dto.request.ItemRequest;
import kr.end.backend.item.dto.response.ItemResponse;
import kr.end.backend.item.service.ItemService;
import kr.end.backend.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/items")
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @PostMapping
  public ResponseEntity<ItemResponse> createTrade(@AuthRequiredPrincipal Member member,
      @RequestBody ItemRequest request) {

    return ResponseEntity.ok(itemService.createItem(member, request));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ItemResponse> getItem(@AuthRequiredPrincipal Member member,
      @PathVariable Long id) {
    return ResponseEntity.ok(itemService.getItem(member, id));
  }

  @GetMapping
  public ResponseEntity<List<ItemResponse>> getItems(@AuthRequiredPrincipal Member member) {
    return ResponseEntity.ok(itemService.getItems(member));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteItem(@AuthRequiredPrincipal Member member,
      @PathVariable Long id) {

    itemService.deleteItem(member, id);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Void> updateItem(@AuthRequiredPrincipal Member member,
      @PathVariable Long id, @RequestBody ItemRequest request) {

    itemService.updateItem(member, id, request);
    return ResponseEntity.ok().build();
  }

}
