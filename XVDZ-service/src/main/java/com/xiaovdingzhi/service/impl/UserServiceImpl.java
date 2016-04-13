package com.xiaovdingzhi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaov.dao.UserDao;
import com.xiaov.model.UserInfo;
import com.xiaov.orm.core.Page;
import com.xiaov.orm.core.PropertyFilter;
import com.xiaov.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;
	
	public int insert(UserInfo entity) {
		dao.save(entity);
		return 0;
	}

	public void fakeDelete(String Id) {
		// TODO Auto-generated method stub
		
	}

	public void realDelete(String Id) {
		// TODO Auto-generated method stub
		
	}

	public UserInfo selectById(String Id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int saveOrUpdate(UserInfo entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int save(UserInfo entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<UserInfo> loadAll(int start, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<UserInfo> page(Page<UserInfo> page, List<PropertyFilter> filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
