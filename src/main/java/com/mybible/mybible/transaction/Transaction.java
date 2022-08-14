package com.mybible.mybible.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mybible.mybible.subtransaction.Subtransaction;
import com.mybible.mybible.type.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

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
            name = "custom_id"
    )
    private String customId;

    @Column(
            name = "description",
            nullable = false
    )
    private String description;

    @Column(
            name = "date",
            nullable = false
    )
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    private Double totalAmount;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinTable(
            name = "transaction_types",
            joinColumns = { @JoinColumn(name = "transaction_id") },
            inverseJoinColumns = { @JoinColumn(name = "type_id") }
    )
    private Set<Type> types = new HashSet<>();

    @ManyToOne
    @JoinColumn(
            name="transaction_parent",
            referencedColumnName = "transaction_id",
            nullable = true
    )
    @JsonIgnoreProperties({
            "date",
            "customId",
            "description",
            "totalAmount",
            "types",
            "transactionParent",
            "transactionChildren",
            "subtransactions"
    })
    private Transaction transactionParent;

    @OneToMany( mappedBy = "transactionParent" )
    @JsonIgnoreProperties({
            "date",
            "customId",
            "description",
            "totalAmount",
            "types",
            "transactionParent",
            "transactionChildren",
            "subtransactions"
    })
    private List<Transaction> transactionChildren;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "transaction"
    )
    @JsonIgnoreProperties({
            "transaction"
    })
    private Set<Subtransaction> subtransactions = new HashSet<>();

    public Transaction() {
    }

    public Transaction(Long transactionId, String customId, String description, Date date, Double totalAmount) {
        this.transactionId = transactionId;
        this.customId = customId;
        this.description = description;
        this.date = date;
        this.totalAmount = totalAmount;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
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

    public Set<Type> getTypes() {
        return types;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
    }

    public void addType(Type type) {
        types.add(type);
    }

    public Transaction getTransactionParent() {
        return transactionParent;
    }

    public void setTransactionParent(Transaction transactionParent) {
        this.transactionParent = transactionParent;
    }

    public List<Transaction> getTransactionChildren() {
        return transactionChildren;
    }

    public void setTransactionChildren(List<Transaction> transactionChildren) {
        this.transactionChildren = transactionChildren;
    }

    public Set<Subtransaction> getSubtransactions() {
        return subtransactions;
    }

    public void setSubtransactions(Set<Subtransaction> subtransactions) {
        this.subtransactions = subtransactions;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                    "transactionId=" + transactionId +
                    ", customId='" + customId + '\'' +
                    ", description='" + description + '\'' +
                    ", date=" + date +
                    ", totalAmount=" + totalAmount +
                    ", types=" + types +
                    ", transactionParent=" + transactionParent +
                    ", transactionChildren=" + transactionChildren +
                    ", subtransactions=" + subtransactions +
                '}';
    }
}