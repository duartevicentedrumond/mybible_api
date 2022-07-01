package com.mybible.mybible.transaction;

import com.mybible.mybible.category.CategoryService;
import com.mybible.mybible.subtransaction.SubtransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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

        System.out.println(transaction);

        Transaction editedTransaction = new Transaction();
        editedTransaction.setDescription(transaction.getDescription());
        editedTransaction.setDate(transaction.getDate());
        editedTransaction.setType(transaction.getType());

        if (transaction.getTransactionParent().getTransactionId() != null) {
            editedTransaction.setTransactionParent(transaction.getTransactionParent());
        }

        Transaction submittedTransaction = transactionService.saveTransaction(editedTransaction);

        transaction.getSubtransactions().forEach(subtransaction -> {
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
    public Transaction getTransaction(@PathVariable Long transactionId){
        return transactionService.getTransaction(transactionId);
    }

    @PutMapping("/update/{transactionId}")
    public Transaction updateTransaction(@PathVariable Long transactionId, @RequestBody Transaction transaction){

        Transaction oldTransaction = transactionService.getTransaction(transactionId);

        List<Long> oldSubtransactionsIdsList = new ArrayList<>();
        oldTransaction.getSubtransactions().forEach(oldsubtransaction -> {
            oldSubtransactionsIdsList.add(oldsubtransaction.getSubtransactionId());
        });

        List<Long> newSubtransactionsIdsList = new ArrayList<>();
        transaction.getSubtransactions().forEach(subtransaction -> {

            Long subtransactionId = subtransaction.getSubtransactionId();

            if (subtransactionId != null) {
                subtransaction.setTransaction(transaction);
                subtransactionService.saveSubtransaction(subtransaction);
                newSubtransactionsIdsList.add(subtransactionId);
            } else {
                subtransactionService.updateSubtransaction(subtransactionId, subtransaction);
            }
        });

        oldSubtransactionsIdsList.removeAll(newSubtransactionsIdsList);

        oldSubtransactionsIdsList.forEach(oldSubtransactionId ->{
            subtransactionService.deleteSubtransaction(oldSubtransactionId);
        });

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