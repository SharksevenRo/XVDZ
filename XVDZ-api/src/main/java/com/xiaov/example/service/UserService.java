package com.xiaov.example.service;

import java.util.List;

import com.xiaov.example.model.UserModel;
import com.xiaov.orm.core.Page;
import com.xiaov.orm.core.PropertyFilter;

public interface UserService {

	//业务逻辑接口
	public List<UserModel> getUsers();
	
	public void save(UserModel user);
	public void update(UserModel user);
	
	public void delete(UserModel user);
	public Page<UserModel> page(Page<UserModel> page,List<PropertyFilter> filter);
	//获取一个
	public UserModel getOne(UserModel user);
	
	//根据某字段查询
	public List<UserModel> getByProperty(UserModel user);
}

