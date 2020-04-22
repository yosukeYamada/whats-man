package com.example.mappers;


import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Portfolio;

@Mapper
public interface PortfolioMapper {

	public void insert(Portfolio portfolio);

	public Portfolio findByUserId(Integer userId);
	
	public Portfolio findByUuid(String uuid);
	
	public Integer lastInsertId();

}
