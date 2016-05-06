package com.xiaov.service.interfaces;

import java.util.List;

import com.xiaov.model.Vote;

public interface VoteService {

	/**
	 * 是否重复投票
	 * @param vote
	 * @return
	 */
	 boolean isRepeate(Vote vote);
	 
	 /**
	  * 投票
	  * @param vote
	  * @return
	  */
	 boolean vote(Vote vote);
	 
	 /**
	  * 排行
	  * @param vote
	  * @return
	  */
	 List<Vote> top(Vote vote);
		
}
