package com.xiaov.dao;

import com.xiaov.model.Advertisment;
import com.xiaov.orm.hibernate.HibernateSupportDao;
import org.springframework.stereotype.Repository;

/**
 * Created by zouziyang on 4/18/16.
 */
@Repository
public class AdvertismentDao extends HibernateSupportDao<Advertisment, String> {
}
