package com.xiaov.dao;

import org.springframework.stereotype.Repository;

import com.xiaov.model.Material;
import com.xiaov.orm.hibernate.HibernateSupportDao;

@Repository
public class MaterialDao extends HibernateSupportDao<Material, String>{

}
