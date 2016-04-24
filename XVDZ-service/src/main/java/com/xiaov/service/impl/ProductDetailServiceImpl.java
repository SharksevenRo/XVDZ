package com.xiaov.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaov.dao.ProductDetailDao;
import com.xiaov.model.ProductDetail;
import com.xiaov.service.interfaces.ProductDetailService;

@Service
public class ProductDetailServiceImpl extends BaseServiceImpl<ProductDetail> implements ProductDetailService{
	
	@Autowired
	private ProductDetailDao dao;
	@Override
	public void delete(ProductDetail entity) {
		
		super.delete(entity);
	}
	@Override
	public List<ProductDetail> loadAll(ProductDetail entity) {
		
		return super.loadAll(entity);
	}
	@Override
	public void save(ProductDetail entity) {
		
		super.save(entity);
	}
	@Override
	public void update(ProductDetail entity) {
		
		super.update(entity);
	}
	@Override
	public ProductDetail getOne(Class clazz, String pk) {
		
		return super.getOne(clazz, pk);
	}
}
