package com.xiaov.example.dao.impl;

import org.springframework.stereotype.Repository;

import com.xiaov.example.model.UserModel;
import com.xiaov.orm.hibernate.HibernateSupportDao;

/**
 * dao层实现类
 * @author Sharkseven
 *
 */
@Repository
public class UserDao extends HibernateSupportDao<UserModel, Long>{

}
