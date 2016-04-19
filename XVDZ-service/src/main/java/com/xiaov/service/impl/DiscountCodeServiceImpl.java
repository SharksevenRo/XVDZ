package com.xiaov.service.impl;

import com.xiaov.dao.DiscountCodeDao;
import com.xiaov.model.DiscountCode;
import com.xiaov.service.interfaces.DiscountCodeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zouziyang on 4/18/16.
 */
public class DiscountCodeServiceImpl extends BaseServiceImpl<DiscountCode> implements DiscountCodeService {
    @Autowired
    private DiscountCodeDao discountCodeDao;

    @Override
    public void delete(DiscountCode entity) {

        super.delete(entity);
    }

    @Override
    public List<DiscountCode> loadAll(DiscountCode entity) {

        return super.loadAll(entity);
    }

    @Override
    public void save(DiscountCode entity) {

        super.save(entity);
    }

    @Override
    public void update(DiscountCode entity) {

        super.update(entity);
    }

    @Override
    public DiscountCode getOne(Class clazz, String pk) {

        return super.getOne(clazz, pk);
    }
}
