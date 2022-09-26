package com.mybible.mybible.transaction;

import java.util.Date;

public class SubtransactionByTransaction {
    private Long subtransactionId;
    private Double amount;
    private String customId;
    private Date date;
    private String description;

    public Long getSubtransactionId() {
        return subtransactionId;
    }

    public void setSubtransactionId(Long subtransactionId) {
        this.subtransactionId = subtransactionId;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
