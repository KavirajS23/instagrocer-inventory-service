package com.tasty.greens.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.tasty.greens.model.Product;
import com.tasty.greens.repository.ProductRepository;
import com.tasty.greens.service.CatalogService;

@Service
public class CatalogServiceImpl implements CatalogService {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<String> getCatalog() {
		List<Product> products = productRepository.getProductCatalog();
		System.out.println("Products: "+ products);
		Set<String> catalog = new LinkedHashSet<String>();
		if(products != null && products.size()>0) {
			for (Product product : products) {
				catalog.add(product.getType());
			}
		}
		return catalog.stream().toList();
	}

	@Override
	public List<Product> getCatalogProdutsByType(String type) {
		
		Query dynamicQuery = new Query();
		if(type != null) {
			Criteria typeCriteria = Criteria.where("type").is(type);
			dynamicQuery.addCriteria(typeCriteria);
		}
		return mongoTemplate.find(dynamicQuery, Product.class, "product");
	}

}
