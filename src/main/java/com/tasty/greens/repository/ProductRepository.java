package com.tasty.greens.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tasty.greens.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {

	void deleteProductById(long productId);
	
	@Query(value = "instagrocer.product.distinct('type')")
	List<Product> getProductCatalog();

}
