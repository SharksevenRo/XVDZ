package com.xiaov.dao;

import com.xiaov.model.BankCard;
import com.xiaov.orm.hibernate.HibernateSupportDao;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/4/18.
 */
@Repository
public class BankCardDao extends HibernateSupportDao<BankCard, String> {

}
