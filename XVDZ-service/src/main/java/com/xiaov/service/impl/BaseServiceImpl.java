package com.xiaov.service.impl;

import java.lang.reflect.Field;
import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaov.orm.core.Page;
import com.xiaov.orm.hibernate.HibernateSupportDao;
import com.xiaov.service.BaseService;
import com.xiaov.utils.ReflectionUtils;

/**
 * service基础类。提供基本业务对象的基本操作
 * @param <T>
 */
@Service
public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	@Qualifier(value = "hibernateSupportDao")

	protected HibernateSupportDao<T, String> dao;

	@Transactional
	public void delete(T entity) {
		dao.delete(entity);
	}
	@Transactional
	public void update(T entity) {
		String string = ReflectionUtils.invokeGetterMethod(entity, "id").toString();
		T t = dao.get(entity.getClass(),string);
		List<Field> accessibleFields = ReflectionUtils.getAccessibleFields(entity.getClass(), true);
		Object value;
		for (Field field : accessibleFields) {
			try {
				if (isBase(field.getType())) {
					value = ReflectionUtils.invokeGetterMethod(entity, field.getName());
					if (value != null) {
						ReflectionUtils.invokeSetterMethod(t, field.getName(), value, field.getType());
						value=null;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dao.update(t);
	}

	@Transactional
	public void save(T entity) {
		dao.save(entity);
	}

	public List<T> loadAll(T entity) {

		String name = entity.getClass().getSimpleName();
		StringBuilder hql = new StringBuilder();
		hql.append("from " + name + " where 1=1");
		List<Field> accessibleFields = ReflectionUtils.getAccessibleFields(entity.getClass(), true);
		Object value = null;
		Map<String, Object> map = new HashMap<String, Object>();
		for (Field field : accessibleFields) {
			try {
				if (isBase(field.getType())) {
					value = ReflectionUtils.invokeGetterMethod(entity, field.getName());
					if (value != null) {
						map.put(field.getName(), value);
						hql.append(" and " + field.getName() + "=:" + field.getName());
						value=null;
					}
				}
			} catch (Exception e) {
			}

		}

		return dao.createQuery(hql.toString(), map).list();
	}


	public Page<T> page(Page page) {
		String name = page.getClass().getSimpleName();
		StringBuilder hql = new StringBuilder();
		hql.append("from " + name + " where 1=1");
		List<Field> accessibleFields = ReflectionUtils.getAccessibleFields(page.getClass(), true);
		Object value = null;
		Map<String, Object> map = new HashMap<String, Object>();
		for (Field field : accessibleFields) {
			try {

				if (isBase(field.getType())) {
					value = ReflectionUtils.invokeGetterMethod(page, field.getName());

					if (value != null) {
						map.put(field.getName(), value);
						hql.append(" and " + field.getName() + "=:" + field.getName());
						value=null;
					}
				}
			} catch (Exception e) {
			}
		}
		page= dao.findPage(page, dao.createQuery(hql.toString(), map));
		//设置总页数
		page.setTotalPages();
		return page;
	}

	public T getOne(Class clazz, String pk) {
		return (T) dao.getSession().get(clazz, pk);
	}


	private boolean isBase(Class clazz) {

		if (clazz.equals(Boolean.class) || clazz.equals(String.class) || clazz.equals(Integer.class)
				|| clazz.equals(Double.class) || clazz.equals(Date.class) | clazz.equals(Long.class)) {
			return true;
		}
		return false;
	}
	public List<T> getByids(Class clazz,List<String> ids){

		List<T> ts = dao.get(clazz,ids);
		return ts;
	}

	public Page<T> pageNotLazy(Page page,String [] fields,T t) {
		return pageNotLazy(page,fields,null,t);
	}

	public Page<T> pageNotLazy(Page page,String [] fields,Criterion [] criterions1,T t) {

		String name = t.getClass().getSimpleName();
		StringBuilder hql = new StringBuilder();
		List<Field> accessibleFields = ReflectionUtils.getAccessibleFields(page.getClass(), true);
		Object value = null;
		List<Criterion> criterions=new ArrayList<Criterion>();
		Criterion criterion=null;
		for (Field field : accessibleFields) {
			try {
				criterion=null;
				if (isBase(field.getType())) {
					value = ReflectionUtils.invokeGetterMethod(page, field.getName());
					if (value != null) {
						criterion=Restrictions.eq(field.getName(),value);
						value=null;
					}
				}
				if(field.getType().newInstance() instanceof Page){
					value = ReflectionUtils.invokeGetterMethod(page, field.getName());
					if (value != null) {
						criterion=Restrictions.eq(field.getName(),value);
						value=null;
					}
				}
			} catch (Exception e) {
			}
			if(criterion!=null){
				criterions.add(criterion);
			}
		}
		if(criterions1!=null&&criterions1.length>1){
			for (Criterion c:criterions1
					) {
				criterions.add(c);
			}
		}
		Criteria criteriaEq = dao.createCriteriaEq(criterions,t.getClass());
		if(fields!=null){
			for (String string : fields) {
				criteriaEq = criteriaEq.setFetchMode(string, org.hibernate.FetchMode.JOIN);
			}
		}
		page= dao.findPage(page,criteriaEq);
		//设置总页数
		page.setTotalPages();
		return page;
	}
}
