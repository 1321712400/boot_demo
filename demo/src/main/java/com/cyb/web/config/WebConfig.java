package com.cyb.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cyb.web.filter.TimeFilter;
import com.cyb.web.interceptor.TimeInterceptor;
/**
 * 
 * @author Administrator
 *
 */
@Configuration//配置类
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private TimeInterceptor timeInterceptor;
	
	@Override//拦截异步请求
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//		configurer.registerCallableInterceptors(interceptors);
		
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
	}
	@Bean 
	public FilterRegistrationBean timeFilter()
	{
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		
		TimeFilter timeFilter = new TimeFilter();
		registrationBean.setFilter(timeFilter);
		
		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);
		return registrationBean;
	}
}
