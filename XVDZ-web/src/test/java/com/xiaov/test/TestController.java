package com.xiaov.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaov.constant.APPConstant;
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
		return new MessageBean(APPConstant.SUCCESS, "添加成功");
	}
	
	@RequestMapping("/test/update")
	@ResponseBody
	public MessageBean update(Test user){
		//url:http://localhost:8080/XVDZ-web/test/update?testId=1&name=1&isValid=0,就是更新主键为1记录的name和isValid，更新更多的字段只要前台带过来就可以
		baseService.update(user);
		return null;
	}
	@RequestMapping("/test/page")
	@ResponseBody
	public Page<Test> page(Test test){
		//没有查询条件：（以pageSize=2，pageNo=1的方式分页）http://localhost:8080/XVDZ-web/test/page?pageSize=2&pageNo=1
		//以name=1条件查询，可多条件（暂时只支持等于和and的逻辑）http://localhost:8080/XVDZ-web/test/page?pageSize=2&pageNo=1&name=1
		//设置返回总条数（将存储在test.totalItems中）
		/**
		 * 返回的json：{"pageNo":1,"pageSize":2,"orderBy":null,"orderDir":null,"countTotal":true,"code":null,"message":null,"result":
		 * [{"pageNo":1,"pageSize":10,"orderBy":null,"orderDir":null,"countTotal":true,"code":null,"message":null,"result":null,"totalItems":-1,"testId":"1","name":"1","isValid":false,"totalPages":0,"nextPage":1,"prePage":1,"offset":0,"sort":[],"orderBySetted":false,"orderSortString":""}],
		 * "totalItems":1,"totalPages":1,"nextPage":1,"prePage":1,"offset":0,"sort":[],"orderBySetted":false,"orderSortString":""}
		 * 可以看出这里面可以存储很表示分页逻辑的数据，对于有排序要求的，这个API暂未封装，（稍等）
		 */
		test.setCountTotal(true);
		return baseService.page(test);
	}
	@RequestMapping("/test/all")
	@ResponseBody
	public List<Test> all(Test test){
		/**
		 * http://localhost:8080/XVDZ-web/test/all
		 * 不带参数查询所有
		 * 带参数，根据条件过滤（暂不支持ordeby，待扩展）
		 */
		return baseService.loadAll(test);
	}
	@RequestMapping("/test/delete")
	@ResponseBody
	public MessageBean delete(Test test){
		/**
		 * http://localhost:8080/XVDZ-web/test/all
		 * 不带参数查询所有
		 * 带参数，根据条件过滤（暂不支持ordeby，待扩展）
		 */
		test = baseService.getOne(test.getClass(), test.getTestId());
		baseService.delete(test);
		return new MessageBean(APPConstant.SUCCESS, "删除成功");
	}
}
