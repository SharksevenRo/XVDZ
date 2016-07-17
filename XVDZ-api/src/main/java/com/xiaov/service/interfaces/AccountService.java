package com.xiaov.service.interfaces;

import com.xiaov.model.Account;
import com.xiaov.model.UserInfo;
import com.xiaov.service.BaseService;

/**
 * Created by yymao on 2016/4/25.
 */
public interface AccountService extends BaseService<Account> {


    public Account getAccountByUser(UserInfo userInfo);
}


