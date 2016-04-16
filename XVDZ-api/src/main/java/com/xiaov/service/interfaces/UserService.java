package com.xiaov.service.interfaces;

import com.xiaov.model.UserInfo;
import com.xiaov.service.BaseService;

public interface UserService extends BaseService<UserInfo>{
	/**
	 * 根据openid查询用户信息
	 * @param openId
	 * @return
	 */
	public UserInfo getUserInfoByOpenID(String openId);
}
