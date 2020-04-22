package com.example.service.interfaces;

import com.example.domain.MailAuthentication;

public interface MailAuthenticationService {
	
	public MailAuthentication findByUserName(String userName);

	public MailAuthentication findByUuid(String uuid);
	
	public void insertMailAuthentication(MailAuthentication mailAuthentication);
	
	public void updateStatus(String uuid);
	
}
