package com.mybible.mybible.gifttype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GifttypeServiceImpl implements GifttypeService {

    @Autowired
    private GifttypeRepository gifttypeRepository;

    @Override
    public Gifttype saveGifttype(Gifttype gifttype) {
        return gifttypeRepository.save(gifttype);
    }

    @Override
    public List<Gifttype> getAllGifttypes() {
        return gifttypeRepository.findAllByOrderByGifttypeId();
    }

    @Override
    public Gifttype getGifttype(Long gifttypeId) {
        return gifttypeRepository.findByGifttypeId(gifttypeId);
    }

    @Override
    public Gifttype updateGifttype(Long gifttypeId, Gifttype gifttype) {
        return gifttypeRepository.save(gifttype);
    }

    @Override
    public void deleteGifttype(Long gifttypeId) {
        gifttypeRepository.deleteById(gifttypeId);
    }

}
