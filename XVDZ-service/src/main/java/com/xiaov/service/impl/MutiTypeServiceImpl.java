package com.xiaov.service.impl;

import com.xiaov.model.MutiType;
import com.xiaov.service.interfaces.MutiTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sharkseven on 2016/7/9.
 */
@Service
public class MutiTypeServiceImpl extends BaseServiceImpl<MutiType> implements MutiTypeService {
    @Override
    public void delete(MutiType entity) {

        super.delete(entity);
    }

    @Override
    public List<MutiType> loadAll(MutiType entity) {

        return super.loadAll(entity);
    }

    @Override
    public void save(MutiType entity) {

        super.save(entity);
    }

    @Override
    public void update(MutiType entity) {

        super.update(entity);
    }

    @Override
    public MutiType getOne(Class clazz, String pk) {

        return super.getOne(clazz, pk);
    }
}
