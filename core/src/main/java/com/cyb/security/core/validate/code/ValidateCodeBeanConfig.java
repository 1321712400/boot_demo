package com.cyb.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cyb.security.core.properties.SecurityProperties;
import com.cyb.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.cyb.security.core.validate.code.sms.SmsCodeSender;

@Configuration
public class ValidateCodeBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;
	@Bean
	@ConditionalOnMissingBean(name = "imageCodeGenerator")//系统启动时会先找imageCodeGenerator  如果存在就用已经存在的，如果不存再就用下面的。
	public ValidateCodeGenerator imageCodeGenerator()
	{
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)//系统启动时会先找smsCodeGenerator  如果存在就用已经存在的，如果不存再就用下面的。
	public SmsCodeSender smsCodeSender()
	{
		return new DefaultSmsCodeSender();
	}
}
