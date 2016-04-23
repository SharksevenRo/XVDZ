package com.xiaov.service.impl;

import com.xiaov.dao.OrdersDao;
import com.xiaov.model.Orders;
import com.xiaov.model.Orders;
import com.xiaov.orm.core.Page;
import com.xiaov.orm.hibernate.support.EntityParamsUtil;
import com.xiaov.service.interfaces.OrdersService;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
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
	
	
	public List<Orders> example(){
		
		/*Restrictions.eq

		＝

		Restrictions.ne

		Restrictions.allEq

		＜＞

		利用Map来进行多个等于的限制

		Restrictions.gt

		＞

		Restrictions.ge

		＞＝

		Restrictions.lt

		＜

		Restrictions.le

		＜＝

		Restrictions.between

		BETWEEN

		Restrictions.like

		LIKE

		Restrictions.in

		in

		Restrictions.and

		and

		Restrictions.or

		or

		Restrictions.sqlRestriction

		用SQL限定查询
		*/
		//先申明要关闭延迟加载的关联对象orderDetail
		String[] fields=new String[]{"orderDetail","dbTypes","discountCoupan"};
		
		//创建过滤条件,商品状态过滤
		Criterion eq1 = Restrictions.eq("orState", true);
		//商品类型
		Criterion eq2 = Restrictions.eq("dbTypes.typeName", "非卖品");
		//总额大于
		Criterion ge=Restrictions.sizeGe("orTotal", 400);
		
		Criterion [] criterions={eq1,eq2,ge};
		return dao.getEntitiestNotLazy(new Orders(), fields, criterions);
	}
}

