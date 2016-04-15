package com.xiaov.dao;

import org.springframework.stereotype.Repository;

import com.xiaov.model.Product;
import com.xiaov.orm.hibernate.HibernateSupportDao;
@Repository
public class ProductDao extends HibernateSupportDao<Product, String>{

}
