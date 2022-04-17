package com.mybible.mybible.transaction;

import com.mybible.mybible.Type.Type;
import com.mybible.mybible.category.Category;
import com.mybible.mybible.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        return transactionService.updateTransaction(transactionId, transaction);
    }

    @GetMapping("/getSumByCategory")
    public List<SumByCategory> getSumByCategory(){

        List<SumByCategory> listSumByCategory = new ArrayList<>();
        List<Object[]> sumByCategory = transactionService.getSumByCategory();

        sumByCategory.forEach(category ->{
            SumByCategory new_category = new SumByCategory();
            new_category.setCategory(category[0].toString());
            new_category.setSum((Double) category[1]);
            listSumByCategory.add(new_category);
        });

        return listSumByCategory;

    }
}