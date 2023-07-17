package com.tasty.greens.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tasty.greens.dto.ProductDTO;
import com.tasty.greens.dto.ProductResponse;

@Service
public interface ProductService {

	public ProductDTO getItem(long productId);
	public ProductResponse saveItem(ProductDTO product);
	public List<ProductDTO> getItems();
	public ProductResponse deleteItem(long productId);
	public ProductResponse updateItem(ProductDTO product);
	
}
