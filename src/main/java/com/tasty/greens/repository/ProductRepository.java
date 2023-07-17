package com.tasty.greens.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tasty.greens.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {

	void deleteProductById(long productId);
}
