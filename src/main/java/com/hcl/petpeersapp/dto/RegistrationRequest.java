package com.hcl.petpeersapp.dto;

import java.io.Serializable;

public class RegistrationRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String userPassword;
	private String confirmPassword;

	public RegistrationRequest() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "RegistrationRequest [username=" + username + ", userPassword=" + userPassword + ", confirmPassword="
				+ confirmPassword + "]";
	}

}
