package com.xiaov.service.impl;

import com.xiaov.dao.OrdersDao;
import com.xiaov.model.Orders;
import com.xiaov.model.Orders;
import com.xiaov.orm.core.Page;
import com.xiaov.orm.hibernate.support.EntityParamsUtil;
import com.xiaov.service.interfaces.OrdersService;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zouziyang on 4/16/16.
 */
@Service
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements OrdersService {

    @Autowired
    private OrdersDao dao;

    @Override
	public void delete(Orders entity) {
		
		super.delete(entity);
	}
	@Override
	public List<Orders> loadAll(Orders entity) {
		
		return super.loadAll(entity);
	}
	@Override
	public void save(Orders entity) {
		
		super.save(entity);
	}
	@Override
	public void update(Orders entity) {
		
		super.update(entity);
	}
	@Override
	public Orders getOne(Class clazz, String pk) {
		
		return super.getOne(clazz, pk);
	}
}
