package com.xiaov.example.service.impl;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaov.example.dao.impl.UserDao;
import com.xiaov.example.model.UserModel;
import com.xiaov.example.service.UserService;
import com.xiaov.orm.core.Page;
import com.xiaov.orm.core.PropertyFilter;
@Service
public class UserServiceImpl implements UserService{

	//必须定义 在做增删改的时候打日志
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class); 
	@Autowired
	private UserDao userDao;
	@Transactional
	public List<UserModel> getUsers() {
		//可传Order参数，即排序规则  通过Order.asc(propertyName)方法获取排序规则对象
		return userDao.getAll();
	}
	
	/**
	 * 增删改的方法都支持重载，支持单个操作（包括对象参数和主键参数），也支持批量操作（List<T>），
	 */
	public void save(UserModel user) {
		
		//save方法是个重载函数。接受List<T>时批量插入
		userDao.save(user);
	}

	public void update(UserModel user) {
		userDao.update(user);
		logger.info(user.getName()+"被修改");
	}
	public void delete(UserModel user) {
		userDao.delete(user);
		logger.info(user.getName()+"被删除");
		
	}
	public Page<UserModel> page(Page<UserModel> page,List<PropertyFilter> filters) {
		
		//HQL的方式分页
		String HQL="select * from";
		//获取分页的hql
		String hql2 = userDao.setPageRequestToHql(HQL, page);
		List<UserModel> list = userDao.createSQLQuery(hql2, new HashMap<String, UserModel>()).list();
		
		//Criteria方式分页
		Criteria criteria = userDao.getSession().createCriteria(UserModel.class);
		List list2 = userDao.setPageRequestToCriteria(criteria, page).list();
		
		//动态关闭延迟加载的实例
		
		//效果是查询出的UserModel里的Role是完整的对象，不是Hibernate代理对象
		//要关闭延迟加载的关联对象的 成员变量名
		String [] fields=new String[]{"role"};
		
		//过滤条件。可通过Restrictions类获取各种过滤条件，eq是等于，其他的自行百度，看源码
		SimpleExpression [] eqs=new SimpleExpression[]{Restrictions.eq("", null)};
		
		userDao.getEntitiestNotLazy(new UserModel(), fields, eqs);
		//UserModel 是Page的子类，若操作的Model有分页的需求需要继承Page类
		return userDao.findPage(page, filters);
	}
	public UserModel getOne(UserModel user) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<UserModel> getByProperty(UserModel user) {
		// TODO Auto-generated method stub
		return null;
	}
}
