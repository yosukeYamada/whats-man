package com.example.domain;

import lombok.Data;

@Data
public class Product {

	private Integer productId;
	private Integer portfolioId;
	private String productName;
	private String productPeriod;
	private String productContent;
	private String tool;
	
}
