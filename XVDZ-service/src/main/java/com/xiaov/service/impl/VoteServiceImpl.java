package com.xiaov.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaov.dao.VoteDao;
import com.xiaov.dao.VoteRecordDao;
import com.xiaov.model.Vote;
import com.xiaov.model.VoteRecord;
import com.xiaov.service.interfaces.VoteService;

@Service
public class VoteServiceImpl implements VoteService {

	private static int count=0;
	@Autowired
	private VoteDao dao;
	@Autowired
	private VoteRecordDao recordDao;

	public boolean isRepeate(Vote vote) {
		String hql="from VoteRecord where oppenId='"+vote.getOpenId()+"' and voteId='"+vote.getId()+"'";
		System.out.println(hql);
		List list = recordDao.getSession().createQuery(hql).list();
		if (list.size() >=1) {
			return true;
		} else {
			return false;
		}

	}

	public  boolean vote(Vote vote) {
		try {
			
			vote = dao.get(vote.getId());
			count++;
			System.out.println("未枷锁："+count);
			VoteRecord record=new VoteRecord();
			record.setOpenId(vote.getOpenId());
			record.setVoteId(vote.getId());
			recordDao.save(record);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<Vote> top(Vote vote) {
		
		String hql = "from Vote  order by count desc";
		Query query = dao.createQuery(hql);
		query.setMaxResults(10);
		return query.list();
	}

}
