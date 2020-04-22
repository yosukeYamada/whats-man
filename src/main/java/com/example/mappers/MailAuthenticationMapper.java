package com.example.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.MailAuthentication;

/**
 * 新規ユーザ登録時、メール認証に使うMapper.
 * 
 * 
 * @author yosuke.yamada
 *
 */
@Mapper
public interface MailAuthenticationMapper {

	public MailAuthentication findByUserName(String userName);
	
	
	
	/**
	 * 認証情報を取得するMapper
	 * 
	 * @param uuid UUID
	 * @return 認証情報
	 */
	public MailAuthentication findByUuid(String uuid);

	/**
	 * 認証情報を登録するMapper
	 * 
	 * @param mailAuthentication
	 */
	public void insertMailAuthentication(MailAuthentication mailAuthentication);
	
	
	public void updateStatus(String uuid);

}
