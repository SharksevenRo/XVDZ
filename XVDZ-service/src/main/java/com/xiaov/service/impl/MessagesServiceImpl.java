package com.xiaov.service.impl;

import com.xiaov.dao.MessagesDao;
import com.xiaov.model.Messages;
import com.xiaov.service.interfaces.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zouziyang on 4/18/16.
 */
@Service
public class MessagesServiceImpl extends BaseServiceImpl<Messages> implements MessagesService {

    @Autowired
    private MessagesDao messagesDao;

    @Override
    public void delete(Messages entity) {

        super.delete(entity);
    }

    @Override
    public List<Messages> loadAll(Messages entity) {

        return super.loadAll(entity);
    }

    @Override
    public void save(Messages entity) {

        super.save(entity);
    }

    @Override
    public void update(Messages entity) {

        super.update(entity);
    }

    @Override
    public Messages getOne(Class clazz, String pk) {

        return super.getOne(clazz, pk);
    }
}
