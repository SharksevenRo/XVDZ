package com.xiaov.dao;

import com.xiaov.model.Messages;
import com.xiaov.orm.hibernate.HibernateSupportDao;
import org.springframework.stereotype.Repository;


/**
 * Created by zouziyang on 4/18/16.
 */
@Repository
public class MessagesDao extends HibernateSupportDao<Messages, String> {
}
