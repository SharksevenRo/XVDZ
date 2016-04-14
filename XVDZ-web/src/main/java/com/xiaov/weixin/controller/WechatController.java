package com.xiaov.weixin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaov.example.service.impl.UserServiceImpl;
import com.xiaov.weixin.config.ConfigDao;
import com.xiaov.weixin.config.ConfigInfo;

import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;

@Controller
public class WechatController {
	
	private static Logger logger = LoggerFactory.getLogger(WechatController.class); 
	protected WxMpInMemoryConfigStorage wxMpConfigStorage;
	protected WxMpService wxMpService;
	protected WxMpMessageRouter wxMpMessageRouter;
	public void init(){ 
		ConfigDao configDao = new ConfigDao();
		ConfigInfo configInfo = configDao.GetConfig();
		wxMpConfigStorage = new WxMpInMemoryConfigStorage();
		wxMpConfigStorage.setAppId(configInfo.getWeChatAppID()); // 设置微信公众号的appid
		wxMpConfigStorage.setSecret(configInfo.getWeChatAppSecret()); // 设置微信公众号的app
																		// corpSecret 
		wxMpConfigStorage.setToken(configInfo.getWeChatToken()); // 设置微信公众号的token
		wxMpConfigStorage.setAesKey(configInfo.getWeChatAESKey()); // 设置微信公众号的EncodingAESKey
 
		wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxMpConfigStorage);
	} 
	@RequestMapping("/xvdz/wechat")
	public void XVDZWechat(HttpServletRequest request,HttpServletResponse response){
		try { 
			
			init();
			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);
 
			String signature = request.getParameter("signature");
			String nonce = request.getParameter("nonce");
			String timestamp = request.getParameter("timestamp");
 
			if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
				// 消息签名不正确，说明不是公众平台发过来的消息 
				response.getWriter().println("非法请求");
				return; 
			} 
 
			String echostr = request.getParameter("echostr");
			if (StringUtils.isNotBlank(echostr)) {
				// 说明是一个仅仅用来验证的请求，回显echostr 
				response.getWriter().println(echostr);
				return; 
			} 
 
			String encryptType = StringUtils.isBlank(request
					.getParameter("encrypt_type")) ? "raw" : request
					.getParameter("encrypt_type"); 
			response.setContentType("text/xml");
			if ("raw".equals(encryptType)) {
				// 明文传输的消息 
				WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request
						.getInputStream()); 
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT()
						.fromUser(inMessage.getToUserName()).content("您好,欢迎关注小V定制微信平台")
						.toUser(inMessage.getFromUserName()).build();
				response.getWriter().write(m.toXml());
			} 
			if ("aes".equals(encryptType)) {
				// 是aes加密的消息 
				WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request
						.getInputStream()); 
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT()
						.fromUser(inMessage.getToUserName()).content("您好,欢迎关注小V定制微信平台")
						.toUser(inMessage.getFromUserName()).build();
				response.getWriter().write(m.toXml());
			} 
			response.getWriter().println("不可识别的加密类型");
			return; 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} 
 
	}
}
