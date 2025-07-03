package kr.end.backend.item.controller;

import jakarta.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import kr.end.backend.auth.config.AuthRequiredPrincipal;
import kr.end.backend.item.dto.request.ItemRequest;
import kr.end.backend.item.dto.response.ItemResponse;
import kr.end.backend.item.service.ItemManageService;
import kr.end.backend.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/items")
@RequiredArgsConstructor
public class ItemController implements ItemSwaggerController {

  private final ItemManageService itemService;

  @PostMapping
  public ResponseEntity<Void> createItem(@AuthRequiredPrincipal Member member,
      @RequestBody @Valid ItemRequest request) {

    long itemId = itemService.createItem(member, request);

    return ResponseEntity.created(URI.create("/items/" + itemId)).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateItem(@AuthRequiredPrincipal Member member,
      @PathVariable long id,
      @RequestBody @Valid ItemRequest request) {

    itemService.updateItem(member, id, request);

    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<List<ItemResponse>> getItems(@AuthRequiredPrincipal Member member,
      @RequestParam(required = false) LocalDate searchDate) {

    if (searchDate == null) {
      searchDate = LocalDate.now();
    }

    return ResponseEntity.ok(itemService.getItems(member, searchDate));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ItemResponse> getItem(@AuthRequiredPrincipal Member member,
      @PathVariable Long id) {
    return ResponseEntity.ok(itemService.getItem(member, id));
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteItem(@AuthRequiredPrincipal Member member,
      @PathVariable Long id) {

    itemService.deleteItem(member, id);
    return ResponseEntity.ok().build();
  }
//
//  @PatchMapping("/{id}")
//  public ResponseEntity<Void> updateItem(@AuthRequiredPrincipal Member member,
//      @PathVariable Long id, @RequestBody @Valid ItemRequest request) {
//
//    itemService.updateItem(member, id, request);
//    return ResponseEntity.ok().build();
//  }

}
