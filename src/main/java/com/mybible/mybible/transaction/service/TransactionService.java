package com.mybible.mybible.transaction.service;

import com.mybible.mybible.transaction.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    public Transaction saveTransaction(Transaction transaction);
    public List<Transaction> getAllTransactions();
    public Optional<Transaction> getTransaction(Long transactionId);
    public Transaction updateTransaction(Long transactionId, Transaction transaction);
}
