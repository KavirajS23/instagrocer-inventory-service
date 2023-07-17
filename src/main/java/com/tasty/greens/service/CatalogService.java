package com.tasty.greens.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tasty.greens.model.Product;

@Service
public interface CatalogService {

	public List<String> getCatalog();
	public List<Product> getCatalogProdutsByType(String type);
}
