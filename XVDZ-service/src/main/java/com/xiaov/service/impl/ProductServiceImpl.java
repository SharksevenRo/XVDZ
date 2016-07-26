package com.xiaov.service.impl;

import java.util.List;

import com.xiaov.model.*;
import com.xiaov.orm.core.Page;
import com.xiaov.orm.core.PageRequest;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaov.constant.APPConstant;
import com.xiaov.dao.ProductDao;
import com.xiaov.dao.ProductDetailDao;
import com.xiaov.service.interfaces.ProductService;
import com.xiaov.utils.LazyObjecUtil;
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

	/**
	 * 获取商品某种属性详情
	 * @param product 当前商品详细的类型（color,Material,Size,颜色、面料、尺码）
	 * @return
	 * @throws Exception 
	 */
	public Product fillDetail(Product product) throws Exception{
		Criterion [] criterions = null;
		if(product.getId()!=null){
			criterions=new Criterion [] {Restrictions.eq("productId", product.getId())};
		}
		List<ProductDetail> details = detailDao.getEntitiestNotLazy(new ProductDetail(), new String[]{"picB","picF"}, criterions);
		product.setDetail(details);
		return product;
	}
	/**
	 * 获取批量商品的所有类型详细
	 * @param products
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public List<Product> fillDetail(List<Product> products) throws Exception{
		
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
	 * @throws Exception 
	 */
	@Transactional
	public List<Product> fillDetail(List<Product> products,String type) throws Exception{
		
		for (Product product : products) {
			fillDetail(product);
		}
		return products;
	}
	public List<Product> searchProduct(Criterion[] criterions) {

		return dao.getEntitiestNotLazy(new Product(),null, criterions);

	}
	public List<Product> getSimpleProduct(Types types) throws Exception {

		List<Product> products = dao.findByProperty("productType", types);
		products=fillDetail(products);
		return products;
	}
	public List<Product> getByProperty(String propertyName,String values){
		List<Product> products = dao.findByProperty(propertyName,values);

		return  products;
	}
	public List<Product> getProductByMutiType(PageRequest pageRequest,String types){

		String [] split=types.split("[_]");

		Criterion[] eqs=new SimpleExpression[split.length+1];
		for (int i=0;i<split.length;i++) {
			eqs[i]= Restrictions.like("pdtLabel","%,"+split[i]+"%");

		}
		eqs[split.length]=Restrictions.eq("deleteFlag",0);
		return dao.getEntitiestNotLazy(new Product(),new String[]{"productType","img","show","backImage","user"},eqs,pageRequest.getOffset(),pageRequest.getPageSize());
	}

	public List<Product> designerPage(Product product) {

		if(product.getUsId()!=null){
			Criterion[] eqs={Restrictions.eq("usId",product.getUsId()),Restrictions.eq("deleteFlag",0)};
			return dao.getEntitiestNotLazy(new Product(),new String[]{"productType","img","show","backImage","user"},eqs,product.getOffset(),product.getPageSize());
		}

		return null;
	}
	public List<Product> pageByType(Product product) {

		if(product.getProductType()!=null){

			Criterion[] eqs={Restrictions.eq("productType",product.getProductType()),Restrictions.eq("deleteFlag",0)};
			return dao.getEntitiestNotLazy(new Product(),new String[]{"productType","img","show","backImage","user"},eqs,product.getOffset(),product.getPageSize());
		}
		return null;
	}
	public Product geOneDetail(Product product) {

		if(product.getId()!=null){

			Criterion[] eqs={Restrictions.eq("id",product.getId()),Restrictions.eq("deleteFlag",0)};
			Product product1 = dao.getEntitiestNotLazy(new Product(), new String[]{"productType", "user"}, eqs).get(0);
			return  product1;
		}
		return null;
	}

	public Product geOneDetailNotClose(Product product) {
		if(product.getId()!=null){

			Criterion[] eqs={Restrictions.eq("id",product.getId()),Restrictions.eq("deleteFlag",0)};
			Product product1 = dao.getEntitiestNotLazy(new Product(), new String[]{"productType", "user"}, eqs, product.getOffset(), product.getPageSize()).get(0);
			return  product1;
		}
		return null;
	}

	public List<Product> search(SearchModel search) {

		Criterion [] criterions={Restrictions.like("pdtName","%"+search.getSearch()+"%"),Restrictions.like("pdtPc","%"+search.getSearch()+"%")};
		Criterion [] criterions2={Restrictions.or(criterions),Restrictions.eq("deleteFlag",0)};
		return dao.getEntitiestNotLazy(new Product(),new String[]{"productType","img","show","backImage","user"},criterions2,search.getOffset(),search.getPageSize());
	}

	public List<Product> hot(Product product) {
		Criterion [] criterions2={Restrictions.eq("isModule",0),Restrictions.eq("deleteFlag",0)};
		return dao.getEntitiestNotLazyWithOrder(new Product(),new String[]{"productType","img","show","backImage","user"},criterions2,0,8, Order.desc("pdtSaleCount"));
	}

	public List<Product> moudule(Product product) {
		Criterion [] criterions2={Restrictions.eq("isModule",product.getIsModule()),Restrictions.eq("deleteFlag",0)};
		return dao.getEntitiestNotLazyWithOrder(new Product(),new String[]{"productType","img","show","backImage","user"},criterions2,product.getOffset(),product.getPageSize(), Order.desc("pdtSaleCount"));
	}
}
