package com.xiaov.weixin.controller;


import com.xiaov.weixin.config.ConfigDao;
import com.xiaov.weixin.config.ConfigInfo;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;



public class BuildAndGetWxService {

	private static WxMpService wxMpService;
	
	/**
	 * 建立微信服务对象
	 * @return
	 */
	public static WxMpService buildAndGetWxService(){
		
		if(wxMpService==null){
			ConfigInfo info = new ConfigDao().GetConfig();
			WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
			wxMpConfigStorage.setAppId(info.getWeChatAppID()); // 设置微信公众号的appid
			wxMpConfigStorage.setSecret(info.getWeChatAppSecret()); // 设置微信公众号的app
			wxMpConfigStorage.setToken(info.getWeChatToken()); // 设置微信公众号的token
			wxMpConfigStorage.setAesKey(info.getWeChatAESKey()); // 设置微信公众号的EncodingAESKey
			wxMpService = new WxMpServiceImpl();
			wxMpService.setWxMpConfigStorage(wxMpConfigStorage);
		}
		return wxMpService;
	}
}
