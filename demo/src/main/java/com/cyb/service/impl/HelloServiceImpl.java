package com.cyb.service.impl;

import org.springframework.stereotype.Service;

import com.cyb.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService{

	@Override
	public String greeting(String name) {
		System.out.println("service init");
		return "hello "+name;
	}

}
