package com.xiaov.service.impl;

import com.xiaov.dao.TypesDao;
import com.xiaov.model.Types;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yymao on 2016/4/26.
 */
@Service
public class TypesImpl extends BaseServiceImpl<Types> implements TypesService {

    @Autowired
    private TypesDao dao;
    @Override
    public void delete(Types entity) {
        super.delete(entity);
    }

    @Override
    public void update(Types entity) {
        super.update(entity);
    }

    @Override
    public void save(Types entity) {
        super.save(entity);
    }

    @Override
    public List<Types> loadAll(Types entity) {
        return super.loadAll(entity);
    }



    @Override
    public Types getOne(Class clazz, String pk) {
        return super.getOne(clazz, pk);
    }
}