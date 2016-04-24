package com.xiaov.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaov.model.UserInfo;

@Controller
public class ManagerController {
	
	@RequestMapping("/manager/getAllUser")
	@ResponseBody
	public String AllUser(String username, HttpServletResponse response ){
//		System.out.println(username);
		/*if(username.equals("User")) {
			UserServiceImpl user = new UserServiceImpl();
			List<UserInfo> users = user.loadAll(new UserInfo());
			return users;
		}*/
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("{");
		sBuilder.append("\"act_id\":\"act_id\",");
		sBuilder.append("\"us_id\":\"us_id\",");
		sBuilder.append("\"act_mm\":\"act_mm\",");
		sBuilder.append("\"act_tt_en\":\"act_tt_en\",");
		sBuilder.append("\"act_blc\":\"act_blc\",");
		sBuilder.append("\"act_fm\":\"act_fm\",");
		sBuilder.append("\"act_state\":\"act_state\",");
		sBuilder.append("\"update_time\":\"update_time\",");
		sBuilder.append("\"remark\":\"remark\",");
		sBuilder.append("\"add_time\":\"add_time\",");
		sBuilder.append("\"delete_time\":\"delete_time\",");
		sBuilder.append("\"act_id\":\"act_id\",");
		sBuilder.append("\"act_id\":\"act_id\",");
		sBuilder.append("}");
		return sBuilder.toString();
	}
	
	@RequestMapping("/manager/getAllStaff") 
	@ResponseBody
	public List<UserInfo> AllStaff(String username, HttpServletResponse response ){
//		System.out.println(username);
		if(username.equals("Staff")) {
			//返回所有的职工信息
		}
		return null;
	}
	
	//添加某个用户信息
	@RequestMapping("/manager/saveUserInfo")
	@ResponseBody
	public String saveUser() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("{");
		sBuilder.append("\"act_id\":\"act_id\",");
		
		sBuilder.append("}");
		return null;
	}
}
