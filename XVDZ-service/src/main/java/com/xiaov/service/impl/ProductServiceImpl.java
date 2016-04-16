package com.xiaov.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiaov.dao.ProductDao;
import com.xiaov.model.Product;
import com.xiaov.orm.core.Page;
import com.xiaov.orm.hibernate.support.EntityParamsUtil;
import com.xiaov.service.interfaces.ProductService;

public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao dao;
	
	public void Delete(Product entity) {
		dao.delete(entity);
	}

	public void save(Product entity) {
		entity.setAddTime(new Date());
		dao.save(entity);
	}

	public List<Product> loadAll(Product entity) {
		List<SimpleExpression> paramsToEqs = new EntityParamsUtil<Product>().paramsToEqs(entity);
		return dao.createCriteriaEq(paramsToEqs).list();
	}

	public Page<Product> page(Page<Product> page) {
		List<SimpleExpression> paramsToEqs = new EntityParamsUtil<Page<Product>>().paramsToEqs(page, true);
		return dao.findPage(page, dao.createCriteriaEq(paramsToEqs));
	}

	public Product getOne(Class clazz, String pk) {
		return dao.load(pk);
	}

	public void update(Product entity) {
		entity.setUpdateTime(new Date());
		dao.update(entity);
		
	}

}
