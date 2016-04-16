package com.xiaov.service.impl;

import com.xiaov.dao.OrdersDao;
import com.xiaov.model.Orders;
import com.xiaov.orm.core.Page;
import com.xiaov.orm.hibernate.support.EntityParamsUtil;
import com.xiaov.service.interfaces.OrdersService;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zouziyang on 4/16/16.
 */
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao dao;

    public List<Orders> loadAll(Orders entity) {
        List<SimpleExpression> paramsToEqs = new EntityParamsUtil<Orders>().paramsToEqs(entity);
        return dao.createCriteriaEq(paramsToEqs).list();
    }

    public Page<Orders> page(Page<Orders> page) {
        List<SimpleExpression> paramsToEqs = new EntityParamsUtil<Page<Orders>>().paramsToEqs(page, true);
        return dao.findPage(page, dao.createCriteriaEq(paramsToEqs));
    }

    public Orders getOne(Class clazz, String pk) {
        return dao.load(pk);
    }

    public void update(Orders entity) {
        if (entity != null) {
            entity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            dao.update(entity);
        }

    }

    public void Delete(Orders entity) {
        if (entity != null) {
            entity.setDeleteTime(new Timestamp(System.currentTimeMillis()));
            dao.delete(entity);
        }

    }

    public void save(Orders entity) {
        if (entity != null) {
            entity.setAddTime(new Timestamp(System.currentTimeMillis()));
            dao.save(entity);
        }

    }
}
