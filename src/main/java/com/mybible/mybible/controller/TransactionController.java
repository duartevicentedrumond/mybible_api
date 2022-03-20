package com.mybible.mybible.controller;

import com.mybible.mybible.model.Transaction;
import com.mybible.mybible.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/add")
    public Transaction add(@RequestBody Transaction transaction){
        return transactionService.saveTransaction(transaction);

    }

    @GetMapping("/getAll")
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    @RequestMapping("/{id}")
    public Optional<Transaction> getTransaction(@PathVariable Long id){
        return transactionService.getTransaction(id);
    }

    @PutMapping("/update/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction){
        return transactionService.updateTransaction(id, transaction);
    }

}