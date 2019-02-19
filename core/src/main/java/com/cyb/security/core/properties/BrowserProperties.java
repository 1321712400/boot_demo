package com.cyb.security.core.properties;

public class BrowserProperties {

	private String loginPage = "/cyb-login.html";

	private LoginType loginType = LoginType.JSON;
	
	private int rememberMeSeconds = 3600;//记住秒数
	
	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}
	
	
}
