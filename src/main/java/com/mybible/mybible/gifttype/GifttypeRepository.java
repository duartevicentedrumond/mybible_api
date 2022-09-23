package com.mybible.mybible.gifttype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GifttypeRepository extends JpaRepository<Gifttype, Long> {
    List<Gifttype> findAllByOrderByGifttypeId();
    Gifttype findByGifttypeId(Long gifttypeId);
}
