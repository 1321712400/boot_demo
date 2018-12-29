package com.cyb.web.async;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AsyncControll {

	@Autowired
	private MockQueue mockqueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@RequestMapping("/order")
	public DeferredResult<String> order() throws InterruptedException
	{
		logger.info("主线程开始");
		
		String orderUnumber = RandomStringUtils.randomNumeric(8);
		mockqueue.setPlaceOrder(orderUnumber);
		
		DeferredResult<String> result = new DeferredResult<>();
		deferredResultHolder.getMap().put(orderUnumber, result);
		
		
//		Callable<String> result=new Callable<String>() {
//			public String call() throws InterruptedException {
//				logger.info("副线程开始");
//				Thread.sleep(1000);
//				logger.info("副线程结束");
//				return "success";
//			}
//		};
		logger.info("主线程返回");
		return result;
	}
}
