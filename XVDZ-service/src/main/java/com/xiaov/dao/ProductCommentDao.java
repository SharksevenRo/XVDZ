package com.xiaov.dao;

import com.xiaov.model.ProductComment;
import com.xiaov.orm.hibernate.HibernateSupportDao;
import org.springframework.stereotype.Repository;

/**
 * Created by zouziyang on 4/18/16.
 */
@Repository
public class ProductCommentDao extends HibernateSupportDao<ProductComment, String> {

}
