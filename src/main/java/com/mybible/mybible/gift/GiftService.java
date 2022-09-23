package com.mybible.mybible.gift;

import java.util.List;
import java.util.Optional;

public interface GiftService {
    Gift saveGift(Gift gift);
    List<Gift> getAllGifts();
    Optional<Gift> getGift(Long giftId);
    Gift updateGift(Long giftId, Gift gift);
    void deleteGift(Long giftId);
    List<Object[]> getGiftByPerson();
}
