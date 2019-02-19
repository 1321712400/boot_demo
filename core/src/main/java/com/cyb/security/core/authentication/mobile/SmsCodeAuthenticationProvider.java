package com.cyb.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
	
	private UserDetailsService iserDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
		UserDetails user = iserDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
		
		if(user == null) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		
		SmsCodeAuthenticationToken authenticationToken2 = new SmsCodeAuthenticationToken(user, user.getAuthorities());
		
		authenticationToken2.setDetails(authenticationToken.getDetails());
		
		return authenticationToken2;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getIserDetailsService() {
		return iserDetailsService;
	}

	public void setIserDetailsService(UserDetailsService iserDetailsService) {
		this.iserDetailsService = iserDetailsService;
	}

}
