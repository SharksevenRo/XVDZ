package com.xiaov.dao;

import org.springframework.stereotype.Repository;

import com.xiaov.model.ProductDetail;
import com.xiaov.orm.hibernate.HibernateSupportDao;
@Repository
public class ProductDetailDao  extends HibernateSupportDao<ProductDetail, String>{

}
