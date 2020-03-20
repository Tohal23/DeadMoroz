package org.project.moroz.service;

import org.project.behavior.model.BehaviorRequestDto;
import org.project.behavior.model.BehaviorResponseDto;
import org.project.moroz.domain.Gift;
import org.project.moroz.domain.GiftRequest;
import org.project.moroz.domain.Order;
import org.project.moroz.model.GiftRequestDto;
import org.project.moroz.model.GiftResponseDto;
import org.project.moroz.model.OrderResponseDto;
import org.project.moroz.repo.GiftRepo;
import org.project.moroz.repo.GiftRequestRepo;
import org.project.moroz.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
public class GiftService {
    private final Integer minCount;
    private final String urlServiceBehavior;
    private final String urlServiceGiftFactory;

    private final RestTemplate restTemplate;

    private final GiftRepo giftRepo;
    private final OrderRepo orderRepo;
    private final GiftRequestRepo giftRequestRepo;

    public GiftService(@Value("${gift.count.min}") Integer minCount,
                       @Value("${service.behavior.url}") String urlServiceBehavior,
                       @Value("${service.giftfactory.url}") String urlServiceGiftFactory, GiftRepo giftRepo,
                       OrderRepo orderRepo,
                       GiftRequestRepo giftRequestRepo,
                       RestTemplate restTemplate) {
        this.minCount = minCount;
        this.urlServiceBehavior = urlServiceBehavior;
        this.urlServiceGiftFactory = urlServiceGiftFactory;
        this.giftRepo = giftRepo;
        this.orderRepo = orderRepo;
        this.giftRequestRepo = giftRequestRepo;
        this.restTemplate = restTemplate;
    }

    public Optional<OrderResponseDto> getOrder(String nameGift, String nameChild) {
        BehaviorRequestDto behaviorRequestDto = new BehaviorRequestDto();
        behaviorRequestDto.setNameChild(nameChild);

        String url = urlServiceBehavior + "/get-gift";
        BehaviorResponseDto body = restTemplate.postForObject(url, behaviorRequestDto, BehaviorResponseDto.class);

        Boolean isGreat = Optional.ofNullable(body)
                .map(BehaviorResponseDto::getGreat)
                .orElseThrow(() -> new RuntimeException("Response body is null"));

        if (isGreat) {
            OrderResponseDto responseDto = getOrderResponseDto(nameGift);
            return Optional.of(responseDto);
        } else {
            return Optional.empty();
        }
    }

    private OrderResponseDto getOrderResponseDto(String nameGift) {
        Gift gift = giftRepo.findGiftByName(nameGift);

        if (gift.getCount() > minCount) {
            Order order = new Order();
            gift.setCount(gift.getCount() - 1);
            giftRepo.save(gift);

            order.setGift(gift);
            order.setSum(1);
            order.setType(Order.Type.MINUS);
            orderRepo.save(order);

            return new OrderResponseDto(gift.getName(), 1, "Gift sent");
        } else {
            String uuid = UUID.randomUUID().toString();
            GiftRequest giftRequest = new GiftRequest();
            giftRequest.setUuidReq(uuid);
            giftRequest.setGift(gift);
            giftRequestRepo.save(giftRequest);

            GiftRequestDto giftRequestDto = new GiftRequestDto();

            giftRequestDto.setNameGift(nameGift);
            giftRequestDto.setSumGift(10);
            giftRequestDto.setUuid(uuid);

            String url = urlServiceGiftFactory + "/add-gift";
            GiftResponseDto body = restTemplate.postForObject(url, giftRequestDto, GiftResponseDto.class);

            return new OrderResponseDto(gift.getName(), 0, "Gift none");
        }
    }

    public GiftResponseDto addGift(String nameGift, Integer sumGift, String uuid) {
        Gift gift = giftRepo.findGiftByName(nameGift);
        GiftRequest giftRequest = giftRequestRepo.findByGiftAndUuidReq(gift, uuid);
        if (giftRequest.getUuidReq().equals(uuid)) {
            giftRequest.setUuidReq(null);
            giftRequestRepo.save(giftRequest);

            gift.setCount(gift.getCount() + sumGift);
            giftRepo.save(gift);

            Order order = new Order();
            order.setGift(gift);
            order.setSum(sumGift);
            order.setType(Order.Type.PLUS);
            orderRepo.save(order);

            GiftResponseDto giftResponseDto = new GiftResponseDto();
            giftResponseDto.setSuccess(true);
            return giftResponseDto;
        }

        GiftResponseDto giftResponseDto = new GiftResponseDto();
        giftResponseDto.setSuccess(false);
        return giftResponseDto;
    }
}