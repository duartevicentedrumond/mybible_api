package com.mybible.mybible.subtransaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubtransactionRepository extends JpaRepository<Subtransaction, Long> {

    Optional<Subtransaction> findBySubtransactionId(Long subtransactionId);

    @Query(
            value = "SELECT * FROM \"subtransaction\" " +
                    "ORDER BY subtransaction_id DESC",
            nativeQuery = true
    )
    List<Subtransaction> findAllByOrderByIdDesc();
}
