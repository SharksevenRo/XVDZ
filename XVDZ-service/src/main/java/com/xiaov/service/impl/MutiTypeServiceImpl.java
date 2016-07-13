package com.xiaov.service.impl;

import com.xiaov.model.MutiType;
import com.xiaov.model.Product;
import com.xiaov.model.UserInfo;
import com.xiaov.service.interfaces.MutiTypeService;
import com.xiaov.service.interfaces.ProductService;
import com.xiaov.service.interfaces.UserService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

        Criterion [] eqs={Restrictions.eq("userId",entity.getUserId())};

        return  dao.getEntitiestNotLazy(new MutiType(),new String[]{"type"},eqs );
    }

    @Override
    @Transactional
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
