package com.xiaov.weixin.config;

public class ConfigInfo {
		private String WeChatAppID;
		private String WeChatAppSecret;
		private String WeChatOriginalID;
		private String WeChatToken;
		private String WeChatAESKey;
		private String jwckey;
		 
		public String getWeChatAppID(){
			return WeChatAppID;
		} 
		public void setWeChatAppID(String WeChatAppID){
			this.WeChatAppID = WeChatAppID;
		} 
		 
		public String getWeChatOriginalID(){
			return WeChatOriginalID;
		} 
		public void setWeChatOriginalID(String WeChatOriginalID){
			this.WeChatOriginalID = WeChatOriginalID;
		} 
		 
		public String getWeChatAppSecret(){
			return WeChatAppSecret;
		} 
		public void setWeChatAppSecret(String WeChatAppSecret){
			this.WeChatAppSecret = WeChatAppSecret;
		} 
		 
		public String getWeChatToken(){
			return WeChatToken;
		} 
		public void setWeChatToken(String WeChatToken){
			this.WeChatToken = WeChatToken;
		} 
		 
		public String getWeChatAESKey(){
			return WeChatAESKey;
		} 
		public void setWeChatAESKey(String WeChatAESKey){
			this.WeChatAESKey = WeChatAESKey;
		} 
		 
		public String getjwckey(){
			return jwckey;
		} 
		public void setgetjwckey(String jwckey){
			this.jwckey = jwckey;
		} 
}
