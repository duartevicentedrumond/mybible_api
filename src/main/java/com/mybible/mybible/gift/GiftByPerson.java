package com.mybible.mybible.gift;

import java.util.Date;

public class GiftByPerson {

    private Long giftId;
    private Date date;
    private String description;
    private Boolean from;
    private Double value;
    private String nickname;
    private String gifttypeDescription;

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFrom() {
        return from;
    }

    public void setFrom(Boolean from) {
        this.from = from;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGifttypeDescription() {
        return gifttypeDescription;
    }

    public void setGifttypeDescription(String gifttypeDescription) {
        this.gifttypeDescription = gifttypeDescription;
    }
}
