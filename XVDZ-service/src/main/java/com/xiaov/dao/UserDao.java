package com.xiaov.dao;

import org.springframework.stereotype.Repository;

import com.xiaov.model.UserInfo;
import com.xiaov.orm.hibernate.HibernateSupportDao;
@Repository
public class UserDao extends HibernateSupportDao<UserInfo, String> {

}
