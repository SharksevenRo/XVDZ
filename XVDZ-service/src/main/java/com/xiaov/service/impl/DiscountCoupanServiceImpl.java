package com.xiaov.service.impl;

import com.xiaov.dao.DiscountCoupanDao;
import com.xiaov.model.DiscountCoupan;
import com.xiaov.service.interfaces.DiscountCoupanService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zouziyang on 4/18/16.
 */
public class DiscountCoupanServiceImpl extends BaseServiceImpl<DiscountCoupan> implements DiscountCoupanService {

    @Autowired
    private DiscountCoupanDao discountCoupanDao;

    @Override
    public void delete(DiscountCoupan entity) {

        super.delete(entity);
    }

    @Override
    public List<DiscountCoupan> loadAll(DiscountCoupan entity) {

        return super.loadAll(entity);
    }

    @Override
    public void save(DiscountCoupan entity) {

        super.save(entity);
    }

    @Override
    public void update(DiscountCoupan entity) {

        super.update(entity);
    }

    @Override
    public DiscountCoupan getOne(Class clazz, String pk) {

        return super.getOne(clazz, pk);
    }
}
