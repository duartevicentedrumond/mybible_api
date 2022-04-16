package com.mybible.mybible.transaction;

import com.fasterxml.jackson.annotation.*;
import com.mybible.mybible.Type.Type;
import com.mybible.mybible.category.Category;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.*;

@Entity(name = "Transaction")
public class Transaction {

    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
          strategy = SEQUENCE,
          generator = "transaction_sequence"
    )
    @Column(
            name = "transaction_id",
            updatable = false,
            unique = true,
            insertable = false
    )
    private Long transactionId;

    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;

    @Column(
            name = "date",
            nullable = false
    )
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @Column(
            name = "amount",
            nullable = false
    )
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    public Transaction() {
    }

    public Transaction(String description,
                       Date date,
                       Double amount,
                       Category category,
                       Type type) {
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.type = type;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transaction_id=" + transactionId +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", category=" + category +
                ", type=" + type +
                '}';
    }
}
