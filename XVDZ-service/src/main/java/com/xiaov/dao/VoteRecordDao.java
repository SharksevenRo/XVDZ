package com.xiaov.dao;

import org.springframework.stereotype.Repository;

import com.xiaov.model.VoteRecord;
import com.xiaov.orm.hibernate.HibernateSupportDao;

@Repository
public class VoteRecordDao extends HibernateSupportDao<VoteRecord , String> {

}
