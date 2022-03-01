package com.mybible.mybible.service;

import com.mybible.mybible.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    public Transaction saveTransaction(Transaction transaction);
    public List<Transaction> getAllTransactions();
    public Optional<Transaction> getTransaction(Long id);
}
