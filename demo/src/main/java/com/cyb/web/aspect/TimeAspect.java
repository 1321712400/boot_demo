package com.cyb.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {

//	@Before//调用之前
//	@After//成功返回
//	@AfterThrowing//出现错误
	@Around("execution(* com.cyb.web.controll.UserControll.*(..))")
	public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
	
		System.out.println("time aspect start");
		
		Object[] object = pjp.getArgs();
		for (Object object2 : object) {
			System.out.println("arg is "+object2);
		}
		long start = new Date().getTime();
		Object obj = pjp.proceed();
		System.out.println("endtime aspect start 耗时"+(new Date().getTime()-start));
		return obj;
		
	}
}
