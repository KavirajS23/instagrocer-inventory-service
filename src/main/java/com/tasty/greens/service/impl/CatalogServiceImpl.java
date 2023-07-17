package com.tasty.greens.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.tasty.greens.dto.CatalogDTO;
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
	public List<CatalogDTO> getCatalog() {
		List<Product> products = productRepository.findAll();
		List<CatalogDTO> catalogs = new ArrayList();
		if(products != null && products.size()>0) {
			for (Product product : products) {
				CatalogDTO catalog = CatalogDTO.builder()
						.title(product.getTitle())
						.type(product.getType())
						.description(product.getDescription())
						.price(product.getPrice())
						.build();
				catalogs.add(catalog);
			}
		}
		return catalogs;
	}

	@Override
	public List<CatalogDTO> getCatalogProdutsByType(String type) {
		
		Query dynamicQuery = new Query();
		if(type != null) {
			Criteria typeCriteria = Criteria.where("type").is(type);
			dynamicQuery.addCriteria(typeCriteria);
		}
		List<Product> products = mongoTemplate.find(dynamicQuery, Product.class, "product");
		List<CatalogDTO> catalogs = new ArrayList();
		if(products != null && products.size()>0) {
			for (Product product : products) {
				CatalogDTO catalog = CatalogDTO.builder()
						.title(product.getTitle())
						.type(product.getType())
						.description(product.getDescription())
						.price(product.getPrice())
						.build();
				catalogs.add(catalog);
			}
		}
		return catalogs;
	}

}
