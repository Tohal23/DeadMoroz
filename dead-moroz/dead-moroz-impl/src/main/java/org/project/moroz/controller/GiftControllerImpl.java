package org.project.moroz.controller;

import org.project.moroz.model.GiftRequestDto;
import org.project.moroz.model.GiftResponseDto;
import org.project.moroz.model.OrderRequestDto;
import org.project.moroz.model.OrderResponseDto;
import org.project.moroz.service.GiftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("gift")
public class GiftControllerImpl implements GiftController {
    private final GiftService giftService;

    public GiftControllerImpl(GiftService giftService) {
        this.giftService = giftService;
    }

    @Override
    public ResponseEntity<OrderResponseDto> getOrder(@RequestBody OrderRequestDto orderRequest) {
        try {
            String nameGift = orderRequest.getNameGift();
            String nameChild = orderRequest.getNameChild();
            Optional<OrderResponseDto> order = giftService.getOrder(nameGift, nameChild);
            return order.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Behaviour service not found", e);
        }
    }

    @Override
    public ResponseEntity<GiftResponseDto> addGift(GiftRequestDto giftRequestDto) {
        String nameGift = giftRequestDto.getNameGift();
        Integer sumGift = giftRequestDto.getSumGift();
        String uuid = giftRequestDto.getUuid();
        GiftResponseDto giftResponseDto = giftService.addGift(nameGift, sumGift, uuid);
        return ResponseEntity.ok(giftResponseDto);
    }
}
