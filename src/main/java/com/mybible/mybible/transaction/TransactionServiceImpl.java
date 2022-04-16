package com.mybible.mybible.transaction;

import com.mybible.mybible.transaction.Transaction;
import com.mybible.mybible.transaction.TransactionRepository;
import com.mybible.mybible.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAllByOrderByDateDesc();
    }

    @Override
    public Optional<Transaction> getTransaction(Long transactionId) {
        return transactionRepository.findByTransactionId(transactionId);
    }

    @Override
    public Transaction updateTransaction(Long transactionId, Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
