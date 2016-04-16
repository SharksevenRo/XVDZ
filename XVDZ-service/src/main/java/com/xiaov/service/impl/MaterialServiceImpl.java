package com.xiaov.service.impl;

import java.util.List;

import com.xiaov.model.Material;
import com.xiaov.service.interfaces.MaterialService;

public class MaterialServiceImpl extends BaseServiceImpl<Material> implements MaterialService{

	@Override
	public void delete(Material entity) {
		
		super.delete(entity);
	}
	@Override
	public List<Material> loadAll(Material entity) {
		
		return super.loadAll(entity);
	}
	@Override
	public void save(Material entity) {
		
		super.save(entity);
	}
	@Override
	public void update(Material entity) {
		
		super.update(entity);
	}
	@Override
	public Material getOne(Class clazz, String pk) {
		
		return super.getOne(clazz, pk);
	}
}
