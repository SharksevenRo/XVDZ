package com.xiaov.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xiaov.dao.UserDao;
import com.xiaov.model.UserInfo;
import com.xiaov.orm.core.Page;
import com.xiaov.orm.hibernate.support.EntityParamsUtil;
import com.xiaov.service.interfaces.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserInfo> implements UserService{

	@Autowired
	private UserDao userDao;
	
	/**
	 * 根据openid查询用户信息
	 * @param openId
	 * @return
	 */
	public UserInfo getUserInfoByOpenID(String openId){
		
		List<UserInfo> users = userDao.findByProperty("appId", openId);
		if(users.size()!=1){
			return null;
		}else{
			return users.get(0);
		}
	}

	@Override
	public void delete(UserInfo entity) {
		
		super.delete(entity);
	}
	@Override
	public List<UserInfo> loadAll(UserInfo entity) {
		
		return super.loadAll(entity);
	}
	@Override
	public void save(UserInfo entity) {
		
		super.save(entity);
	}
	@Override
	public void update(UserInfo entity) {
		
		super.update(entity);
	}
	@Override
	public UserInfo getOne(Class clazz, String pk) {
		
		return super.getOne(clazz, pk);
	}
}
