package com.tasty.greens.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

	private long itemId;
	private String title;
	private String response;
}
