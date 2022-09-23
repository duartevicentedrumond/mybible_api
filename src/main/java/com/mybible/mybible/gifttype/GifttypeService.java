package com.mybible.mybible.gifttype;

import java.util.List;

public interface GifttypeService {

    Gifttype saveGifttype(Gifttype gifttype);
    Gifttype getGifttype(Long gifttypeId);
    Gifttype updateGifttype(Long gifttypeId, Gifttype gifttype);
    List<Gifttype> getAllGifttypes();
    void deleteGifttype(Long typeId);
}
