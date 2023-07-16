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
				.productId(product.getId())
				.productName(product.getName())
				.productPrice(product.getPrice())
				.productType(product.getType())
				.build();
	}

	@Override
	public Product convertDtoToEntity(ProductDTO productDto) {
		
		return Product.builder()
				.id(productDto.getProductId() > 0 ? productDto.getProductId() : null)
				.name(productDto.getProductName())
				.price(productDto.getProductPrice())
				.type(productDto.getProductType())
				.build();
	}

}
