package com.xiaov.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaov.dao.VoteDao;
import com.xiaov.dao.VoteRecordDao;
import com.xiaov.model.Vote;
import com.xiaov.model.VoteRecord;
import com.xiaov.service.interfaces.VoteService;

@Service
public class VoteServiceImpl implements VoteService {

	private static int count = 0;
	@Autowired
	private VoteDao dao;
	@Autowired
	private VoteRecordDao recordDao;

	public boolean isRepeate(Vote vote) {
		String hql = "from VoteRecord where oppenId='" + vote.getOpenId() + "' and voteId='" + vote.getId() + "'";
		System.out.println(hql);
		List list = recordDao.getSession().createQuery(hql).list();
		if (list.size() >= 1) {
			return true;
		} else {
			return false;
		}

	}

	public boolean vote(Vote vote) {
		try {

			vote = dao.get(vote.getId());
			count++;
			System.out.println("未枷锁：" + count);
			VoteRecord record = new VoteRecord();
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

		String hql = "select distinct  b.mount 'mount' ,name,a.id 'id',pic1,pic2,description,slogan,school  from  vote a left join (" + "select count(voteid) 'mount',voteid from voterecord  group by voteid"
				+ ") as b on a.id=b.voteid order by b.mount desc" ;
		SQLQuery createSQLQuery = dao.createSQLQuery(hql);
		createSQLQuery.addEntity(Vote .class); 

		return createSQLQuery.list();
	}

	public void save(Vote vote) {
		
		VoteRecord record=new VoteRecord();
		
		dao.save(vote);
		record.setVoteId(vote.getId());
		record.setOpenId("111");
		recordDao.save(record);
	}

}
