package org.project.factory.controller;

import org.project.factory.service.CreateGiftService;
import org.project.moroz.model.GiftRequestDto;
import org.project.moroz.model.GiftResponseDto;
import org.project.moroz.model.OrderResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
public class CreateGiftController {

    private final CreateGiftService createGiftService;

    public CreateGiftController(CreateGiftService createGiftService) {
        this.createGiftService = createGiftService;
    }

    @PostMapping("add-gift")
    public ResponseEntity<GiftResponseDto> createGift(@RequestBody GiftRequestDto giftRequestDto) {
        GiftResponseDto giftResponseDto = createGiftService.createGift(giftRequestDto.getNameGift(), giftRequestDto.getSumGift(), giftRequestDto.getUuid());
        return ResponseEntity.ok(giftResponseDto);
    }

}
