package com.xiaov.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaov.example.model.UserModel;
import com.xiaov.example.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/example/all")
	public String getAll(){
		List<UserModel> users = userService.getUsers();
		System.out.println("11");
		return "/index.jsp";
	}
	
	
}
