package com.braavos.fractal.repository;

import com.braavos.fractal.dto.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    @Query("{'category.ID': ?0}")
    List<Transaction> findByCategory(final String id);
}
