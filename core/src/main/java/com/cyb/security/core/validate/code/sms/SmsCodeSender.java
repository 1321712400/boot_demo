package com.cyb.security.core.validate.code.sms;

public interface SmsCodeSender {

	/**
	 * 
	 * @param mobile 手机号码
	 * @param code	内容
	 */
	void send(String mobile , String code);
}
