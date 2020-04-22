package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

/**
 * メール認証ドメイン.
 * 
 * @author yosuke.yamada
 *
 */
@Data
public class MailAuthentication {

	private Integer authenticationId;
	private String uuid;
	private String userName;
	private String password;
	private String mailAddress;
	private Integer birthday;
	private Timestamp date;
	private Integer status;
	
}
