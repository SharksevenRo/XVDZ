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
			orderDetail.setOrDtNo(entity.getId());
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
		String[] fields=new String[]{"user","dbTypes","discountCoupan"};
		
		//创建过滤条件,商品状态过滤
		Criterion eq1 = Restrictions.eq("orState", true);
		//商品类型
		Criterion eq2 = Restrictions.eq("dbTypes.typeName", "非卖品");
		//总额大于
		Criterion ge=Restrictions.sizeGe("orTotal", 400);
		
		Criterion [] criterions={eq1,eq2,ge};
		return dao.getEntitiestNotLazy(new Orders(), fields, criterions);
	}

	public List<Orders> getByColumn(Criterion[] criterions) {

		String[] fields = new String[]{"user", "dbTypes", "discountCoupan"};

		return dao.getEntitiestNotLazy(new Orders(), fields, criterions);

	}

	public List<Orders> getOrderDetail(Orders orders){


		Criterion [] criterions={Restrictions.eq("user",orders.getUser())};
		List<Orders> orderses = dao.getEntitiestNotLazy(new Orders(), null, criterions, orders.getOffset(), orders.getPageSize());
		OrderDetail orderDetail=new OrderDetail();
		Product product=new Product();
		for (Orders order:orderses
			 ) {

			orderDetail.setOrDtNo(order.getId());
			List<OrderDetail> orderDetails = orderDetailService.loadAll(orderDetail);
			for (OrderDetail detail:orderDetails
				 ) {
				String materials="";
				if(detail.getDesigner_product()!=null){
					product.setId(detail.getDesigner_product().getId());
					detail.setDesigner_product(productService.geOneDetail(product));
				}
				materials = detail.getImage_back() + "_" + detail.getImage_front();
				List<String> strings = splitStr(materials);
				List<Material> byids1 = materialService.getByids(Material.class,strings);
				detail.setMaterials(byids1);
			}
			order.setOrderDetails(orderDetails);
		}
		return  orderses;
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

