package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class RegisterUserForm {

	@NotBlank
	@Pattern(regexp = "^[0-9a-zA-Z]{8,16}+$")
	private String userName;
	@NotBlank
	@Pattern(regexp = "^[0-9a-zA-Z]{8,16}+$")
	private String password;
	@NotBlank(message = "入力フォームが空です")
	@Email(message = "メールアドレスを入力してください")
	private String mailAddress;
	@Pattern(regexp = "^\\d{8}$") // 半角数値8桁
	private String birthday;

	public Integer getIntBirthday() {
		return Integer.parseInt(birthday);
	}

}
