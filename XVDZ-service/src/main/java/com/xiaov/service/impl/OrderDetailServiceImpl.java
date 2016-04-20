package com.xiaov.service.impl;

import com.xiaov.dao.OrderDetailDao;
import com.xiaov.model.OrderDetail;
import com.xiaov.service.interfaces.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zouziyang on 4/18/16.
 */
@Service
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail> implements OrderDetailService {
    @Autowired
    private OrderDetailDao orderdetaildao;

    @Override
    public void delete(OrderDetail entity) {

        super.delete(entity);
    }

    @Override
    public List<OrderDetail> loadAll(OrderDetail entity) {

        return super.loadAll(entity);
    }

    @Override
    public void save(OrderDetail entity) {

        super.save(entity);
    }

    @Override
    public void update(OrderDetail entity) {

        super.update(entity);
    }

    @Override
    public OrderDetail getOne(Class clazz, String pk) {

        return super.getOne(clazz, pk);
    }
}
