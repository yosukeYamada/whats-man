package com.example.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.MailAuthentication;
import com.example.form.RegisterUserForm;
import com.example.mappers.MailAuthenticationMapper;
import com.example.service.MailSenderService;


@Service
public class MailAuthenticationServiceImpl implements com.example.service.interfaces.MailAuthenticationService {

	@Autowired
	private MailAuthenticationMapper mapper;
	
	@Autowired
	private MailSenderService mailSenderService;
	
	@Override
	public MailAuthentication findByUserName(String userName) {
		return mapper.findByUserName(userName);
	}

	@Override
	public MailAuthentication findByUuid(String uuid) {
		return mapper.findByUuid(uuid);

	}
	
	public boolean subscriptionUser(RegisterUserForm form) {
		MailAuthentication registerMailAuthentication = new MailAuthentication();
		BeanUtils.copyProperties(form, registerMailAuthentication);
		registerMailAuthentication.setBirthday(form.getIntBirthday());
		LocalDateTime localDateTime = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(localDateTime);
		registerMailAuthentication.setDate(timestamp);
		String uuid = UUID.randomUUID().toString();
		registerMailAuthentication.setUuid(uuid);
		registerMailAuthentication.setStatus(0);
		insertMailAuthentication(registerMailAuthentication);
		boolean mailsend = mailSenderService.mailSenderAboutAuthenticationUser(registerMailAuthentication.getMailAddress(), uuid);
		return mailsend;
	}
	

	@Override
	public void insertMailAuthentication(MailAuthentication mailAuthentication) {
		mapper.insertMailAuthentication(mailAuthentication);
	}
	
	@Override
	public void updateStatus(String uuid) {
		mapper.updateStatus(uuid);
	}

}
