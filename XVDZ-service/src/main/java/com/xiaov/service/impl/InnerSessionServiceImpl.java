package com.xiaov.service.impl;

import com.xiaov.dao.InnerSessionDao;
import com.xiaov.model.InnerSession;
import com.xiaov.service.interfaces.InnerSessionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sharkseven on 2016/7/8.
 */
@Service
public class InnerSessionServiceImpl extends BaseServiceImpl<InnerSession> implements InnerSessionService {



    @Override
    public void delete(InnerSession entity) {

        super.delete(entity);
    }
    @Override
    public List<InnerSession> loadAll(InnerSession entity) {

        return super.loadAll(entity);
    }
    @Override
    public void save(InnerSession entity) {

        super.save(entity);
    }
    @Override
    public void update(InnerSession entity) {

        super.update(entity);
    }
    @Override
    public InnerSession getOne(Class clazz, String pk) {

        return super.getOne(clazz, pk);
    }
}
