package com.mybible.mybible.transaction;

import com.mybible.mybible.category.CategoryService;
import com.mybible.mybible.subtransaction.Subtransaction;
import com.mybible.mybible.subtransaction.SubtransactionService;
import com.mybible.mybible.type.Type;
import com.mybible.mybible.type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private Environment env;

    @PostMapping("/add")
    public Transaction add(@RequestBody Transaction transaction){

        Transaction editedTransaction = new Transaction();
        Transaction submittedTransaction;

        //filter only date, description and transactionParent from transaction to editedTransaction
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

        Set<Subtransaction> oldSubtransactions = getTransaction(transactionId).getSubtransactions();

        //filter only id, customId, date, description and transactionParent from transaction to editedTransaction
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
        //System.out.println(submittedTransaction);

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
            new_category.setSum(((BigDecimal) category[1]).doubleValue());
            new_category.setActive((Boolean) category[2]);
            listSumByCategory.add(new_category);
        });

        return listSumByCategory;

    }

    @GetMapping("/getSumByMonth")
    public List<SumByMonth> getSumByMonth(){

        List<SumByMonth> listSumByMonth = new ArrayList<>();
        List<Object[]> sumByMonth = transactionService.getSumByMonth();

        sumByMonth.forEach(month ->{

            SumByMonth new_month = new SumByMonth();

            new_month.setYear((Double) month[0]);
            new_month.setMonth((Double) month[1]);
            new_month.setSum(((BigDecimal) month[2]).doubleValue());
            listSumByMonth.add(new_month);
        });

        return listSumByMonth;

    }

    @GetMapping("/getSumByDebt")
    public List<SumByDebt> getSumByDebt(){

        List<SumByDebt> listSumByDebt = new ArrayList<>();
        List<Object[]> sumByDebt = transactionService.getSumByDebt();

        sumByDebt.forEach(debtor ->{

            SumByDebt new_debtor = new SumByDebt();

            new_debtor.setPerson_id(((BigInteger) debtor[0]).longValue());
            new_debtor.setNickname((String) debtor[1]);
            new_debtor.setDebt(((BigDecimal) debtor[2]).doubleValue());
            listSumByDebt.add(new_debtor);
        });

        return listSumByDebt;

    }

    @GetMapping("/getSubtransactionByTransaction")
    public List<SubtransactionByTransaction> getSubtransactionByTransaction(){

        List<SubtransactionByTransaction> listSubtransactionByTransaction = new ArrayList<>();
        List<Object[]> subtransactionByTransaction = transactionService.getSubtransactionByTransaction();

        subtransactionByTransaction.forEach(subtransaction ->{

            SubtransactionByTransaction new_subtransaction = new SubtransactionByTransaction();

            new_subtransaction.setSubtransactionId(((BigInteger) subtransaction[0]).longValue());
            new_subtransaction.setAmount((Double) subtransaction[1]);
            new_subtransaction.setCustomId((String) subtransaction[2]);
            new_subtransaction.setDate((Date) subtransaction[3]);
            new_subtransaction.setDescription((String) subtransaction[4]);
            listSubtransactionByTransaction.add(new_subtransaction);
        });

        return listSubtransactionByTransaction;

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