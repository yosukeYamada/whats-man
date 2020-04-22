package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.MyException;
import com.example.domain.User;
import com.example.form.LoginUserForm;
import com.example.form.RegisterUserForm;
import com.example.form.ValidateForm;
import com.example.service.impl.MailAuthenticationServiceImpl;
import com.example.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private MailAuthenticationServiceImpl mailAuthenticationService;

	@Autowired
	private HttpSession session;

	/**
	 * 新規ユーザ登録を行うメソッド.
	 * 
	 * @param form リクエストパラメータ
	 * @return 結果を返す.(仮)
	 */
	@CrossOrigin(origins = { "http://localhost:8081" })
	@RequestMapping("/registerUser")
	public void registerUser(@RequestBody @Validated RegisterUserForm form) {
		User user = userService.findByUserNameAndMailAddress(form.getUserName(), form.getMailAddress());

		if (user == null) {
			boolean mailsend = mailAuthenticationService.subscriptionUser(form);
			if (mailsend == true) {
				System.err.println("成功");
			} else {
				System.err.println("メール送信失敗");
			}
		}
		System.err.println(form);
//		return form;
	}

	/**
	 * メール認証を行うメソッド.
	 * 
	 * @param form リクエストパラメータ(uuid)
	 * @return 認証結果を返す。
	 */
	@CrossOrigin
	@RequestMapping("/validate")
	public boolean validate(@RequestBody ValidateForm form) {
		boolean checkInsert = userService.registerUser(form.getUuid());
		return checkInsert;
	}

	/**
	 * ログインを行うメソッド.
	 * 
	 * @param form リクエストパラメータ
	 * @return ユーザ情報
	 */
	@RequestMapping("/login")
	public User login(@RequestBody LoginUserForm form) throws Exception {
		User user = userService.findByUserNameAndPassword(form);
		if (user != null) {
			String token = userService.makeUUID();
			user.setToken(token);
			session.setAttribute(user.getToken(), user);
			return user;
		} else {
			return null;
		}
	}

	/**
	 * ログアウトを行うメソッド.
	 * 
	 * @param token 消去するトークン
	 */
	@RequestMapping("/logout")
	public void logout(String token) {
		User user = (User) session.getAttribute(token);
		if (user != null) {
			session.removeAttribute(token);
		}
	}

	@ExceptionHandler(Exception.class)
	public String exception(Exception e){
//	public void exception(Exception e) {
		System.out.println("test");
//		return "Exception!!" + e.toString();
		return e.toString();
	}

}
