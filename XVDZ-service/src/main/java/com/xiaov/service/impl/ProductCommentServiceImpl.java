package com.xiaov.service.impl;

import com.xiaov.dao.ProductCommentDao;
import com.xiaov.model.ProductComment;
import com.xiaov.service.interfaces.ProductCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zouziyang on 4/18/16.
 */
@Service
public class ProductCommentServiceImpl extends BaseServiceImpl<ProductComment> implements ProductCommentService {

    @Autowired
    private ProductCommentDao productCommentDao;

    @Override
    public void delete(ProductComment entity) {

        super.delete(entity);
    }

    @Override
    public List<ProductComment> loadAll(ProductComment entity) {

        return super.loadAll(entity);
    }

    @Override
    public void save(ProductComment entity) {

        super.save(entity);
    }

    @Override
    public void update(ProductComment entity) {

        super.update(entity);
    }

    @Override
    public ProductComment getOne(Class clazz, String pk) {

        return super.getOne(clazz, pk);
    }
}
