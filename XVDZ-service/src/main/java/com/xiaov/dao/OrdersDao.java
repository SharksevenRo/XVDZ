package com.xiaov.dao;

import com.xiaov.model.Orders;
import com.xiaov.orm.hibernate.HibernateSupportDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by zouziyang on 4/12/16.
 */

@Repository
public class OrdersDao extends HibernateSupportDao<Orders, String> {


}
