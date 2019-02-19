package com.cyb.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.cyb.security.core.validate.code.ImageCode;
import com.cyb.security.core.validate.code.ValidateCodeGenerator;

//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

	@Override
	public ImageCode createImageCode(ServletWebRequest request) {
		System.out.println("更高级");
		return null;
	}

}
