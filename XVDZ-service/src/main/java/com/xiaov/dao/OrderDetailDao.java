package com.xiaov.dao;

import com.sun.org.apache.xpath.internal.operations.String;
import com.xiaov.model.OrderDetail;
import com.xiaov.orm.hibernate.HibernateSupportDao;
import org.springframework.stereotype.Repository;


/**
 * Created by zouziyang on 4/18/16.
 */
@Repository
public class OrderDetailDao extends HibernateSupportDao<OrderDetail, String> {

}
