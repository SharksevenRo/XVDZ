package com.xiaov.service.impl;

import com.xiaov.dao.AdvertismentDao;
import com.xiaov.model.Advertisment;
import com.xiaov.service.interfaces.AdvertismentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zouziyang on 4/18/16.
 */
public class AdvertismentServiceImpl extends BaseServiceImpl<Advertisment> implements AdvertismentService {
    @Autowired
    private AdvertismentDao advertismentDao;

    @Override
    public void delete(Advertisment entity) {

        super.delete(entity);
    }

    @Override
    public List<Advertisment> loadAll(Advertisment entity) {

        return super.loadAll(entity);
    }

    @Override
    public void save(Advertisment entity) {

        super.save(entity);
    }

    @Override
    public void update(Advertisment entity) {

        super.update(entity);
    }

    @Override
    public Advertisment getOne(Class clazz, String pk) {

        return super.getOne(clazz, pk);
    }
}
