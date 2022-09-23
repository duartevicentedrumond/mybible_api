package com.mybible.mybible.gift;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(
            value = "SELECT " +
                        "gift.gift_id, " +
                        "gift.date, " +
                        "gift.description, " +
                        "gift.from, " +
                        "gift.value, " +
                        "person.nickname, " +
                        "gifttype.description AS gifttype " +
                    "FROM gift " +
                    "JOIN gift_people ON gift.gift_id = gift_people.gift_id " +
                    "JOIN person ON gift_people.person_id = person.person_id " +
                    "JOIN gifttype ON gift.gifttype_id = gifttype.gifttype_id " +
                    "WHERE person.person_id = :personId",
            nativeQuery = true
    )
    List<Object[]> getGiftByPerson(@Param("personId") Long personId);
}
