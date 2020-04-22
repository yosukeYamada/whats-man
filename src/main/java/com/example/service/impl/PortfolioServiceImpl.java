package com.example.service.impl;


import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Portfolio;
import com.example.domain.Product;
import com.example.domain.User;
import com.example.form.RegisterPortfolioForm;
import com.example.form.ShowPortfolioForm;
import com.example.mappers.PortfolioMapper;
import com.example.mappers.ProductMapper;
import com.example.service.interfaces.PortfolioService;

@Service
public class PortfolioServiceImpl implements PortfolioService {

	@Autowired
	private PortfolioMapper portfolioMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private HttpSession session;
	
	
	@Override
	public void registerPortfolio(Portfolio portfolio) {
		portfolioMapper.insert(portfolio);
	}
	
	@Override
	public Portfolio findByUserId(Integer userId) {
		Portfolio portfolio = portfolioMapper.findByUserId(userId);
		return portfolio;
	}
	
	@Override
	public void registerProduct(Product product) {
		productMapper.insert(product);
	}
	
	@Override
	public Product findByProductId(Integer productId) {
		Product product = productMapper.findByProductId(productId);
		return product;
	}
	
	@Override
	public Integer lastInsertId() {
		return productMapper.lastInsertId();
	}
	
	@Override
	public Portfolio findByUuid(String uuid) {
		Portfolio portfolio = portfolioMapper.findByUuid(uuid);
		return portfolio;
	}
	
	
	
	/**
	 * ポートフォリオを登録するメソッド.
	 * 
	 * @param form リクエストパラメータ
	 * @return アクセスUUID
	 */
	public String register(RegisterPortfolioForm form) {
		Portfolio portfolio = new Portfolio();
		BeanUtils.copyProperties(form, portfolio);
		UUID uuid = UUID.randomUUID();
		portfolio.setUuid(uuid.toString());
		Integer userId = ((User) session.getAttribute(form.getToken())).getUserId();
		portfolio.setUserId(userId);
		registerPortfolio(portfolio);
		Integer portfolioId = portfolioMapper.lastInsertId();
		for(Product product:form.getProductList()) {
			product.setPortfolioId(portfolioId);
			registerProduct(product);
		}
		return uuid.toString();
	}
	
	public Portfolio searchPortfolio(ShowPortfolioForm form) {
		Portfolio portfolio = findByUuid(form.getUuid());
		return portfolio;
	}
	
	
	
}
