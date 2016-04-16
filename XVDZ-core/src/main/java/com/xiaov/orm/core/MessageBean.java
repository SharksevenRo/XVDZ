package com.xiaov.orm.core;

/**
 * 向前台传递响应状态和提示消息
 * @author Sharkseven
 *
 */
public class MessageBean {

	/**
	 * 响应码
	 */
	private String code;
	private String message;
	
	
	public MessageBean(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
