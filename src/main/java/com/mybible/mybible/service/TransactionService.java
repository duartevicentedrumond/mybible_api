package com.mybible.mybible.service;

import com.mybible.mybible.model.Transaction;

import java.util.List;

public interface TransactionService {

    public Transaction saveTransaction(Transaction transaction);
    public List<Transaction> getAllTransactions();
}
