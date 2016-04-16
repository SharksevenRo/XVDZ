package com.xiaov.service.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaov.orm.core.Page;
import com.xiaov.orm.hibernate.HibernateSupportDao;
import com.xiaov.service.BaseService;
import com.xiaov.utils.ReflectionUtils;

@Service
public class BaseServiceImpl<T> implements BaseService<T>{

	@Autowired
	@Qualifier(value="hibernateSupportDao")
	
	private HibernateSupportDao<T, ?> dao;
	@Transactional
	public void delete(T entity) {
		dao.delete(entity);
	}
	public void update(T entity) {
		dao.update(entity);
	}
	@Transactional
	public void save(T entity) {
		dao.save(entity);
	}
	public List<T> loadAll(T entity) {
		String name = entity.getClass().getSimpleName();
		StringBuilder hql=new StringBuilder();
		hql.append("from "+name+" where 1=1");
		List<Field> accessibleFields = ReflectionUtils.getAccessibleFields(entity.getClass(), true);
		Object value = null;
		Map<String, Object> map=new HashMap<String, Object>();
		for (Field field : accessibleFields) {
			try {
				value= ReflectionUtils.invokeGetterMethod(entity, field.getName());
				
			} catch (Exception e) {
			}
			
			if(value!=null){
				map.put(field.getName(), value);
				hql.append(" and "+field.getName()+"=:"+field.getName());
			}
		}
		
		return dao.createQuery(hql.toString(), map).list();
	}

	@SuppressWarnings("unchecked")
	public Page<T> page(Page<T> page) {
		String name = page.getClass().getSimpleName();
		StringBuilder hql=new StringBuilder();
		hql.append("from "+name+" where 1=1");
		List<Field> accessibleFields = ReflectionUtils.getAccessibleFields(page.getClass(), true);
		Object value = null;
		Map<String, Object> map=new HashMap<String, Object>();
		for (Field field : accessibleFields) {
			try {
				value= ReflectionUtils.invokeGetterMethod(page, field.getName());
			} catch (Exception e) {
			}
			
			if(value!=null){
				map.put(field.getName(), value);
				hql.append(" and "+field.getName()+"=:"+field.getName());
			}
		}
		String hql2 = dao.setPageRequestToHql(hql.toString(), page);
		return dao.findPage(page, dao.createQuery(hql2, map));
	}

	public T getOne(Class clazz,String pk) {
		return (T) dao.getSession().load(clazz, pk);
	}
}
