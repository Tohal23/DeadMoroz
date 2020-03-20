package org.project.moroz.controller;

import org.project.moroz.model.GiftRequestDto;
import org.project.moroz.model.GiftResponseDto;
import org.project.moroz.model.OrderRequestDto;
import org.project.moroz.model.OrderResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface GiftController {
    @PostMapping("get")
    ResponseEntity<OrderResponseDto> getOrder(@RequestBody OrderRequestDto orderRequest);

    @PostMapping("add")
    ResponseEntity<GiftResponseDto> addGift(@RequestBody GiftRequestDto giftRequestDto);
}
