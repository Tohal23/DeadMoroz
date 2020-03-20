package org.project.factory.service;

import org.project.moroz.model.GiftResponseDto;
import org.springframework.stereotype.Service;

@Service
public class CreateGiftService {

    private final AsyncService asyncService;

    public CreateGiftService(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    public GiftResponseDto createGift(String nameGift, Integer sumGift, String uuid) {
        try {
            asyncService.createGifts(nameGift, sumGift, uuid);

            GiftResponseDto giftResponseDto = new GiftResponseDto();
            giftResponseDto.setSuccess(true);
            return giftResponseDto;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GiftResponseDto giftResponseDto = new GiftResponseDto();
        giftResponseDto.setSuccess(false);
        return giftResponseDto;
    }
}
