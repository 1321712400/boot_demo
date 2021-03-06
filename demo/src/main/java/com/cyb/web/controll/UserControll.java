package com.cyb.web.controll;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyb.dto.User;
import com.cyb.dto.UserQueryCondition;
import com.cyb.exception.UserNotExistException;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/user")
public class UserControll {
	
	
	
	@GetMapping("/me")
	public Object getCurrenUser(@AuthenticationPrincipal UserDetails authentication)
	{
		return authentication;
	}
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id)
	{
		System.out.println(id);
	}

	
	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user , BindingResult errors)
	{
		if(errors.hasErrors())
		{
			errors.getAllErrors().stream().forEach(error -> 
			{
//				FieldError fi = (FieldError) error;
//				String message = fi.getField()+error.getDefaultMessage();
				System.out.println(error.getDefaultMessage());
				
			});
		}
		
		System.out.println(user.toString());
		user.setId("1");
		return user;
	}
	

	@PostMapping
//	public User create(@Valid @RequestBody User user , BindingResult errors)
	public User create(@Valid @RequestBody User user )
	{
//		if(errors.hasErrors())
//		{
//			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
//		}
//		
		System.out.println(user.toString());
		user.setId("1");
		return user;
	}
	@GetMapping
	@JsonView(User.UserSimpleView.class)
	@ApiOperation(value = "用户查询服务")
	public List<User> query(UserQueryCondition condition ,@PageableDefault(page = 1 , size = 20 , sort="username ,asc") Pageable pageable)
	{
		System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getSort());
		
		List<User> user = new ArrayList<User>();
		user.add(new User());
		user.add(new User());
		user.add(new User());
		return user;
	}
	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@ApiParam("用户ID") @PathVariable String id )
	{
//		throw new UserNotExistException("123");
		System.out.println("getinfo");
		User user = new User();
		user.setUsername("tom");
		return user;
	}
	@GetMapping("chen")
	public String chen()
	{
		return "chen";
	}
	
	
}
