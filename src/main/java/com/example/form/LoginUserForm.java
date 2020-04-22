package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class LoginUserForm {

	@NotBlank
	@Pattern(regexp = "^[0-9a-zA-Z]{8,16}+$")
	private String userName;
	@NotBlank
	@Pattern(regexp = "^[0-9a-zA-Z]{8,16}+$")
	private String password;
	
	
}
