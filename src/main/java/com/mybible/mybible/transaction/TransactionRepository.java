package com.mybible.mybible.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByTransactionId(Long transactionId);

    @Query(
            value = "SELECT category.description, SUM(amount) AS \"total\" FROM \"transaction\" " +
                    "JOIN category ON \"transaction\".\"category_id\" = category.category_id " +
                    "GROUP BY category.category_id " +
                    "ORDER BY category.category_id",
            nativeQuery = true
    )
    List<Object[]> getSumByCategory();

    @Query(
            value = "SELECT * FROM \"transaction\" " +
                    "ORDER BY date DESC, transaction_id DESC",
            nativeQuery = true
    )
    List<Transaction> findAllByOrderByDateAndIdDesc();
}
