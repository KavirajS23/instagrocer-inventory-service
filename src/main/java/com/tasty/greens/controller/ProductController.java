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
		Boolean isDeleted = productSvc.deleteItem(id);
		String message = Boolean.TRUE.equals(isDeleted) ? "Deleted successfully" : "No item found for id: "+id;
		HttpStatus status = Boolean.TRUE.equals(isDeleted) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(message, status);
	}
	
	@PutMapping(path = "item")
	public ResponseEntity<Object> updateItem(@RequestBody ProductDTO productDto) {
		ProductDTO updatedProductDto = productSvc.updateItem(productDto);
		Object response = updatedProductDto != null ? updatedProductDto : "No matching record found to update";
		HttpStatus status = updatedProductDto != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(response, status);
	}
	
}
