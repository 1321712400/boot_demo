package com.cyb.web.controll;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cyb.exception.UserNotExistException;

@ControllerAdvice
public class ControllExceptionHandler {

	@ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String , Object> handleUserNotExis(UserNotExistException ex)
	{
		Map<String , Object> result = new HashMap<>();
		result.put("id", ex.getId());
		result.put("message", ex.getMessage());
		return result;
	}
}
