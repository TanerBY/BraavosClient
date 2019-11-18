package com.braavos.fractal.repository;

import com.braavos.fractal.dto.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository  extends MongoRepository<Category, String> {
}
