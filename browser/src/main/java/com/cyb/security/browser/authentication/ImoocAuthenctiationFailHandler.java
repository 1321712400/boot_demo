package com.cyb.security.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.cyb.security.browser.support.SimpleResponse;
import com.cyb.security.core.properties.LoginType;
import com.cyb.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("imoocAuthenctiationFailureHandler")
public class ImoocAuthenctiationFailHandler extends SimpleUrlAuthenticationFailureHandler {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SecurityProperties securityProperties;
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		logger.info("登录失败");
		
		if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType()))
		{
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
		}
		else
		{
			super.onAuthenticationFailure(request, response, exception);
		}
		
	}

}
