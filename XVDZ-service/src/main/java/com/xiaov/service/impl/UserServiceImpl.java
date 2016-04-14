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
public class UserServiceImpl implements UserService{

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

	public void Delete(UserInfo entity) {
		userDao.delete(entity);
		
	}

	public void saveOrUpdate(UserInfo entity) {
		userDao.saveOrUpdate(entity);
	}
	public List<UserInfo> loadAll(UserInfo entity) {
	
		Map<String, Object> params = new EntityParamsUtil<UserInfo>().paramsToMap(entity);
		String hql="from UserInfo";
		return userDao.createQuery(hql, params).list();
	}

	public Page<UserInfo> page(Page<UserInfo> page) {
		
		/**
		 * 分页对象打包参数
		 */
		Map<String, Object> params = new EntityParamsUtil<Page<UserInfo>>().paramsToMap(page,true);
		String hql="select from UserInfo";
		String hql2 = userDao.setPageRequestToHql(hql, page);
		return (Page<UserInfo>) userDao.createQuery(hql2, params).list();
	}

	public UserInfo getOne(Class clazz, String pk) {
		return userDao.get(pk);
	}
}
