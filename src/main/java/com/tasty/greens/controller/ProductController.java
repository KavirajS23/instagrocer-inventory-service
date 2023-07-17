package com.tasty.greens.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tasty.greens.dto.ProductDTO;
import com.tasty.greens.dto.ProductResponse;
import com.tasty.greens.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productSvc;

	@GetMapping(path = "items") 
	public ResponseEntity<Object> getItems(@RequestParam(name="itemid", defaultValue = "0", required = false) Long productId) {
		if(productId != 0) {
			List<ProductDTO> productsDto = new ArrayList<ProductDTO>();
			ProductDTO specificProduct = productSvc.getItem(productId);
			if(specificProduct != null) {
				productsDto.add(specificProduct);
				return new ResponseEntity<>(productsDto, HttpStatus.OK);
			}
			return new ResponseEntity<>("No record found for item id: "+productId, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(productSvc.getItems(), HttpStatus.OK);
	}
	
	
	@PostMapping(path = "item") 
	public ResponseEntity<Object> saveItem(@RequestBody ProductDTO product) {
		return new ResponseEntity<>(productSvc.saveItem(product), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "item/{itemId}")
	public ResponseEntity<Object> deleteItem(@PathVariable(name = "itemId") Long id) {
		ProductResponse response = productSvc.deleteItem(id);
		response = response != null ? response : ProductResponse.builder()
				.itemId(id)
				.response("No item found for id: "+id)
				.build();
		HttpStatus status = response != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(response, status);
	}
	
	@PutMapping(path = "item")
	public ResponseEntity<Object> updateItem(@RequestBody ProductDTO productDto) {
		ProductResponse response = productSvc.updateItem(productDto);
		response = response != null ? response : ProductResponse.builder()
				.itemId(productDto.getItemId())
				.response("No item found for id: "+productDto.getItemId())
				.build();
		HttpStatus status = response != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(response, status);
	}
	
}
