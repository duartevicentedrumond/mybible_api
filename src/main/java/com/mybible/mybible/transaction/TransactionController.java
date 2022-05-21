package com.mybible.mybible.transaction;

import com.mybible.mybible.category.CategoryService;
import com.mybible.mybible.subtransaction.SubtransactionService;
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

    @Autowired
    private SubtransactionService subtransactionService;

    @PostMapping("/add")
    public Transaction add(@RequestBody Transaction transaction){

        Transaction editedTransaction = new Transaction();
        editedTransaction.setDescription(transaction.getDescription());
        editedTransaction.setDate(transaction.getDate());
        editedTransaction.setAmount(transaction.getAmount());
        editedTransaction.setType(transaction.getType());
        editedTransaction.setTransactionParent(transaction.getTransactionParent());

        Transaction submittedTransaction = transactionService.saveTransaction(editedTransaction);

        transaction.getSubtransaction().forEach(subtransaction -> {
            subtransaction.setTransaction(submittedTransaction);
            subtransactionService.saveSubtransaction(subtransaction);
        });

        return submittedTransaction;
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

    @DeleteMapping("/delete/{transactionId}")
    public void deleteTransaction(@PathVariable Long transactionId){
        transactionService.deleteTransaction(transactionId);
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