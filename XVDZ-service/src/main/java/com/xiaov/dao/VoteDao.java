package com.xiaov.dao;

import org.springframework.stereotype.Repository;

import com.xiaov.model.Vote;
import com.xiaov.orm.hibernate.HibernateSupportDao;

@Repository
public class VoteDao extends HibernateSupportDao<Vote , String>{

}
