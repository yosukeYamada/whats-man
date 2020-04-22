package com.example.form;

import java.util.List;

import com.example.domain.Product;

import lombok.Data;

@Data
public class RegisterPortfolioForm {

	private String token;
	private String userId;
	private String name;
	private String career;
	private List<Product> productList;
//	private String productName;
//	private String productPeriod;
//	private String productContent;
//	private String tool;
	
	
}
