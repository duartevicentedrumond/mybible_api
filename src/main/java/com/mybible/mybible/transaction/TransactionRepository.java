package com.mybible.mybible.transaction;

import com.mybible.mybible.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByOrderByDateDesc();
    Optional<Transaction> findByTransactionId(Long transactionId);
}