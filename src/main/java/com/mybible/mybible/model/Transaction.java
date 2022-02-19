package com.mybible.mybible.model;

import com.fasterxml.jackson.annotation.JsonFormat;

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
            name = "id",
            updatable = false,
            unique = true,
            insertable = false
    )
    private Long id;
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
    @Column(
            name = "category",
            nullable = false,
            precision = 2
    )
    private Integer category;
    @Column(
            name = "type",
            nullable = false
    )
    private Integer type;

    public Transaction() {
    }

    public Transaction(String description,
                       Date date,
                       Double amount,
                       Integer category,
                       Integer type) {
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", category=" + category +
                ", type=" + type +
                '}';
    }
}
