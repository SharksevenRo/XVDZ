package com.xiaov.dao;

import org.springframework.stereotype.Repository;

import com.xiaov.model.Account;
import com.xiaov.orm.hibernate.HibernateSupportDao;

@Repository
public class AccountDao extends HibernateSupportDao<Account , String>{

}
