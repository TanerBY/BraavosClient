package com.braavos.fractal.dto;

public class CategoryUpdate {

    private String categoryID;

    private String transactionID;

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }


    public CategoryUpdate () {}


    public CategoryUpdate(String categoryID, String transactionID) {
        this.categoryID = categoryID;
        this.transactionID = transactionID;
    }
}
