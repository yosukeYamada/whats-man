package com.example.domain;

import java.util.List;

import lombok.Data;

@Data
public class User {

	private Integer userId;
	private String userName;
	private String password;
	private String mailAddress;
	private String birthday;
	private String token;
	private List<Portfolio> portfolioList;
//	private List<String> portfolioUuidList;

}
