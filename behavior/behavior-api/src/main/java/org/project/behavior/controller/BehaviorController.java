package org.project.behavior.controller;

import org.project.behavior.model.BehaviorRequestDto;
import org.project.behavior.model.BehaviorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface BehaviorController {

    @PostMapping("get-gift")
    ResponseEntity<BehaviorResponseDto> getBehavior(@RequestBody BehaviorRequestDto behaviorRequestDto);

}
