package com.xiaov.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaov.dao.AdvertismentDao;
import com.xiaov.model.Advertisment;
import com.xiaov.service.interfaces.AdvertismentService;

/**
 * Created by zouziyang on 4/18/16.
 */
@Service
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
