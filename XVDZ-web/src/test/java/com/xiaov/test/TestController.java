package com.xiaov.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaov.model.Test;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.BaseService;

@Controller
public class TestController {

	@Autowired
	@Qualifier("baseServiceImpl")
	private BaseService<Test> baseService;
	
	@RequestMapping("/test/save")
	@ResponseBody
	public MessageBean save(Test user){
		baseService.save(user);
		return null;
	}
	
	@RequestMapping("/test/update")
	@ResponseBody
	public MessageBean update(Test user){
		baseService.save(user);
		return null;
	}
	@RequestMapping("/test/page")
	@ResponseBody
	public Page<Test> page(Test test){
		
		return baseService.page(test);
	}
}
