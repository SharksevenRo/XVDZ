package com.xiaov.weixin.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaov.utils.LogBuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

@Controller
public class WechatOauth2Controller{
	
	private static Logger logger = LoggerFactory.getLogger(WechatOauth2Controller.class);
	
	public static WxMpService wxService;
	/**
	 * 网页授权回调
	 * @param request
	 * @param response
	 */
	@RequestMapping("/weixin/oauth2/redirect")
	public void oauth(HttpServletRequest request,HttpServletResponse response) {

		 
		try {
		wxService = BuildAndGetWxService.buildAndGetWxService();
			//获取请求url的类型
			String state = request.getParameter("state");
			String openId = null;
			LogBuilder.writeToLog("error in oauth");
			if (request.getSession().getAttribute("openId") == null) {
				WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService
						.oauth2getAccessToken(request.getParameter("code"));
				openId = wxMpOAuth2AccessToken.getOpenId();
				//或者存到cookie里
				request.getSession().setAttribute("openId", openId);
			} else {
				openId = (String) request.getSession().getAttribute(
						"openId");
				LogBuilder.writeToLog("error");
			}
			//根据state判断用户点击的菜单
			if (state.equals("1")) {
				response.getWriter().write("xiaov商城");
				response.sendRedirect("/index.html");
				return;
			} else if (state.equals("2")) {
				response.getWriter().write("授权成功"+"你的openid:"+openId);
				response.sendRedirect("/index.html");
			} else if(state.equals("3")){
				
				
				return;
			}else if(state.equals("4")){
			
			}else if(state.equals("5")){
			
				
			}else if(state.equals("6")){
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogBuilder.writeToLog(e.getMessage());
		}

	}

	/**
	 * 网页授权用户绑定
	 * @param request
	 * @param response
	 */
	@RequestMapping("/weixin/oauth2")
	public void bind(HttpServletRequest request,HttpServletResponse response) {

		try {
			
			wxService= BuildAndGetWxService.buildAndGetWxService();
			//构建网页授权url
			String authorizationUrl = wxService
					.oauth2buildAuthorizationUrl(
							"http://weixin.xiaovdingzhi.com/weixin/oauth2/redirect",
							WxConsts.OAUTH2_SCOPE_BASE, request.getParameter("state"));
			//进入微信授权
			response.sendRedirect(authorizationUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 微信授权模拟，用户开发时自测
	 * @param request
	 * @param response
	 */
	@RequestMapping("/weixin/oauth2/monitor")
	public void monitor(HttpServletRequest request,HttpServletResponse response) {

		try {
			//设置一个模拟测试环境
			Cookie cookie = new Cookie("openId","oWJP6sjCHdhDYO9gGzSa5_DvTEE8");
			cookie.setMaxAge(3600);
			//设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
			 
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

	}
}
