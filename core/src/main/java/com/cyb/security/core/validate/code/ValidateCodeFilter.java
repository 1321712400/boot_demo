package com.cyb.security.core.validate.code;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

public class ValidateCodeFilter extends OncePerRequestFilter{
	
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	private  SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(StringUtils.equals("/authentication/form", request.getRequestURI())
					&& StringUtils.equalsIgnoreCase(request.getMethod(), "post"))
		{
			try {
				validate(new ServletWebRequest(request));
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return ; 
			}
		}
		filterChain.doFilter(request, response);
	}

	private void validate(ServletWebRequest request) throws ServletRequestBindingException {
		ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
		
		String codeInReuqest = ServletRequestUtils.getStringParameter(request.getRequest() , "imageCode");
		
		if(StringUtils.isBlank(codeInReuqest))
		{
			throw new ValidateCodeException("验证码的值不能为空");
		}
		if(codeInSession == null)
		{
			throw new ValidateCodeException("验证码不存在");
		}
		if(codeInSession.isExpried())
		{
			sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
			throw new ValidateCodeException("验证码已过期");
		}
		if(!StringUtils.equals(codeInSession.getCode(), codeInReuqest))
		{
			throw new ValidateCodeException("验证码不匹配");
		}
		
		sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
	}

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	public SessionStrategy getSessionStrategy() {
		return sessionStrategy;
	}

	public void setSessionStrategy(SessionStrategy sessionStrategy) {
		this.sessionStrategy = sessionStrategy;
	}

	

}
