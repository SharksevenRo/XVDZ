package com.xiaov.service.impl;

import java.util.List;
import java.util.Map;

import com.xiaov.model.*;
import com.xiaov.orm.core.PageRequest;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xiaov.dao.UserDao;
import com.xiaov.orm.core.Page;
import com.xiaov.orm.hibernate.support.EntityParamsUtil;
import com.xiaov.service.interfaces.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserInfo> implements UserService{

	@Autowired
	private UserDao userDao;
	
	/**
	 * 根据openid查询用户信息
	 * @param openId
	 * @return
	 */
	public UserInfo getUserInfoByOpenID(String openId){
		
		List<UserInfo> users = userDao.findByProperty("appId", openId);
		if(users.size()!=1){
			return null;
		}else{
			return users.get(0);
		}
	}

	@Override
	public void delete(UserInfo entity) {
		
		super.delete(entity);
	}
	@Override
	public List<UserInfo> loadAll(UserInfo entity) {
		
		return super.loadAll(entity);
	}
	@Override
	public void save(UserInfo entity) {
		
		super.save(entity);
	}
	@Override
	public void update(UserInfo entity) {
		
		super.update(entity);
	}
	@Override
	public UserInfo getOne(Class clazz, String pk) {
		
		return super.getOne(clazz, pk);
	}

	public  List<UserInfo> getByProperty(String propertynmae,String propertyvalues){
		List<UserInfo> result ;
		result = userDao.findByProperty(propertynmae,propertyvalues);
		return result;
	}
	public UserInfo login(String usPwd,String usTel){
		return  userDao.login(usPwd,usTel);
	}

	public List<UserInfo> getDesignerByMutiType(PageRequest pageRequest,String types){

		String [] split=types.split("[_]");

		Criterion[] eqs=new SimpleExpression[split.length+2];
		for (int i=0;i<split.length;i++) {
			eqs[i]= Restrictions.like("usRemark","%,"+split[i]+"%");

		}
		eqs[split.length]=Restrictions.eq("deleteFlag",0);
		eqs[split.length+1]=Restrictions.eq("typeId","user.designer");

		return userDao.getEntitiestNotLazy(new UserInfo(),null,eqs,pageRequest.getOffset(),pageRequest.getPageSize());
	}

	public List<UserInfo> search(SearchModel search) {

		Criterion [] criterions={Restrictions.like("usName","%"+search.getSearch()+"%"),Restrictions.like("school","%"+search.getSearch()+"%"),Restrictions.like("discountCode","%"+search.getSearch()+"%"),Restrictions.like("usHobby","%"+search.getSearch()+"%"),Restrictions.like("usNcNa","%"+search.getSearch()+"%"),Restrictions.like("usLgNa","%"+search.getSearch()+"%")};
		Criterion [] criterions2={Restrictions.or(criterions),Restrictions.eq("typeId","user.designer")};
		return dao.getEntitiestNotLazy(new UserInfo(),new String[]{"productType","img","show"},criterions2,search.getOffset(),search.getPageSize());
	}
}
