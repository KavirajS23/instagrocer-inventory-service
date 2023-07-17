package com.tasty.greens.model;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "product")
public class Product {
	
	@Transient
	public static final String SEQUENCE_NAME = "product_sequence";

	@Id
	private long id;
	private String name;
	private String type;
	@Field(targetType = FieldType.DECIMAL128)
	private BigDecimal price;
	private long quantity;
	private String unit;
}
