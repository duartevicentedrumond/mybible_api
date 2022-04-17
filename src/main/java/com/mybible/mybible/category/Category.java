package com.mybible.mybible.category;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Category")
public class Category {

    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "category_sequence"
    )
    @Column(
            name = "category_id",
            unique = true
    )
    private Long categoryId;

    @Column(
            name = "description",
            columnDefinition = "TEXT",
            updatable = true
    )
    private String description;

    public Category() {
    }

    public Category(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + categoryId +
                ", description='" + description + '\'' +
                '}';
    }
}

