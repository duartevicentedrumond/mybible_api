package com.mybible.mybible.transaction;

import com.mybible.mybible.category.CategoryService;
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

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Transaction add(@RequestBody Transaction transaction){
        return transactionService.saveTransaction(transaction);

    }

    @GetMapping("/getAll")
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    @RequestMapping("/{transactionId}")
    public Optional<Transaction> getTransaction(@PathVariable Long transactionId){
        return transactionService.getTransaction(transactionId);
    }

    @PutMapping("/update/{transactionId}")
    public Transaction updateTransaction(@PathVariable Long transactionId, @RequestBody Transaction transaction){

        Long new_category_id = Long.valueOf(transaction.getCategory().getCategoryId());
        transaction.getCategory().setCategoryId(new_category_id);

        return transactionService.updateTransaction(transactionId, transaction);
    }

}