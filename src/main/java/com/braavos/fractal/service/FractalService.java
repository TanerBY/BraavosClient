package com.braavos.fractal.service;


import com.braavos.fractal.repository.CategoryRepository;
import com.braavos.fractal.repository.TransactionRepository;
import com.braavos.fractal.client.FractalClient;
import com.braavos.fractal.dto.Category;
import com.braavos.fractal.dto.CategoryUpdate;
import com.braavos.fractal.dto.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FractalService {

    @Autowired
    FractalClient fractalClient;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CategoryRepository categoryRepository;


    public void readRawTransactions () {
        List<Transaction> transactions = fractalClient.readTransactions();
        cleanUpCategories();
        transactions.stream().forEach(s -> s.setCategory(getCategoryByDescription(s.getDescription())));
        transactionRepository.saveAll(transactions);
    }

    private Category getCategoryByDescription ( String description) {
        return categoryRepository.findAll().stream().filter(s -> description.contains(s.getFilterWord())).findFirst().orElse(null);
    }

    public List<Transaction> getCategorizedTransactions (String id) {
        return transactionRepository.findByCategory(id);
    }

    public Category addCategory(Category category) {
        return categoryRepository.insert(category);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void updateTransactionCategory (CategoryUpdate update) {
        Transaction transaction = transactionRepository.findById(update.getTransactionID()).get();
        Category category = categoryRepository.findById(update.getCategoryID()).get();
        transaction.setCategory(category);
        transactionRepository.save(transaction);
    }

    private void cleanUpCategories () {
        categoryRepository.deleteAll();
        categoryRepository.saveAll(Arrays.asList(new Category("Coffee", "COSTA"), new Category( "Travel", "TRAVEL")));
    }


}
