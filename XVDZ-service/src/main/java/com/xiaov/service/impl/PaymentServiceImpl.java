package com.xiaov.service.impl;

import com.xiaov.model.Payment;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.PaymentService;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yymao on 2016/5/5.
 */
@Service
public class PaymentServiceImpl extends BaseServiceImpl<Payment> implements PaymentService{
 
    @Override
    public void delete(Payment entity) {
        super.delete(entity);
    }

    @Override
    public void update(Payment entity) {
        super.update(entity);
    }

    @Override
    public void save(Payment entity) {
        super.save(entity);
    }

    @Override
    public List<Payment> loadAll(Payment entity) {
        return super.loadAll(entity);
    }

    @Override
    public Page<Payment> page(Page page) {
        return super.page(page);
    }

    @Override
    public Payment getOne(Class clazz, String pk) {
        return super.getOne(clazz, pk);
    }
}
