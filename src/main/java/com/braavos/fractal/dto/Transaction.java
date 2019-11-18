package com.braavos.fractal.dto;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Transaction implements Serializable {

    @Id
    private String transactionId;

    private String companyId;

    private String bankId;

    private String accountId;

    private String bookingDate;

    private String description;

    private String currencyCode;

    private String type;

    private String amount;

    private Category category;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) { this.type = type; }

    public String getAmount() { return amount; }

    public void setAmount(String amount) { this.amount = amount; }

    public Category getCategory() { return category;    }

    public void setCategory(Category category) { this.category = category;    }

    public Transaction () {}

    public Transaction(String transactionId, String description, String accountId, Category category) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.category = category;
        this.description = description;
    }
}
