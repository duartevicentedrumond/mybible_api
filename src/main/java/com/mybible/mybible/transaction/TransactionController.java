package com.mybible.mybible.transaction;

import com.mybible.mybible.category.CategoryService;
import com.mybible.mybible.subtransaction.Subtransaction;
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

        //filter only date, description and transactionParent from transaction to editedTransaction
        editedTransaction.setDate(transaction.getDate());
        editedTransaction.setDescription(transaction.getDescription());
        editedTransaction = addOrUpdateTransactionParent(transaction, editedTransaction);

        //add relation between new transaction (editedTransaction) and existing types
        editedTransaction = addOrUpdateTypes(transaction, editedTransaction);

        //add custom ID to new transaction
        String customId = transactionService.getLastTransactionByOrderByYear(editedTransaction.getDate());
        editedTransaction.setCustomId(customId);

        //submit editedTransaction
        submittedTransaction = transactionService.saveTransaction(editedTransaction);

        //submit new subtransactions
        addSubtransactions(transaction, submittedTransaction);

        //print submittedTransaction
        System.out.println(submittedTransaction);

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

        //filter only id, date, description and transactionParent from transaction to editedTransaction
        editedTransaction.setTransactionId(transactionId);
        editedTransaction.setDate(transaction.getDate());
        editedTransaction.setDescription(transaction.getDescription());
        editedTransaction = addOrUpdateTransactionParent(transaction, editedTransaction);

        //add relation between new transaction (editedTransaction) and existing types
        editedTransaction = addOrUpdateTypes(transaction, editedTransaction);

        //submit editedTransaction
        submittedTransaction = transactionService.updateTransaction(transactionId, editedTransaction);

        //update related subtransactions
        updateSubtransactions(transaction, submittedTransaction);

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
        if ( existingTransaction.getTransactionParent() != null ) {

            Long transactionParentId = existingTransaction.getTransactionParent().getTransactionId();
            Transaction transactionParent = transactionService.getTransaction(transactionParentId);
            newTransaction.setTransactionParent(transactionParent);
        }

        return newTransaction;
    }

    public void addSubtransactions(Transaction existingTransaction, Transaction newTransaction) {

        //go through every subtransaction on sent transaction
        for ( Subtransaction subtransaction : existingTransaction.getSubtransactions() ) {

            //add new subtransaction into database
            subtransaction.setTransaction(newTransaction);
            Subtransaction newSubtransaction = subtransactionService.saveSubtransaction(subtransaction);
        }

    }

    public void updateSubtransactions(Transaction existingTransaction, Transaction newTransaction) {

        //go through every subtransaction on sent transaction
        for ( Subtransaction subtransaction : existingTransaction.getSubtransactions() ) {

            //get subtransactionId
            Long subtransactionId = subtransaction.getSubtransactionId();

            //check if subtransaction already exists in database and updates it
            if ( subtransactionId != null ) {

                subtransaction.setTransaction(newTransaction);
                Subtransaction newSubtransaction = subtransactionService.updateSubtransaction(subtransactionId, subtransaction);

            } else { //check if subtransaction doesn't yet exists in database and creates it

                subtransaction.setTransaction(newTransaction);
                Subtransaction newSubtransaction = subtransactionService.saveSubtransaction(subtransaction);

            }

        }

    }
}