package com.mybible.mybible.transaction;

import com.mybible.mybible.category.CategoryService;
import com.mybible.mybible.subtransaction.SubtransactionService;
import com.mybible.mybible.type.Type;
import com.mybible.mybible.type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubtransactionService subtransactionService;

    @PostMapping("/add")
    public Transaction add(@RequestBody Transaction transaction){

        Transaction editedTransaction = new Transaction();
        Transaction submittedTransaction;

        //filter only date, description, transactionParent and subtransaction from transaction to editedTransaction
        editedTransaction.setDate(transaction.getDate());
        editedTransaction.setDescription(transaction.getDescription());
        editedTransaction = addOrUpdateTransactionParent(transaction, editedTransaction);
        editedTransaction.setSubtransactions(transaction.getSubtransactions());

        //add relation between new transaction (editedTransaction) and existing types
        editedTransaction = addOrUpdateTypes(transaction, editedTransaction);

        //add custom ID to new transaction
        String customId = transactionService.getLastTransactionByOrderByYear(editedTransaction.getDate());
        editedTransaction.setCustomId(customId);

        //submit editedTransaction
        submittedTransaction = transactionService.saveTransaction(editedTransaction);

        //print submittedTransaction
        //System.out.println(submittedTransaction);

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

        Transaction editedTransaction = new Transaction();
        Transaction submittedTransaction;

        //filter only id, customId, date, description, transactionParent and subtransactions from transaction to editedTransaction
        editedTransaction.setTransactionId(transactionId);
        editedTransaction.setCustomId(transaction.getCustomId());
        editedTransaction.setDate(transaction.getDate());
        editedTransaction.setDescription(transaction.getDescription());
        editedTransaction = addOrUpdateTransactionParent(transaction, editedTransaction);
        editedTransaction.setSubtransactions(transaction.getSubtransactions());

        //add relation between new transaction (editedTransaction) and existing types
        editedTransaction = addOrUpdateTypes(transaction, editedTransaction);

        //submit editedTransaction
        submittedTransaction = transactionService.updateTransaction(transactionId, editedTransaction);

        //print submittedTransaction
        System.out.println(submittedTransaction);

        return submittedTransaction;
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

    public Transaction addOrUpdateTypes(Transaction existingTransaction, Transaction newTransaction) {

        //go through every type on sent transaction
        for ( Type type : existingTransaction.getTypes() ) {

            //add or update type into sent transaction
            Type newType = typeService.getType(type.getTypeId());
            newTransaction.addType(newType);
        }

        return newTransaction;
    }

    public Transaction addOrUpdateTransactionParent(Transaction existingTransaction, Transaction newTransaction) {

        //check if sent transaction has a transactionParent and updates it if it has
        if ( existingTransaction.getTransactionParent() != null && existingTransaction.getTransactionParent().getTransactionId() != null) {

            Long transactionParentId = existingTransaction.getTransactionParent().getTransactionId();
            Transaction transactionParent = transactionService.getTransaction(transactionParentId);
            newTransaction.setTransactionParent(transactionParent);
        }

        return newTransaction;
    }

}