package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

	
	@Autowired
	private MailSender mailSender;
	
	
	
	@Async
	public boolean mailSenderAboutAuthenticationUser(String mailAddress, String uuid) {
		String ipAndPort = "localhost:8081/";
		String from = "yshantian833@gmail.com";
		String to = mailAddress;
		String title = "アカウント確認のお願い";
		String context = "こんにちは。" + "\n" + "下記URLをクリックにてWhat`s Manの認証を完了してください。" + "http://" + ipAndPort + "validate"
				+ "?id=" + uuid;
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(title);
		message.setText(context);
		try {
			
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
		mailSender.send(message);
		return true;
	}
	
}
