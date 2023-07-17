package com.tasty.greens.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {

	private long productId;
	private String productName;
	private String productType;
	private BigDecimal productPrice;
	private long productQuantity;
	private String productUnit;
}
