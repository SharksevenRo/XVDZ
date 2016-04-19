package com.xiaov.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaov.model.UserInfo;
import com.xiaov.service.impl.UserServiceImpl;

@Controller
public class ManagerController {
	
	@RequestMapping("/manager/getAllUser")
	@ResponseBody
	public List<UserInfo> AllUser(String username, HttpServletResponse response ){
		System.out.println(username);
		if(username.equals("User")) {
			UserServiceImpl user = new UserServiceImpl();
			List<UserInfo> users = user.loadAll(new UserInfo());
			return users;
		}else if(username.equals("Staff")) {
			//获取所有的员工信息
		}
		return null;
	}
	
	@RequestMapping("/manager/saveUserInfo")
	@ResponseBody
	public void saveUser() {
		System.out.println("OK");
	}
}
