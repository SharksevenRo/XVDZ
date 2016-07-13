package com.xiaov.service.impl;

import com.xiaov.model.Style;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.StyleService;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yymao on 2016/5/5.
 */
@Service
public class StyleServiceImpl extends BaseServiceImpl<Style> implements StyleService{

    @Override
    public void delete(Style entity) {
        super.delete(entity);
    }

    @Override
    public void update(Style entity) {
        super.update(entity);
    }

    @Override
    public void save(Style entity) {
        super.save(entity);
    }

    @Override
    public List<Style> loadAll(Style entity) {
        return super.loadAll(entity);
    }

    @Override
    public Page<Style> page(Page page) {
        return super.page(page);
    }

    @Override
    public Style getOne(Class clazz, String pk) {
        return super.getOne(clazz, pk);
    }
}
