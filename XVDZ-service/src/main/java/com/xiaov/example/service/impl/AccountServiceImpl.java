package com.xiaov.example.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaov.example.dao.impl.AccountDao;
import com.xiaov.example.model.UserModel;
import com.xiaov.model.Account;
import com.xiaov.orm.core.Page;
import com.xiaov.orm.core.PropertyFilter;
import com.xiaov.service.interfaces.AccountServiceInterface;
import com.xiaovdingzhi.service.impl.BaseServiceImpl;

/**
 * 
 * @类名称:AccountServiceImpl
 * @类描述:
 * @创建时间:2016-4-11下午11:06:53
 * @作者: 龙华辉
 * @修改人:龙华辉
 * @修改时间:2016-4-11下午11:06:53
 * @修改备注:
 * @版本:v1.0
 */

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account> {

	// 必须定义 在做增删改的时候打日志
	private static Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);
	@Autowired
	private AccountDao accountDao;

	public int insert(Account entity) {
		// 定义返回结果
		int result = -1;
		// 首先捕获异常
		try {
			accountDao.insert(entity);
			result = 1;
		} catch (Exception e) {
			// 填写异常日志
			logger.error("时间:" + new Date().toLocaleString() + ""
					+ e.getMessage());
			result = -1;
		}
		return result;
	}

	public void fakeDelete(String Id) {
		// 首先捕获异常
		try {
			Account account = accountDao.get(Id);
			if (account != null) {
				account.setDeleteFlag(true);
				accountDao.update(account);
			}
		} catch (Exception e) {
			// 填写异常日志
			logger.error("时间:" + new Date().toLocaleString() + ""
					+ e.getMessage());
		}
	}

	public void realDelete(String Id) {
		// 首先捕获异常
		try {
			if (Id != null && Id != "") {
				accountDao.delete(Id);
			}
		} catch (Exception e) {
			// 填写异常日志
			logger.error("时间:" + new Date().toLocaleString() + ""
					+ e.getMessage());
		}
	}

	public Account selectById(String Id) {
		Account account = null;
		// 首先捕获异常
		try {
			if (Id != null && Id != "") {
				account = accountDao.get(Id);
			}
		} catch (Exception e) {
			// 填写异常日志
			logger.error("时间:" + new Date().toLocaleString() + ""
					+ e.getMessage());
		}
		return account;
	}

	public void saveOrUpdate(Account entity) {
		// 定义返回结果
		int result = -1;
		// 首先捕获异常
		try {
			accountDao.saveOrUpdate(entity);
			result = 1;
		} catch (Exception e) {
			// 填写异常日志
			logger.error("时间:" + new Date().toLocaleString() + ""
					+ e.getMessage());
			result = -1;
		}
	}

	public void save(Account entity) {
		// 定义返回结果
		int result = -1;
		// 首先捕获异常
		try {
			accountDao.save(entity);
			result = 1;
		} catch (Exception e) {
			// 填写异常日志
			logger.error("时间:" + new Date().toLocaleString() + ""
					+ e.getMessage());
			result = -1;
		}
	}

	public List<Account> loadAll(int start, int pageSize) {
		//accountDao.f
		return null;
	}

	public Page<Account> page(Page<Account> page, List<PropertyFilter> filter) {
		
		//HQL的方式分页
		String hql="select * from";
		//获取分页的hql
		String pageHql = accountDao.setPageRequestToHql(hql, page);
		List<Account> list = accountDao.createSQLQuery(pageHql, new HashMap<String, Account>()).list();
		
		//Criteria方式分页
		Criteria criteria = accountDao.getSession().createCriteria(Account.class);
		List list2 = accountDao.setPageRequestToCriteria(criteria, page).list();
		
		//动态关闭延迟加载的实例
		
		//效果是查询出的UserModel里的Role是完整的对象，不是Hibernate代理对象
		//要关闭延迟加载的关联对象的 成员变量名
		String [] fields=new String[]{"role"};
		
		//过滤条件。可通过Restrictions类获取各种过滤条件，eq是等于，其他的自行百度，看源码
		SimpleExpression [] eqs=new SimpleExpression[]{Restrictions.eq("", null)};
		
		accountDao.getEntitiestNotLazy(new UserModel(), fields, eqs);
		//UserModel 是Page的子类，若操作的Model有分页的需求需要继承Page类
		return accountDao.findPage(page, filter);
	}

}
