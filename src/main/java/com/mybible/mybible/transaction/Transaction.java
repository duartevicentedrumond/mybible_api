package com.mybible.mybible.transaction;

import com.fasterxml.jackson.annotation.*;
import com.mybible.mybible.Type.Type;
import com.mybible.mybible.category.Category;
import com.mybible.mybible.person.Person;

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

    @Column(
            name = "amount",
            nullable = false
    )
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"description"})
    private Category category;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @JsonIgnoreProperties({"description"})
    private Type type;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonIgnoreProperties({
            "nickname",
            "full_name",
            "birthday"
    })
    private Person person;

    @ManyToOne
    @JoinColumn(
            name="transactionParent",
            referencedColumnName = "transaction_id"
    )
    @JsonIgnoreProperties({
            "amount",
            "date",
            "description",
            "category",
            "type",
            "person",
            "transactionParent",
            "transactionChild"
    })
    private Transaction transactionParent;

    @OneToMany(
            mappedBy = "transactionParent"
    )
    @JsonIgnoreProperties({
            "amount",
            "date",
            "description",
            "category",
            "type",
            "person",
            "transactionParent",
            "transactionChild"
    })
    private List<Transaction> transactionChild;

    public Transaction() {
    }

    public Transaction(Long transactionId, String description, Date date, Double amount, Category category, Type type, Person person, Transaction transactionParent, List<Transaction> transactionChild) {
        this.transactionId = transactionId;
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.person = person;
        this.transactionParent = transactionParent;
        this.transactionChild = transactionChild;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", category=" + category +
                ", type=" + type +
                ", person=" + person +
                ", transactionParent=" + transactionParent +
                ", transactionChild=" + transactionChild +
                '}';
    }
}
