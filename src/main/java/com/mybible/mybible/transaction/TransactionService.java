package com.mybible.mybible.transaction;

import java.util.Date;
import java.util.List;

public interface TransactionService {

    Transaction saveTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    Transaction getTransaction(Long transactionId);
    Transaction updateTransaction(Long transactionId, Transaction transaction);
    List<Object[]> getSumByCategory();
    void deleteTransaction(Long transactionId);
    String getLastTransactionByOrderByYear(Date date);
    List<Object[]> getSumByMonth();
}
