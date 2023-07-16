package com.tasty.greens.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasty.greens.dto.ProductDTO;
import com.tasty.greens.model.Product;
import com.tasty.greens.mongo.util.SequenceGeneratorService;
import com.tasty.greens.repository.ProductRepository;
import com.tasty.greens.service.MappingService;
import com.tasty.greens.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
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
	public ProductDTO saveItem(ProductDTO productDto) {
		
		Product product = mappingSvc.convertDtoToEntity(productDto);
		product.setId(sequenceGeneratorService.generateSequence(Product.SEQUENCE_NAME));
		Product newProduct = productRepository.save(product);
		return mappingSvc.convertEntityToDto(newProduct);
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
	public Boolean deleteItem(long productId) {
		Optional<Product> product = productRepository.findById(productId);
		if(product.isPresent()) {
			productRepository.deleteProductById(productId);
			return true;
		}
		return false;
	}

	@Override
	public ProductDTO updateItem(ProductDTO productDto) {
		Optional<Product> productToUpdate = productRepository.findById(productDto.getProductId());
		if(productToUpdate.isPresent()) {
			Product product = mappingSvc.convertDtoToEntity(productDto);
			Product updatedProduct = productRepository.save(product);
			ProductDTO updatedProductDto = null;
			updatedProductDto = mappingSvc.convertEntityToDto(updatedProduct);
			return updatedProductDto;
		}
		return null;
	}

}
