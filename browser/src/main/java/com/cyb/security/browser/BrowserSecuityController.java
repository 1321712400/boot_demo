package com.cyb.security.browser;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrowserSecuityController {
	
	private RequestCache requestcache = new HttpSessionRequestCache();
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	/**
	 * 当身份认证时  跳转到这里
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/authentication/require")
	public String requireAuthentication(HttpServletRequest request ,HttpServletResponse response) throws IOException
	{
		SavedRequest savedRequest = requestcache.getRequest(request, response);
		if(savedRequest != null)
		{
			String target = ((HttpServletRequest) savedRequest).getRequestURI();
			logger.info("引发跳转的请求是:"+target);
			if(StringUtils.endsWithIgnoreCase(target, ".html"))
			{
				redirectStrategy.sendRedirect(request, response, "");
			}
			
		}
		return null;
	}
}
