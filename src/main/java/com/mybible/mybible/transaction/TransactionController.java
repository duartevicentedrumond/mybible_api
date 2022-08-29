package com.mybible.mybible.transaction;

import com.mybible.mybible.category.CategoryService;
import com.mybible.mybible.subtransaction.Subtransaction;
import com.mybible.mybible.subtransaction.SubtransactionService;
import com.mybible.mybible.type.Type;
import com.mybible.mybible.type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
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
            new_category.setSum((Double) category[1]);
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
            new_month.setSum((Double) month[2]);
            listSumByMonth.add(new_month);
        });

        return listSumByMonth;

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

    @GetMapping("/backup")
    public String backupDatabase() throws IOException {

        Process process;

        //define execution directory
        String directory = "cmd /c cd C:\\Program Files\\PostgreSQL\\13\\bin";

        //define database name from variable spring.datasource.url in application.properties file
        String databaseUrl = env.getProperty("spring.datasource.url");
        String[] arrOfStr = databaseUrl.split("/");
        String database = arrOfStr[arrOfStr.length-1];

        //define directory to store the backup
        String backupDirectory = "C:\\Users\\duarte\\Documents\\me\\my_backup\\";
        //define backup file name
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY_MM_dd");
        String date = formatter.format(new Date());

        String backupFileName = database + "_" + date + "_" + ".sql";

        //define backup order
        String backupPostgreSQL = "pg_dump -U postgres -h localhost -p 5433 -d " + database + " > " + backupDirectory + backupFileName;

        //define entire command
        String command = directory + " && " + backupPostgreSQL;

        String message = "";

        try {
            process = Runtime.getRuntime().exec(command);

            process.waitFor();
            BufferedReader reader=new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            String line;

            message = database + " backup (" + backupFileName + ") executed successfully!";

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return message;
    }

}