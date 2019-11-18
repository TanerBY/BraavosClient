package com.braavos.fractal.dto;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Category implements Serializable {

    @Id
    private String ID;

    private String categoryName;

    private String filterWord;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getFilterWord() {
        return filterWord;
    }

    public void setFilterWord(String filterWord) {
        this.filterWord = filterWord;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Category() {}

    public Category(String categoryName, String filterWord) {
        this.categoryName = categoryName;
        this.filterWord = filterWord;
    }
}
