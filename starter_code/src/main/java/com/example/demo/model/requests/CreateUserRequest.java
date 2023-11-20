package com.example.demo.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Update by ThanhTLN - 2023/11/17
 * Description: model  for Create user request
 */
public class CreateUserRequest {

	@JsonProperty
	private String username;

	// 20231117 ThanhTLN - update start
	@JsonProperty
	private String password;

	@JsonProperty
	private String confirmPassword;
	// 20231117 ThanhTLN - update end
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	// 20231117 ThanhTLN - update start
	public void setPassword(String password) {
		this.password = password;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}
	// 20231117 ThanhTLN - update end
}
