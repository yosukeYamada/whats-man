package com.example;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.domain.User;
import com.example.form.LoginUserForm;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.Logger;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	private AuthenticationManager authenticationManager;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.authenticationManager = authenticationManager;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;

		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(LOGIN_URL, "POST"));

		setUsernameParameter(LOGIN_ID);
		setPasswordParameter(PASSWORD);

	}

	public Authentication attemptAuthenticactAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {

		try {
			LoginUserForm userForm = new ObjectMapper().readValue(req.getInputStream(), LoginUserForm.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userForm.getLoginId(),
					userForm.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);

		}

	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException{
		String token = Jwts.builder().setSubject(((User)auth.getPrincipal()).getUserName()).setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS512,SECRET.getBytes()).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}
	
	

}
