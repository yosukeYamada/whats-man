package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * ログイン認証用設定.
 * 
 * @author yosuke.yamada
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * セキュリティー設定を無効にする.
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/img/**", "/js/**");
	}

	/**
	 * アプリケーションアクセス範囲の認証.
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers().permitAll().antMatchers().hasRole("USER")
//				.antMatchers("/insertItem").hasRole("ADMIN")
				.anyRequest().authenticated();

		http
        .cors()
        .and().authorizeRequests()
        .antMatchers("/public").permitAll()
//        .antMatchers("/public", SIGNUP_URL, LOGIN_URL).permitAll()
        .anyRequest().authenticated()
        .and().logout()
        .and().csrf().disable()
        .addFilter(new JWTAuthenticationFilter(authenticationManager(), bCryptPasswordEncoder()))
        .addFilter(new JWTAuthorizationFilter(authenticationManager()))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
;

		http.formLogin()
				.loginPage("/").loginProcessingUrl("/login").failureUrl("/login?error=true")
				.defaultSuccessUrl("/itemList", true) // 第1引数:デフォルトでログイン成功時に遷移させるパス
														// 第2引数: true :認証後常に第1引数のパスに遷移
														// false:認証されてなくて一度ログイン画面に飛ばされてもログインしたら指定したURLに遷移
				.usernameParameter("name").passwordParameter("password"); // 認証時に使用するパスワードのリクエストパラメータ名

		http.logout() // ログアウトに関する設定
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout**")) // ログアウトさせる際に遷移させるパス
				.logoutSuccessUrl("/") // ログアウト後に遷移させるパス(ここではログイン画面を設定)
				.deleteCookies("JSESSIONID") // ログアウト後、Cookieに保存されているセッションIDを削除
				.invalidateHttpSession(true); // true:ログアウト後、セッションを無効にする false:セッションを無効にしない

//		http.csrf().ignoringAntMatchers("/users/registerUser", "/users/validate"); // 指定したPASSだけcsrfのトークンチェックを外す

	}

//	private CorsConfigurationSource getCorsConfigurationSource() {
//		CorsConfiguration corsConfiguration = new CorsConfiguration();
//		// 全てのメソッドを許可
//		corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
//		// 全てのヘッダを許可
//		corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
//		// 全てのオリジンを許可
//		corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
//		corsConfiguration.setAllowCredentials(true);
//
//		UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
//		// パスごとに設定が可能。ここでは全てのパスに対して設定
//		corsSource.registerCorsConfiguration("/**", corsConfiguration);
//
//		return corsSource;
//	}

	/**
	 * 「認証」に関する設定. 認証ユーザを取得する「UserDetailsService」の設定や
	 * パスワード照合時に使う「PasswordEncoder」の設定
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}

	/**
	 * パスワードハッシュ化の設定を行う.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
