package com.mybible.mybible.Type;

import javax.persistence.*;

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
            columnDefinition = "TEXT",
            updatable = true
    )
    private String description;

    public Type() {
    }

    public Type(String description) {
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

    @Override
    public String toString() {
        return "Type{" +
                "type_id=" + typeId +
                ", description='" + description + '\'' +
                '}';
    }
}

