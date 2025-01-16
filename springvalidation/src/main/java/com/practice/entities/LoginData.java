package com.practice.entities;

import org.springframework.lang.NonNull;

public class LoginData {
	
	@NonNull(message="User can not be empty")
	@Size(min=3, max=12, message="User name must be between 3 to 12 chracters")
	private String userName;
	private String email;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "LoginData [userName=" + userName + ", email=" + email + "]";
	}
	
	
}
