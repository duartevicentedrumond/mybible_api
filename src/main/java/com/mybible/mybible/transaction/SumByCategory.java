package com.mybible.mybible.transaction;

public class SumByCategory {
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    private String category;
    private Double sum;

}
