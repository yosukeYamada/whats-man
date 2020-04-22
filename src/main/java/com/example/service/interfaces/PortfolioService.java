package com.example.service.interfaces;


import com.example.domain.Portfolio;
import com.example.domain.Product;

public interface PortfolioService {

	public void registerPortfolio(Portfolio portfolio);

	public Portfolio findByUserId(Integer userId);
	
	public void registerProduct(Product product);
	
	public Product findByProductId(Integer productId);
	
	public Integer lastInsertId();
	
	public Portfolio findByUuid(String uuid);
	
//	public List<Integer> findByUserIdForLogin(Integer userId);
}
