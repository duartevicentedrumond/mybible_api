package com.mybible.mybible.transaction;

import com.mybible.mybible.subtransaction.Subtransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {

        List<Transaction> transactions = transactionRepository.findAllByOrderByDateAndIdDesc();

        for (Transaction transaction : transactions) {

            Double total_amount = 0.00;
            for (Subtransaction subtransaction : transaction.getSubtransactions()) {
                total_amount += subtransaction.getAmount();
            }

            transaction.setTotalAmount(total_amount);

        }

        return transactions;
    }

    @Override
    public Transaction getTransaction(Long transactionId) {
        return transactionRepository.findByTransactionId(transactionId);
    }

    @Override
    public Transaction updateTransaction(Long transactionId, Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Object[]> getSumByCategory() {
        return transactionRepository.getSumByCategory();
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    @Override
    public String getLastTransactionByOrderByYear(Date date) {

        //create calendar instance
        Calendar calendar = new GregorianCalendar();

        //convert date from Date to Calendar and get year
        calendar.setTime(date);
        String year = String.valueOf(calendar.get(Calendar.YEAR));

        //retrieve last transaction from the required year
        Transaction lastTransaction = transactionRepository.findTopByOrderByIdDesc(year);

        String serialNumberString;

        //check if there is any transaction from the required year
        if (lastTransaction != null) {

            //get custom ID from last entry from the required year
            String lastCustomId = lastTransaction.getCustomId();

            //get the serial number from last custom ID and increments to it
            Long serialNumber = Long.parseLong(lastCustomId.substring(4)) + 1;
            serialNumberString = String.format("%04d", serialNumber);

        } else { //if there is no last transaction from the required year

            //set serial number to "0001"
            serialNumberString = "0001";
        }

        //build new custom ID
        String customId = year + serialNumberString;

        return customId;
    }

    @Override
    public List<Object[]> getSumByMonth() {
        return transactionRepository.getSumByMonth();
    }

    @Override
    public List<Object[]> getSumByDebt() {
        return transactionRepository.getSumByDebt();
    }
}
