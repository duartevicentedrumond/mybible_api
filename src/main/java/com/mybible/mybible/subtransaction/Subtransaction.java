package com.mybible.mybible.subtransaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mybible.mybible.category.Category;
import com.mybible.mybible.person.Person;
import com.mybible.mybible.transaction.Transaction;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Subtransaction")
public class Subtransaction {

    @Id
    @SequenceGenerator(
            name = "subtransaction_sequence",
            sequenceName = "subtransaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
          strategy = SEQUENCE,
          generator = "subtransaction_sequence"
    )
    @Column(
            name = "subtransaction_id",
            updatable = false,
            unique = true
    )
    private Long subtransactionId;

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
    @JoinColumn(name = "person_id")
    @JsonIgnoreProperties({
            "nickname",
            "fullName",
            "birthday"
    })
    private Person person;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    @JsonIgnoreProperties({
            "amount",
            "date",
            "description",
            "type",
            "transactionParent",
            "transactionChild",
            "subtransaction"
    })
    private Transaction transaction;

    public Subtransaction() {
    }

    public Subtransaction(Long subtransactionId, Double amount, Category category, Person person, Transaction transaction) {
        this.subtransactionId = subtransactionId;
        this.amount = amount;
        this.category = category;
        this.person = person;
        this.transaction = transaction;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getSubtransactionId() {
        return subtransactionId;
    }

    public void setSubtransactionId(Long subtransactionId) {
        this.subtransactionId = subtransactionId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "Subvalue{" +
                "subtransactionId=" + subtransactionId +
                ", amount=" + amount +
                ", category=" + category +
                ", person=" + person +
                ", transaction=" + transaction +
                '}';
    }
}
