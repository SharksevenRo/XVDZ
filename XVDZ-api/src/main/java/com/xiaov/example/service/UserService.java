package com.xiaov.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xiaov.example.model.UserModel;

public interface UserService {

	//业务逻辑接口
	public List<UserModel> getUsers();
	
	public void save(UserModel user);
}
