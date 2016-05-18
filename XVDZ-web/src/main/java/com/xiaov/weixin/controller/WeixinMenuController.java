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
		button.setName("投票");
		button.setUrl("http://weixin.xiaovdingzhi.com/weixin/oauth2.do?state=1");
		buttons.add(button);
		
		button=new WxMenuButton();
		button.setType("view");
		button.setName("个性定制");
		button.setUrl("http://weixin.xiaovdingzhi.com/weixin/oauth2.do?state=2");
		buttons.add(button);
		
		button=new WxMenuButton();
		button.setType("view");
		button.setName("美女排行榜");
		button.setUrl("http://weixin.xiaovdingzhi.com/weixin/oauth2.do?state=3");
		buttons.add(button);
		
		menu.setButtons(buttons);
		try {
			service.menuDelete();
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	}
}
