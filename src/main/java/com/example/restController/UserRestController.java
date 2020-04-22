package com.example.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.User;
import com.example.form.RegisterUserForm;
import com.example.service.impl.UserServiceImpl;

@RestController
public class UserRestController {

	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping("/test")
	public String test(@RequestBody RegisterUserForm form) {
		List<User> userList = userService.findAll();
		User user = userService.load(form.getUserName());
		System.out.println(userList);
		System.out.println(user);
		
		return "通信成功";
	}
	
}
