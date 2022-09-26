package com.mybible.mybible.transaction;

import java.util.Date;

public class SubtransactionByTransaction {
    private Long subtransaction_id;
    private Double amount;
    private String custom_id;
    private Date date;
    private String description;

    public Long getSubtransaction_id() {
        return subtransaction_id;
    }

    public void setSubtransaction_id(Long subtransaction_id) {
        this.subtransaction_id = subtransaction_id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCustom_id() {
        return custom_id;
    }

    public void setCustom_id(String custom_id) {
        this.custom_id = custom_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
