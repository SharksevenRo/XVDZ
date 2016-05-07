package com.xiaov.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.Vote;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.service.interfaces.VoteService;
import com.xiaov.web.support.CookieUtil;

@Controller
public class VoteController {

	@Autowired
	private VoteService voteService;
	
	@RequestMapping("/user/vote")
	@ResponseBody
	public MessageBean vote(Vote vote,HttpServletRequest request){
		if(!voteService.isRepeate(vote)){
			String openId = new CookieUtil(request).getValue("user", "openId", true);
			vote.setOpenId(openId);
			voteService.vote(vote);
			return new MessageBean(APPConstant.ERROR, "投票成功");
		}else{
			return new MessageBean(APPConstant.ERROR, "您已为该选选手投票，不能重复投票");
		}
	}
	
	@RequestMapping("/user/top")
	@ResponseBody
	public List<Vote> top(Vote vote){
		try {
			return voteService.top(null);
		} catch (Exception e) {
			return null;
		}
			
	}
}
