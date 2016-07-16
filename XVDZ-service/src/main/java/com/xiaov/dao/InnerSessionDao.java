package com.xiaov.dao;

import com.xiaov.model.ProductComment;
import com.xiaov.orm.hibernate.HibernateSupportDao;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by zouziyang on 4/18/16.
 */
@Repository
public class InnerSessionDao {

    public SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
