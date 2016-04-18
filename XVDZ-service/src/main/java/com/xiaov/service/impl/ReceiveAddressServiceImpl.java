package com.xiaov.service.impl;

import com.xiaov.dao.ReceiveAddressDao;
import com.xiaov.model.ReceiveAddress;
import com.xiaov.service.interfaces.ReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zouziyang on 4/18/16.
 */
public class ReceiveAddressServiceImpl extends BaseServiceImpl<ReceiveAddress> implements ReceiveAddressService {

    @Autowired
    private ReceiveAddressDao receiveAddressDao;

    @Override
    public void delete(ReceiveAddress entity) {

        super.delete(entity);
    }

    @Override
    public List<ReceiveAddress> loadAll(ReceiveAddress entity) {

        return super.loadAll(entity);
    }

    @Override
    public void save(ReceiveAddress entity) {

        super.save(entity);
    }

    @Override
    public void update(ReceiveAddress entity) {

        super.update(entity);
    }

    @Override
    public ReceiveAddress getOne(Class clazz, String pk) {

        return super.getOne(clazz, pk);
    }
}
