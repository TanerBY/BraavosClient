package com.braavos.fractal.controller;


import com.braavos.fractal.dto.Category;
import com.braavos.fractal.dto.CategoryUpdate;
import com.braavos.fractal.dto.Transaction;
import com.braavos.fractal.service.FractalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    FractalService fractalService;

    @GetMapping("/categories/{id}/transactions")
    public List<Transaction> getCategorizedTransactions(@PathVariable(value="id") String id) {
        return fractalService.getCategorizedTransactions(id);
    }


    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category category) {
        return fractalService.addCategory(category);
    }

    @PutMapping("/categories")
    public void updateCategory (@RequestBody CategoryUpdate update) {
        fractalService.updateTransactionCategory(update);
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return fractalService.getCategories();
    }


}
