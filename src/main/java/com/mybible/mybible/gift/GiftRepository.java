package com.mybible.mybible.gift;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {

    Optional<Gift> findByGiftId(Long giftId);
    @Query(
            value = "SELECT * FROM \"gift\" " +
                    "ORDER BY gift_id DESC",
            nativeQuery = true
    )
    List<Gift> findAllByOrderByIdDesc();
}
