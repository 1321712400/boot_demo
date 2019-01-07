package com.cyb.security.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin()
			.loginPage("/authentication/require")
			.loginProcessingUrl("/authentication/form")
//		http.httpBasic()
			.and()
			.authorizeRequests()//下面是授权配置
			.antMatchers("/authentication/require").permitAll()//匹配器跳过认证
			.anyRequest()//任何请求
			.authenticated()//都需要身份认证
			.and()
			.csrf().disable();
			
	}
	
}
