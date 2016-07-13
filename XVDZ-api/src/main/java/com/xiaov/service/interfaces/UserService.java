package com.xiaov.service.interfaces;

import com.xiaov.model.MutiType;
import com.xiaov.model.SearchModel;
import com.xiaov.model.UserInfo;
import com.xiaov.orm.core.PageRequest;
import com.xiaov.service.BaseService;

import java.util.List;

public interface UserService extends BaseService<UserInfo>{
	/**
	 * 根据openid查询用户信息
	 * @param openId
	 * @return
	 */
	public UserInfo getUserInfoByOpenID(String openId);

	/**
	 * 根据设计的的标签过滤查询设计师
	 * @param pageRequest
	 * @param types
     * @return
     */
	public List<UserInfo> getDesignerByMutiType(PageRequest pageRequest, String mutiType);

	public List<UserInfo> search(SearchModel search);
}
