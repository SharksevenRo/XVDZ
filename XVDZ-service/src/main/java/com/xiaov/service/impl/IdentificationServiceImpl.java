package com.xiaov.service.impl;

import com.xiaov.model.Identification;
import com.xiaov.model.Identification;
import com.xiaov.service.interfaces.IdentificationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sharkseven on 2016/7/12.
 */
@Service
public class IdentificationServiceImpl extends BaseServiceImpl<Identification> implements IdentificationService {
    @Override
    public void delete(Identification entity) {

        super.delete(entity);
    }

    @Override
    public List<Identification> loadAll(Identification entity) {

        return super.loadAll(entity);
    }

    @Override
    public void save(Identification entity) {

        super.save(entity);
    }

    @Override
    public void update(Identification entity) {

        super.update(entity);
    }

    @Override
    public Identification getOne(Class clazz, String pk) {

        return super.getOne(clazz, pk);
    }
}
