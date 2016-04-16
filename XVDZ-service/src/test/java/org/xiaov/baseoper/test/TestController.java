package org.xiaov.baseoper.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaov.model.Test;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.service.BaseService;

@Controller
public class TestController {

	@Autowired
	@Qualifier("baseService")
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
}
