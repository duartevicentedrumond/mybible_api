package com.mybible.mybible.type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mybible.mybible.transaction.Transaction;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Type")
public class Type {

    @Id
    @SequenceGenerator(
            name = "type_sequence",
            sequenceName = "type_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "type_sequence"
    )
    @Column(
            name = "type_id",
            unique = true
    )
    private Long typeId;

    @Column(
            name = "description",
            updatable = true
    )
    private String description;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            mappedBy = "types"
    )
    @JsonIgnore
    private Set<Transaction> transactions = new HashSet<>();

    public Type() {
    }

    public Type(Long typeId, String description) {
        this.typeId = typeId;
        this.description = description;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Type{" +
                "typeId=" + typeId +
                ", description='" + description + '\'' +
                '}';
    }
}

