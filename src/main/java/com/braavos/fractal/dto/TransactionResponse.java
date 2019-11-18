package com.braavos.fractal.dto;


import com.fasterxml.jackson.annotation.JsonProperty;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponse implements Serializable {


    @JsonProperty("results")
    private List<Transaction> results = new ArrayList<>();

    public List<Transaction> getResults() {
        return results;
    }

    public void setResults(List<Transaction> results) {
        this.results = results;
    }
}
