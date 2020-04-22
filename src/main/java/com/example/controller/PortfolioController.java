package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Portfolio;
import com.example.form.RegisterPortfolioForm;
import com.example.form.ShowPortfolioForm;
import com.example.service.impl.PortfolioServiceImpl;

@RestController
@RequestMapping("/portfolios")
public class PortfolioController {

	@Autowired
	private PortfolioServiceImpl portfolioService;

	@RequestMapping("/registerPortfolio")
	public String registerPortfolio(@RequestBody RegisterPortfolioForm form) {
		String uuid = portfolioService.register(form);
		String accessid = uuid;
		return accessid;
	}
	
	@RequestMapping("/showPortfolio")
	public Portfolio showPortfolio(@RequestBody ShowPortfolioForm form) {
		Portfolio portfolio = portfolioService.searchPortfolio(form);
		return portfolio;
	}

}
