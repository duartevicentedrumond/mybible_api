package com.mybible.mybible.transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    Transaction saveTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    Optional<Transaction> getTransaction(Long transactionId);
    Transaction updateTransaction(Long transactionId, Transaction transaction);
    List<Object[]> getSumByCategory();
    void deleteTransaction(Long transactionId);
}
