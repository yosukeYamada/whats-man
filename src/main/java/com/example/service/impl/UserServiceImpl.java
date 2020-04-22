package com.example.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.form.LoginUserForm;
import com.example.mappers.MailAuthenticationMapper;
import com.example.mappers.PortfolioMapper;
import com.example.mappers.UserMapper;
import com.example.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MailAuthenticationMapper mailAuthenticationMapper;
	

	@Override
	public List<User> findAll() {
		List<User> userList = userMapper.findAll();
		return userList;
	}

	@Override
	public User load(String userName) {
		User user = userMapper.load(userName);
		return user;
	}
	

	@Override
	public boolean registerUser(String uuid) {
		boolean checkInert = true;
		try {
			userMapper.registerUser(uuid);
			mailAuthenticationMapper.updateStatus(uuid);
		} catch (Exception e) {
			// TODO: handle exception
			checkInert = false;
		}
		return checkInert;
	}

	@Override
	public User findByUserNameAndMailAddress(String userName, String mailAddress) {
		User user = userMapper.findByUserNameAndMailAddress(userName, mailAddress);
		return user;

	}
	
	@Override
	public User findByUserNameAndPassword(LoginUserForm form) {
		String userName = form.getUserName();
		String password = form.getPassword();
		User user = userMapper.findByUserNameAndPassword(userName, password);
		return user;
	}
	
	
	public String makeUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		return str;
	}
	

}
