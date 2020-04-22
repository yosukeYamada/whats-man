package com.example.domain;

import java.util.List;

import lombok.Data;

@Data
public class Portfolio {

	private Integer portfolioId;
	private Integer userId;
	private String name;
	private String career;
	private List<Product> productList;
	private String uuid;
	
}
