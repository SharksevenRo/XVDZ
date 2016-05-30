package com.xiaov.weixin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.UserInfo;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.utils.LogBuilder;
import com.xiaov.web.support.AuthenticationCahce;
import com.xiaov.web.support.CookieUtil;
import com.xiaov.web.support.UserToken;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

@Controller
public class WechatOauth2Controller {

	private static Logger logger = LoggerFactory.getLogger(WechatOauth2Controller.class);

	public static WxMpService wxService;

	/**
	 * 网页授权回调
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/weixin/oauth2/redirect")
	public void oauth(HttpServletRequest request, HttpServletResponse response) {

		try {
			wxService = BuildAndGetWxService.buildAndGetWxService();
			// 获取请求url的类型
			String state = request.getParameter("state");
			String openId = null;
			WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(request.getParameter("code"));
			openId = wxMpOAuth2AccessToken.getOpenId();
			logger.error(openId);
			LogBuilder.writeToLog(openId);
			if (openId == null) {
				return;
			}
			CookieUtil util = new CookieUtil(request);
			util.setValue("user", "openId", openId, true);
			util.save(response, "user",null,null,30*1000*60, true);
			// 根据state判断用户点击的菜单
			if (state.equals("2")) {
				response.getWriter().write("xiaov商城");
				response.sendRedirect("/user/index.html");
				return;
			} else if (state.equals("1")) {
				response.getWriter().write("授权成功" + "你的openid:" + openId);
				response.sendRedirect("/user/vote/vote.html");
			} else if (state.equals("3")) {

				response.sendRedirect("/user/vote/rank.html");
			} else if (state.equals("4")) {

			} else if (state.equals("5")) {

			} else if (state.equals("6")) {

			}
		} catch (Exception e) {
			e.printStackTrace();
			LogBuilder.writeToLog(e.getMessage());
		}

	}

	/**
	 * 网页授权用户绑定
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/weixin/oauth2")
	public void bind(HttpServletRequest request, HttpServletResponse response) {

		try {

			wxService = BuildAndGetWxService.buildAndGetWxService();
			// 构建网页授权url
			String authorizationUrl = wxService.oauth2buildAuthorizationUrl(
					"http://weixin.xiaovdingzhi.com/weixin/oauth2/redirect.do", WxConsts.OAUTH2_SCOPE_BASE,
					request.getParameter("state"));
			// 进入微信授权
			response.sendRedirect(authorizationUrl);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

	}

	/**
	 * 微信授权模拟，用户开发时自测
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/weixin/oauth2/monitor")
	public void monitor(HttpServletRequest request, HttpServletResponse response) {

		try {

				CookieUtil util = new CookieUtil(request);
				UserToken token = AuthenticationCahce.put("22");
				util.setValue("login", "user.token", token.getToken(), true);
				util.save(response, "login", true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

	}
	
	/**
	 * 微信授权模拟，用户开发时自测
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/weixin/oauth2/get")
	public void get(HttpServletRequest request, HttpServletResponse response) {

		try {
			CookieUtil util = new CookieUtil(request);
			String value = util.getValue("login", "user.token",true);
			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

	}
}
