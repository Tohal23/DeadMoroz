package org.project.moroz.model;

public class OrderResponseDto {
    private String giftName;
    private String text;

    public OrderResponseDto(String giftName, Integer sum, String text) {
        this.giftName = giftName;
        this.text = text;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
