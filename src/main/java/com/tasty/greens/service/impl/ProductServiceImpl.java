package com.tasty.greens.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasty.greens.dto.ProductDTO;
import com.tasty.greens.dto.ProductResponse;
import com.tasty.greens.model.Product;
import com.tasty.greens.mongo.util.SequenceGeneratorService;
import com.tasty.greens.repository.ProductRepository;
import com.tasty.greens.service.MappingService;
import com.tasty.greens.service.ProductService;
import com.tasty.greens.util.DateUtil;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private DateUtil dateUtil;
	
	@Autowired
	private MappingService mappingSvc;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Override
	public ProductDTO getItem(long productId) {
		Optional<Product> product = productRepository.findById(productId);
		ProductDTO productDto = null;
		if(product.isPresent()) {
			productDto = mappingSvc.convertEntityToDto(product.get());
		}
		return productDto;
	}

	@Override
	public ProductResponse saveItem(ProductDTO productDto) {
		
		productDto.setItemId(sequenceGeneratorService.generateSequence(Product.SEQUENCE_NAME));
		productDto.setQuantityDate(dateUtil.formatDate(new Date(), "ddMMMyyyy"));
		Product product = mappingSvc.convertDtoToEntity(productDto);
		Product newProduct = productRepository.save(product);
		ProductResponse response = ProductResponse.builder()
				.itemId(newProduct.getId())
				.title(newProduct.getTitle())
				.response("added")
				.build();
		return response;
	}

	@Override
	public List<ProductDTO> getItems() {
		List<ProductDTO> productsDto = new ArrayList<ProductDTO>();
		List<Product> products = productRepository.findAll();
		if(products != null && !products.isEmpty()) {
			for (Product product : products) {
				productsDto.add(mappingSvc.convertEntityToDto(product));
			}
		}
		return productsDto;
	}

	@Override
	public ProductResponse deleteItem(long productId) {
		Optional<Product> product = productRepository.findById(productId);
		if(product.isPresent()) {
			productRepository.deleteProductById(productId);
			return ProductResponse.builder()
					.itemId(product.get().getId())
					.title(product.get().getTitle())
					.response("deleted")
					.build();
		}
		return null;
	}

	@Override
	public ProductResponse updateItem(ProductDTO productDto) {
		Optional<Product> productToUpdate = productRepository.findById(productDto.getItemId());
		if(productToUpdate.isPresent()) {
			Product product = mappingSvc.convertDtoToEntity(productDto);
			product.setQuantityDate(productToUpdate.get().getQuantityDate());
			Product updatedProduct = productRepository.save(product);
			return ProductResponse.builder()
					.itemId(updatedProduct.getId())
					.title(updatedProduct.getTitle())
					.response("updated")
					.build();
		}
		return null;
	}

}
