package com.xiaov.service.impl;

import com.xiaov.model.PresentRecord;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.PresentRecordService;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yymao on 2016/5/5.
 */
@Service
public class PresentRecordServiceImpl extends BaseServiceImpl<PresentRecord> implements PresentRecordService{


    @Override
    public void update(PresentRecord entity) {
        super.update(entity);
    }

    @Override
    public void save(PresentRecord entity) {
        super.save(entity);
    }

    @Override
    public List<PresentRecord> loadAll(PresentRecord entity) {
        return super.loadAll(entity);
    }

    @Override
    public Page<PresentRecord> page(Page page) {
        return super.page(page);
    }

    @Override
    public PresentRecord getOne(Class clazz, String pk) {
        return super.getOne(clazz, pk);
    }
}
