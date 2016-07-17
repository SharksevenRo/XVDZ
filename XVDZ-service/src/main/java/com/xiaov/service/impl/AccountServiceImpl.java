package com.xiaov.service.impl;

import com.xiaov.dao.AccountDao;
import com.xiaov.dao.AccountDao;
import com.xiaov.model.Account;
import com.xiaov.model.Account;
import com.xiaov.model.UserInfo;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.AccountService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yymao on 2016/5/5.
 */
@Service
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService{
    @Autowired
    private AccountDao accountDao;
    @Override
    public void delete(Account entity) {
        super.delete(entity);
    }

    @Override
    public void update(Account entity) {
        super.update(entity);
    }

    @Override
    public void save(Account entity) {
        super.save(entity);
    }

    @Override
    public List<Account> loadAll(Account entity) {
        return super.loadAll(entity);
    }

    @Override
    public Page<Account> page(Page page) {
        return super.page(page);
    }

    @Override
    public Account getOne(Class clazz, String pk) {
        return super.getOne(clazz, pk);
    }

    public Account getAccountByUser(UserInfo userInfo) {

        Criterion [] criterions= {Restrictions.eq("userInfo",userInfo),Restrictions.eq("deleteFlag",0)};

        List<Account> accounts = dao.getEntitiestNotLazy(new Account(), null, criterions);

        if(accounts.size()>1||accounts.size()==0){
            return null;
        }else{
            return accounts.get(0);
        }
    }
}
