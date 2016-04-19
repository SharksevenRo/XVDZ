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
	public List<UserInfo> test(String username, HttpServletResponse response ){
		response.setContentType("text/html;charset=UTF-8");
		UserServiceImpl user = new UserServiceImpl();
		List<UserInfo> users = user.loadAll(null);
		return users;
	}
}
