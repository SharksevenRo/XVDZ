package com.xiaov.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaov.dao.BankCardDao;
import com.xiaov.model.BankCard;
import com.xiaov.service.interfaces.BankCardService;

/**
 * Created by Administrator on 2016/4/18.
 */
@Service
public class BankCardServiceImpl extends BaseServiceImpl<BankCard> implements BankCardService {

    @Autowired
    private BankCardDao dao;

    @Override
    public void delete(BankCard entity) {

        super.delete(entity);
    }

    @Override
    public List<BankCard> loadAll(BankCard entity) {

        return super.loadAll(entity);
    }

    @Override
    public void save(BankCard entity) {

        super.save(entity);
    }

    @Override
    public void update(BankCard entity) {

        super.update(entity);
    }

    @Override
    public BankCard getOne(Class clazz, String pk) {

        return super.getOne(clazz, pk);
    }
}
