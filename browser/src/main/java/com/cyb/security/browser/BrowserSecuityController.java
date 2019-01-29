package com.cyb.security.browser;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cyb.security.browser.support.SimpleResponse;
import com.cyb.security.core.properties.SecurityProperties;

@RestController
public class BrowserSecuityController {
	
	private RequestCache requestcache = new HttpSessionRequestCache();
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * 当身份认证时  跳转到这里
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/authentication/require")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public SimpleResponse requireAuthentication(HttpServletRequest request ,HttpServletResponse response) throws IOException
	{
		SavedRequest savedRequest = requestcache.getRequest(request, response);
		if(savedRequest != null)
		{
			String target = savedRequest.getRedirectUrl();
			logger.info("引发跳转的请求是:"+target);
			if(StringUtils.endsWithIgnoreCase(target, ".html"))
			{
				redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
			}
			
		}
		return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页");
	}
}
