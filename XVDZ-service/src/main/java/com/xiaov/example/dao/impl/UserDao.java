package com.xiaov.example.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.xiaov.example.model.UserModel;
import com.xiaov.orm.hibernate.HibernateSupportDao;
import com.xiaov.orm.hibernate.support.BasicHibernateDao;

/**
 * dao层实现类
 * @author Sharkseven
 *
 */
@Repository
public class UserDao extends HibernateSupportDao<UserModel, Long>{
	
	//logger必须定义
	private static Logger logger = LoggerFactory.getLogger(UserDao.class); 
}
