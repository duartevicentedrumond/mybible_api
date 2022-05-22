package com.mybible.mybible.transaction;

import com.fasterxml.jackson.annotation.*;
import com.mybible.mybible.subtransaction.Subtransaction;
import com.mybible.mybible.type.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
            unique = true
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

    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @JsonIgnoreProperties({"description"})
    private Type type;

    @ManyToOne
    @JoinColumn(
            name="transactionParent",
            referencedColumnName = "transaction_id"
    )
    @JsonIgnoreProperties({
            "date",
            "description",
            "totalAmount",
            "type",
            "transactionParent",
            "transactionChild",
            "subvalue"
    })
    private Transaction transactionParent;

    @OneToMany(
            mappedBy = "transactionParent"
    )
    @JsonIgnoreProperties({
            "date",
            "description",
            "totalAmount",
            "type",
            "transactionParent",
            "transactionChild",
            "subvalue"
    })
    private List<Transaction> transactionChild;

    @OneToMany
    @JoinColumn(name = "transaction_id")
    private List<Subtransaction> subtransactions;

    public Transaction() {
    }

    public Transaction(Long transactionId, String description, Date date, Double totalAmount, Type type, Transaction transactionParent, List<Transaction> transactionChild, List<Subtransaction> subtransactions) {
        this.transactionId = transactionId;
        this.description = description;
        this.date = date;
        this.totalAmount = totalAmount;
        this.type = type;
        this.transactionParent = transactionParent;
        this.transactionChild = transactionChild;
        this.subtransactions = subtransactions;
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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Transaction getTransactionParent() {
        return transactionParent;
    }

    public void setTransactionParent(Transaction transactionParent) {
        this.transactionParent = transactionParent;
    }

    public List<Transaction> getTransactionChild() {
        return transactionChild;
    }

    public void setTransactionChild(List<Transaction> transactionChild) {
        this.transactionChild = transactionChild;
    }

    public List<Subtransaction> getSubtransactions() {
        return subtransactions;
    }

    public void setSubtransactions(List<Subtransaction> subtransactions) {
        this.subtransactions = subtransactions;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", totalAmount=" + totalAmount +
                ", type=" + type +
                ", transactionParent=" + transactionParent +
                ", transactionChild=" + transactionChild +
                ", subtransactions=" + subtransactions +
                '}';
    }
}
