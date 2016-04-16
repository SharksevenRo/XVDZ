package com.xiaov.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.xiaov.orm.core.Page;
import com.xiaov.orm.hibernate.HibernateSupportDao;
import com.xiaov.service.BaseService;
import com.xiaov.utils.ReflectionUtils;

public class BaseServiceImpl<T> implements BaseService<T>{

	@Autowired
	@Qualifier(value="hibernateSupportDao")
	private HibernateSupportDao<T, ?> dao;

	public void Delete(T entity) {
		dao.delete(entity);
	}

	public void update(T entity) {
		dao.saveOrUpdate(entity);
	}

	public void save(T entity) {
		dao.save(entity);
	}

	public List<T> loadAll(T entity) {
		Class clazz=entity.getClass();
		List<SimpleExpression> criterions=new ArrayList<SimpleExpression>();
		List<Field> accessibleFields = ReflectionUtils.getAccessibleFields(clazz, false);
		for (Field field : accessibleFields) {
			Object value = ReflectionUtils.invokeGetterMethod(entity, field.getName());
			if(value!=null){
				criterions.add(Restrictions.eq(field.getName(), value));
			}
		}
		
		dao.createCriteriaEq(criterions);
		return null;
	}

	@SuppressWarnings("unchecked")
	public Page<T> page(Page<T> page) {
		String name = page.getClass().getName();
		List<Field> accessibleFields = ReflectionUtils.getAccessibleFields(page.getClass(), true);
		Object value;
		Map<String, Object> map=new HashMap<String, Object>();
		for (Field field : accessibleFields) {
			value= ReflectionUtils.invokeGetterMethod(page, field.getName());
			if(value!=null){
				map.put(field.getName(), value);
			}
		}
		String hql="from "+name;
		String hql2 = dao.setPageRequestToHql(hql, page);
		return dao.findPage(page, dao.createQuery(hql2, map));
	}

	public T getOne(Class clazz,String pk) {
		return (T) dao.getSession().load(clazz, pk);
	}
}
