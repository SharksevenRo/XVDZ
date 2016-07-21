package com.xiaov.service.impl;

import com.xiaov.dao.OrdersDao;
import com.xiaov.model.Material;
import com.xiaov.model.OrderDetail;
import com.xiaov.model.Orders;
import com.xiaov.model.Product;
import com.xiaov.service.interfaces.MaterialService;
import com.xiaov.service.interfaces.OrderDetailService;
import com.xiaov.service.interfaces.OrdersService;
import com.xiaov.service.interfaces.ProductService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zouziyang on 4/16/16.
 */
@Service
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements OrdersService {

    @Autowired
    private OrdersDao dao;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private ProductService productService;
	@Autowired
	private MaterialService materialService;

    @Override
	public void delete(Orders entity) {

		super.delete(entity);
	}
	@Override
	public List<Orders> loadAll(Orders entity) {
		
		return super.loadAll(entity);
	}
	@Override
	@Transactional
	public void save(Orders entity) {

		super.save(entity);
		for (OrderDetail orderDetail: entity.getOrderDetails()
				) {
			orderDetail.setAddTime(new Date());
			orderDetailService.save(orderDetail);
		}
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
	

	public List<Orders> pageOrder(Orders orders) {

		Criterion [] criterions={Restrictions.eq("deleteFlag",0),Restrictions.eq("user",orders.getUser()),Restrictions.in("orState",new Integer[]{0,1,2,3,4})};
		return dao.getEntitiestNotLazy(new Orders(), null,criterions,orders.getOffset(),orders.getPageSize() );

	}

	public List<Orders> getOrderDetail(Orders orders){

		return  null;
	}

	/**
	 * 切割字符串存入List集合
	 * @param str
	 * @return
     */
	private List<String> splitStr(String str) {

		List<String> strs = null;
		if (str != null && !"".equals(str)) {

			String[] split = str.split("[_]");

			if (split.length > 1) {
				strs = new ArrayList<String>();

				for (String str1 : split) {
					strs.add(str1);
				}
			}
		}
		return strs;
	}
}

