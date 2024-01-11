package com.example.loginApi.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder

public class JwtResponse {
	
	private String accessToken;
	private String token;
	
	
//	public JwtResponse(String accessToken,String Token) {
//		this.accessToken=accessToken;
//		this.token=token;
//	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public static String  builder() {
		// TODO Auto-generated method stub
		return null;
	}

}
