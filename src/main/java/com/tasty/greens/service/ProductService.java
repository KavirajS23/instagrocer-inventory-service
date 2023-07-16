package com.tasty.greens.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tasty.greens.dto.ProductDTO;

@Service
public interface ProductService {

	public ProductDTO getItem(long productId);
	public ProductDTO saveItem(ProductDTO product);
	public List<ProductDTO> getItems();
	public Boolean deleteItem(long productId);
	public ProductDTO updateItem(ProductDTO product);
	
}
