package com.tasty.greens.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tasty.greens.dto.CatalogDTO;
import com.tasty.greens.model.Product;

@Service
public interface CatalogService {

	public List<CatalogDTO> getCatalog();
	public List<CatalogDTO> getCatalogProdutsByType(String type);
}
