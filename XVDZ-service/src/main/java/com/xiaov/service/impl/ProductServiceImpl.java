package com.xiaov.service.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaov.dao.ProductDao;
import com.xiaov.dao.ProductDetailDao;
import com.xiaov.model.Product;
import com.xiaov.model.ProductDetail;
import com.xiaov.service.interfaces.ProductService;
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService{

	@Autowired
	private ProductDao dao;
	
	@Autowired
	private ProductDetailDao detailDao;
	
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
	
	public Product getOneInteger(Class clazz, Integer pk) {
		
		return null;
	}
	
	/**
	 * 获取商品某种属性详情
	 * @param product 当前商品
	 * @param type 详细的类型（color,Material,Size,颜色、面料、尺码）
	 * @return
	 */
	public Product fillDetail(Product product,String type){
		
		Criterion [] criterions={Restrictions.eq("type", type),Restrictions.eq("pdtId", product.getId())};
		List<ProductDetail> details = detailDao.getEntitiestNotLazy(new ProductDetail(), null, criterions);
		if(ProductDetail.COLOR.equals(type)){
			product.setColors(details);
		}else if(ProductDetail.MATERIAL.equals(type)){
			product.setMaterials(details);
		}else if(ProductDetail.SIZE.equals(type)){
			product.setSizes(details);
		}else{
			throw new RuntimeException(type+"参数异常");
		}
		return product;
	}
	/**
	 * 获取当前商品所有类型的详细信息
	 * @param product
	 */
	public void fillDetail(Product product){
		
		fillDetail(product, ProductDetail.COLOR);
		fillDetail(product, ProductDetail.SIZE);
		fillDetail(product, ProductDetail.MATERIAL);
	}
	/**
	 * 获取批量商品的所有类型详细
	 * @param products
	 * @return
	 */
	@Transactional
	public List<Product> fillDetail(List<Product> products){
		
		for (Product product : products) {
			fillDetail(product);
		}
		return products;
	}
	/**
	 * 获取批量商品的某种类型详情
	 * @param products
	 * @param type 详细的类型（color,Material,Size,颜色、面料、尺码）
	 * @return
	 */
	@Transactional
	public List<Product> fillDetail(List<Product> products,String type){
		
		for (Product product : products) {
			fillDetail(product,type);
		}
		return products;
	}
	public List<Product> searchProduct(Criterion[] criterions) {

		return dao.getEntitiestNotLazy(new Product(),null, criterions);

	}
}
