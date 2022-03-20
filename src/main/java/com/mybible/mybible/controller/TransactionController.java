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
    public String add(@RequestBody Transaction transaction){
        transactionService.saveTransaction(transaction);
        return "New transaction added";
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
    public String updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction){
        transactionService.updateTransaction(id, transaction);
        return "Transaction updated";
    }

}