package com.tasty.greens.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CatalogDTO {

	private String title;
	private String type;
	private String description;
	private BigDecimal price;
	private String rating;
	
}
