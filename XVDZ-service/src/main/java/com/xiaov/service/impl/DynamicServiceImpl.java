package com.xiaov.service.impl;

import com.xiaov.dao.DynamicDao;
import com.xiaov.model.Dynamic;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.DynamicService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by yymao on 2016/5/5.
 */
@Service
public class DynamicServiceImpl extends BaseServiceImpl<Dynamic> implements DynamicService{
    @Autowired
    private DynamicDao dynamicDao;
    @Override
    public void delete(Dynamic entity) {
        super.delete(entity);
    }

    @Override
    public void update(Dynamic entity) {
        super.update(entity);
    }

    @Override
    public void save(Dynamic entity) {
        super.save(entity);
    }

    @Override
    public List<Dynamic> loadAll(Dynamic entity) {
        return super.loadAll(entity);
    }

    @Override
    public Page<Dynamic> page(Page page) {
        return super.page(page);
    }

    @Override
    public Dynamic getOne(Class clazz, String pk) {
        return super.getOne(clazz, pk);
    }

    public List<Dynamic> getAll() {
        //先声明关闭延迟加载的关联对象UserInfo
        String[] fields = new String[]{"userInfo"};

        //创建过滤条件

        return dynamicDao.getEntitiestNotLazyWithOrder(new Dynamic(), fields, null, Order.desc("dynmcTime"));
    }
}
