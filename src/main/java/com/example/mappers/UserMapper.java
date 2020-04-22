package com.example.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.User;

@Mapper
public interface UserMapper {

	public List<User> findAll();

	public User load(String userName);

	public void registerUser(String uuid);

	public User findByUserNameAndMailAddress(String userName, String mailAddress);
	
	public User findByUserNameAndPassword(String userName,String password);
	
}
