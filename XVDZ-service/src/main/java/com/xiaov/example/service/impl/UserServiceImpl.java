package com.xiaov.example.service.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaov.example.dao.impl.UserDao;
import com.xiaov.example.model.UserModel;
import com.xiaov.example.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	@Transactional
	public List<UserModel> getUsers() {
		//可传Order参数，即排序规则  通过Order.asc(propertyName)方法获取排序规则对象
		return userDao.getAll();
	}
	public void save(UserModel user) {
		
		//save方法是个重载函数。接受List<T>时批量插入
		userDao.save(user);
	}

public void deletel(UserModel user) {
		
		//save方法是个重载函数。接受List<T>时批量插入
		userDao.delete(user);
	}
}
