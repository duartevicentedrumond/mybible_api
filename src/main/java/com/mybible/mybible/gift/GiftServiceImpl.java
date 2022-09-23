package com.mybible.mybible.gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GiftServiceImpl implements GiftService {

    @Autowired
    private GiftRepository giftRepository;

    @Override
    public Gift saveGift(Gift gift) {
        return giftRepository.save(gift);
    }

    @Override
    public List<Gift> getAllGifts() {
        return giftRepository.findAllByOrderByIdDesc();
    }

    @Override
    public Optional<Gift> getGift(Long giftId) {
        return giftRepository.findByGiftId(giftId);
    }

    @Override
    public Gift updateGift(Long giftId, Gift gift) {
        return giftRepository.save(gift);
    }

    @Override
    public void deleteGift(Long giftId) {
        giftRepository.deleteById(giftId);
    }

}
