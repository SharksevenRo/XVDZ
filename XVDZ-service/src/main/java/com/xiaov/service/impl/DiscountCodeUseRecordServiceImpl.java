package com.xiaov.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaov.dao.DiscountCodeUseRecordDao;
import com.xiaov.model.DiscountCodeUseRecord;
import com.xiaov.service.interfaces.DiscountCodeUseRecordService;

/**
 * Created by zouziyang on 4/18/16.
 */
@Service
public class DiscountCodeUseRecordServiceImpl extends BaseServiceImpl<DiscountCodeUseRecord> implements DiscountCodeUseRecordService {

    @Autowired
    private DiscountCodeUseRecordDao discountCodeUseRecordDao;

    @Override
    public void delete(DiscountCodeUseRecord entity) {

        super.delete(entity);
    }

    @Override
    public List<DiscountCodeUseRecord> loadAll(DiscountCodeUseRecord entity) {

        return super.loadAll(entity);
    }

    @Override
    public void save(DiscountCodeUseRecord entity) {

        super.save(entity);
    }

    @Override
    public void update(DiscountCodeUseRecord entity) {

        super.update(entity);
    }

    @Override
    public DiscountCodeUseRecord getOne(Class clazz, String pk) {

        return super.getOne(clazz, pk);
    }
}
