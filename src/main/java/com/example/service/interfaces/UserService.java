package com.example.service.interfaces;

import java.util.List;

import com.example.domain.User;
import com.example.form.LoginUserForm;

public interface UserService {

	public List<User> findAll();
	
	public User load(String userName);
	
	public boolean registerUser(String uuid);
	
	public User findByUserNameAndMailAddress(String userName,String mailAddress);
	
	public User findByUserNameAndPassword(LoginUserForm form);
	

}
