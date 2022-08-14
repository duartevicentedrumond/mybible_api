package com.mybible.mybible.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(
            value = "SELECT " +
                        "transaction_id, " +
                        "date," +
                        "transaction.description," +
                        "SUM(subtransaction.amount) as \"total_amount\"," +
                        "transaction_parent " +
                    "FROM  \"transaction\" " +
                    "JOIN \"subtransaction\" USING (transaction_id) " +
                    "WHERE  transaction_id = ?1 " +
                    "GROUP BY \"transaction\".\"transaction_id\" ",
            nativeQuery = true
    )
    Transaction findByTransactionId(Long transactionId);

    @Query(
            value = "SELECT " +
                        "category.description, " +
                        "SUM(amount) AS \"total\" " +
                    "FROM \"subtransaction\" " +
                    "JOIN category ON \"subtransaction\".\"category_id\" = category.category_id " +
                    "GROUP BY category.category_id " +
                    "ORDER BY category.category_id",
            nativeQuery = true
    )
    List<Object[]> getSumByCategory();

    @Query(
            value = "SELECT " +
                        "transaction_id, " +
                        "date," +
                        "description," +
                        "SUM(subtransaction.amount) as \"total_amount\"," +
                        "transaction_parent " +
                    "FROM  \"transaction\" " +
                    "JOIN \"subtransaction\" USING (transaction_id) " +
                    "GROUP BY \"transaction\".\"transaction_id\" " +
                    "ORDER BY date DESC, transaction_id DESC",
            nativeQuery = true
    )
    List<Transaction> findAllByOrderByDateAndIdDesc();
}
