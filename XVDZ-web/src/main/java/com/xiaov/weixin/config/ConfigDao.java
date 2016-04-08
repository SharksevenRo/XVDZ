package com.xiaov.weixin.config;

import java.util.ResourceBundle;

public class ConfigDao {
	public ConfigInfo GetConfig() {

		ConfigInfo Config = new ConfigInfo();
		ResourceBundle res = ResourceBundle.getBundle("config");
		Config.setWeChatAppID(res.getString("WeChatAppID"));
		Config.setWeChatAppSecret(res.getString("WeChatAppSecret"));
		Config.setWeChatToken(res.getString("WeChatToken"));
		Config.setWeChatAESKey(res.getString("WeChatAESKey"));
		return Config;
	}
}
