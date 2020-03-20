package org.project.factory.service;

import org.project.moroz.model.GiftRequestDto;
import org.project.moroz.model.GiftResponseDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AsyncService {

    @Async
    public void createGifts(String nameGift, Integer sumGift, String uuid) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        Thread.sleep(1000);
        String url = "http://localhost:8080/gift/add";

        GiftRequestDto giftRequestDto = new GiftRequestDto();

        giftRequestDto.setNameGift(nameGift);
        giftRequestDto.setSumGift(sumGift);
        giftRequestDto.setUuid(uuid);

        GiftResponseDto body = restTemplate.postForObject(url, giftRequestDto, GiftResponseDto.class);
    }

}
