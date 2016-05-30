package com.xiaov.web.support;

import java.util.UUID;

public class UserToken {

	private String token;
	private Long freshTime;

	public UserToken() {
		freshTime = new Long(System.currentTimeMillis());
		token = UUID.randomUUID().toString();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getFreshTime() {
		return freshTime;
	}

	public void setFreshTime(Long freshTime) {
		this.freshTime = freshTime;
	}
}
