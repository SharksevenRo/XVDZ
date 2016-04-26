package com.xiaov.dao;

import com.xiaov.model.Types;
import com.xiaov.orm.hibernate.HibernateSupportDao;
import org.springframework.stereotype.Repository;

@Repository
public class TypesDao extends HibernateSupportDao<Types,String> {
}
