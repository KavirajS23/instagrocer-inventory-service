package com.tasty.greens.service;

import org.springframework.stereotype.Service;

import com.tasty.greens.dto.ProductDTO;
import com.tasty.greens.model.Product;

@Service
public interface MappingService {

	public ProductDTO convertEntityToDto(Product product);
	public Product convertDtoToEntity(ProductDTO product);
}
