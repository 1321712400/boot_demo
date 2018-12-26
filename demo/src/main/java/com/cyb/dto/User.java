package com.cyb.dto;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.cyb.validator.MyConstraint;
import com.fasterxml.jackson.annotation.JsonView;

public class User {

	public interface UserSimpleView{};
	public interface UserDetailView extends UserSimpleView{};
	
	private String id;
	
	@MyConstraint(message = "這是一個測試")
	private String username;
	
	@NotBlank(message = "密码不能为空")
	private String password;
	
	@Past(message = "生日必须是过去的时间")
	private Date birthbody;
	
	

	@JsonView(UserSimpleView.class)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@JsonView(UserSimpleView.class)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@JsonView(UserSimpleView.class)
	public Date getBirthbody() {
		return birthbody;
	}

	public void setBirthbody(Date birthbody) {
		this.birthbody = birthbody;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", birthbody=" + birthbody
				+ "]";
	}

	
	
	
	
}
