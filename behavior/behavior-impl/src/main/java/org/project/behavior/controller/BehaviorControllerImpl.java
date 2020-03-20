package org.project.behavior.controller;

import org.project.behavior.model.BehaviorRequestDto;
import org.project.behavior.model.BehaviorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class BehaviorControllerImpl implements BehaviorController {
    @Override
    public ResponseEntity<BehaviorResponseDto> getBehavior(BehaviorRequestDto behaviorRequestDto) {
        Random random = new Random();
        BehaviorResponseDto responseDto = new BehaviorResponseDto();
        responseDto.setGreat(random.nextBoolean());
        return ResponseEntity.ok(responseDto);
    }
}
