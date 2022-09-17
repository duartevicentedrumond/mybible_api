package com.mybible.mybible.type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mybible.mybible.transaction.Transaction;
import org.hibernate.annotations.ColumnDefault;

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
            name = "description"
    )
    private String description;

    @ColumnDefault("true")
    @Column(
            name = "status"
    )
    private Boolean status;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            mappedBy = "types"
    )
    @JsonIgnore
    private Set<Transaction> transactions = new HashSet<>();

    public Type() {
    }

    public Type(Long typeId, String description, boolean status) {
        this.typeId = typeId;
        this.description = description;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
                ", status=" + status +
                '}';
    }
}

