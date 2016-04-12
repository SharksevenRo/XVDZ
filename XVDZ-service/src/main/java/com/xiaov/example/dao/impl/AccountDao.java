package com.xiaov.example.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.xiaov.model.Account;
import com.xiaov.orm.hibernate.HibernateSupportDao;


@Repository
public class AccountDao extends HibernateSupportDao<Account, String>{
	
	//logger必须定义
	private static Logger logger = LoggerFactory.getLogger(AccountDao.class); 
}
