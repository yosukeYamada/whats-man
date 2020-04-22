package com.example.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Product;

@Mapper
public interface ProductMapper {

	public void insert(Product product);
	
	public Product findByProductId(Integer id);
	
	public Integer lastInsertId();
	
}
