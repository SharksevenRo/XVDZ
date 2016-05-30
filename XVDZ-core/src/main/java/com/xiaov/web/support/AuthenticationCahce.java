package com.xiaov.web.support;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.xiaov.utils.StrKit;

public class AuthenticationCahce {

	private final static Map<String, UserToken> cache = new HashMap<String, UserToken>();

	private final static TokenThread tokenthread = new TokenThread();
	static {
		Thread thread = new Thread(tokenthread);
		thread.start();
	}

	/**
	 * 存入身份认证
	 * 
	 * @param userId
	 * @param token
	 */
	public static UserToken put(String userId) {

		UserToken token = new UserToken();
		if (StrKit.notBlank(userId)) {
			cache.put(userId, token);
		} else {
			throw new RuntimeException("userId is NULL");
		}
		return token;
	}

	public static UserToken get(String userId) {

		if (StrKit.notBlank(userId)) {
			return cache.get(userId);
		} else {
			throw new RuntimeException("userId is NULL");
		}
	}

	/**
	 * 检查是否过时
	 */
	public static void check() {

		UserToken token;
		System.out.println("扫描缓存");
		for (Entry<String, UserToken> entry : cache.entrySet()) {
			token = entry.getValue();
			if ((token.getFreshTime() - System.currentTimeMillis()) > (60 * 1000 * 30)) {
				cache.remove(entry.getKey());
			}
		}
	}
}
