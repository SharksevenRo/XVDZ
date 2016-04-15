package com.xiaov.dao;

import org.springframework.stereotype.Repository;

import com.xiaov.model.Account;
import com.xiaov.orm.hibernate.HibernateSupportDao;

@Repository
public class DynamicDao extends HibernateSupportDao<Account, String>{

}
