package com.xiaov.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.xiaov.model.UserInfo;
import com.xiaov.orm.hibernate.HibernateSupportDao;

import java.util.List;

@Repository
public class UserDao extends HibernateSupportDao<UserInfo, String> {

    public UserInfo login(String usPwd,String usTel){

        String hql = "from UserInfo u where u.usTel=? and u.usPwd=?";
        Query q = this.createQuery(hql);
        q.setString(0, usTel);
        q.setString(1, usPwd);
        return (UserInfo) q.uniqueResult();

    }
}
