package com.cyb.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {

	ValidateCode createImageCode(ServletWebRequest request) ;
//	ImageCode createImageCode(ServletWebRequest request) ;
}
