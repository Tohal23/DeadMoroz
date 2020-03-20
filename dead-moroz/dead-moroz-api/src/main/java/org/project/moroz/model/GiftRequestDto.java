package org.project.moroz.model;

public class GiftRequestDto {
    private String nameGift;
    private Integer sumGift;
    private String uuid;

    public GiftRequestDto() {
    }

    public String getNameGift() {
        return nameGift;
    }

    public void setNameGift(String nameGift) {
        this.nameGift = nameGift;
    }

    public Integer getSumGift() {
        return sumGift;
    }

    public void setSumGift(Integer sumGift) {
        this.sumGift = sumGift;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
