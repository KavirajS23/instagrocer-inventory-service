package com.tasty.greens.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {

	private long itemId;
	private String title;
	private String type;
	private String description;
	private BigDecimal price;
	private long quantity;
	private String quantityDate;
}
