package com.xiaov.weixin.controller;

import java.util.ArrayList;
import java.util.List;

import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

public class WeixinMenuController {

	public static void main(String[] args) {
		WxMpService service = BuildAndGetWxService.buildAndGetWxService();
		List<WxMenuButton> buttons = new ArrayList<WxMenuButton>();
		WxMenu menu = new WxMenu();
		WxMenuButton button=new WxMenuButton();
		
		button.setType("view");
		button.setName("授权测试");
		button.setUrl("http://weixin.xiaovdingzhi.com/weixin/oauth2?state=2");
		buttons.add(button);
		
		button=new WxMenuButton();
		button.setType("view");
		button.setName("小V商城");
		button.setUrl("http://weixin.xiaovdingzhi.com/weixin/oauth2?state=1");
		buttons.add(button);
		
		menu.setButtons(buttons);
		try {
			service.menuCreate(menu);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}