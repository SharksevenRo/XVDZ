package com.xiaov.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xiaov.dao.ProductDao;
import com.xiaov.model.Product;
import com.xiaov.service.interfaces.ProductService;

public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService{

	@Autowired
	private ProductDao dao;
	
	@Override
	public void delete(Product entity) {
		
		super.delete(entity);
	}
	@Override
	public List<Product> loadAll(Product entity) {
		
		return super.loadAll(entity);
	}
	@Override
	public void save(Product entity) {
		
		super.save(entity);
	}
	@Override
	public void update(Product entity) {
		
		super.update(entity);
	}
	@Override
	public Product getOne(Class clazz, String pk) {
		
		return super.getOne(clazz, pk);
	}

}
