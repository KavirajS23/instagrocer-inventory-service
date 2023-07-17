package com.tasty.greens.service.impl;

import org.springframework.stereotype.Service;

import com.tasty.greens.dto.ProductDTO;
import com.tasty.greens.model.Product;
import com.tasty.greens.service.MappingService;

@Service
public class MappingServiceImpl implements MappingService {

	@Override
	public ProductDTO convertEntityToDto(Product product) {
		return ProductDTO.builder()
				.itemId(product.getId())
				.title(product.getTitle())
				.description(product.getDescription())
				.price(product.getPrice())
				.type(product.getType())
				.quantity(product.getQuantity())
				.quantityDate(product.getQuantityDate())
				.build();
	}

	@Override
	public Product convertDtoToEntity(ProductDTO productDto) {
		
		return Product.builder()
				.id(productDto.getItemId() > 0 ? productDto.getItemId() : null)
				.title(productDto.getTitle())
				.description(productDto.getDescription())
				.price(productDto.getPrice())
				.type(productDto.getType())
				.quantity(productDto.getQuantity())
				.quantityDate(productDto.getQuantityDate())
				.build();
	}

}
