package com.mybible.mybible.category;

import org.hibernate.annotations.ColumnDefault;

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
            updatable = true
    )
    private String description;

    @ColumnDefault("true")
    @Column(
            name = "active"
    )
    private Boolean active;

    public Category() {
    }

    public Category(Long categoryId, String description, Boolean active) {
        this.categoryId = categoryId;
        this.description = description;
        this.active = active;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }
}

