package com.cyb.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cyb.security.core.properties.SecurityProperties;
import com.cyb.security.core.validate.code.ValidateCodeFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler imoocAuthenctiationFailureHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
		validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenctiationFailureHandler);
		http
			.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin()
			.loginPage("/authentication/require")
			.loginProcessingUrl("/authentication/form")
			.successHandler(imoocAuthenticationSuccessHandler)
			.failureHandler(imoocAuthenctiationFailureHandler)
//		http.httpBasic()
			.and()
			.authorizeRequests()//下面是授权配置
			.antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage(),"/code/image").permitAll()//匹配器跳过认证
			.anyRequest()//任何请求
			.authenticated()//都需要身份认证
			.and()
			.csrf().disable();
			
	}
	
}
