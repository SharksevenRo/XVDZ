package com.xiaov.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiaov.dao.MaterialDao;
import com.xiaov.model.Material;
import com.xiaov.orm.core.Page;
import com.xiaov.orm.hibernate.support.EntityParamsUtil;
import com.xiaov.service.interfaces.MaterialService;

public class MaterialServiceImpl implements MaterialService{

	@Autowired
	private MaterialDao dao;
	
	public void Delete(Material entity) {
		dao.delete(entity);
		
	}

	public void save(Material entity) {
		entity.setAddTime(new Date());
		dao.save(entity);
	}

	public List<Material> loadAll(Material entity) {
		Map<String, Object> paramsToMap = new EntityParamsUtil<Material>().paramsToMap(entity);
		String hql="from Material";
		return dao.createSQLQuery(hql, paramsToMap).list();
	}

	public Page<Material> page(Page<Material> page) {
		List<SimpleExpression> values = new EntityParamsUtil<Page<Material>>().paramsToEqs(page, true);
		return dao.findPage(page, dao.createQuery("from Material", values));
	}

	public Material getOne(Class clazz, String pk) {
		return dao.load(pk);
	}

	public void update(Material entity) {
		
		entity.setUpdateTime(new Date());
		dao.saveOrUpdate(entity);
	}

}
