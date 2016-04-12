package com.xiaov.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xiaov.utils.TargetStrategy;

/**
 * @类名称:BaseController
 * @类描述:控制器通用方法类
 * @创建时间:2016-3-23下午1:55:43
 * @作者: xiaolong 
 * @修改人:xiaolong
 * @修改时间:2016-3-23下午1:55:43
 * @修改备注:
 * @版本:v1.0
 */

public class BaseController {
	
	private static Logger logger = LoggerFactory
				.getLogger(BaseController.class);
	
	protected HttpServletRequest request;  
    
	protected HttpServletResponse response;  
    
	protected HttpSession session;  
      
    @ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;  
        this.response = response;  
        this.session = request.getSession();  
    }  

	/**
	 * 将集合转换成json
	 * @param t 与表对应的实体类
	 * @param list 需要转JSON的数据，这里只能传表结构的列表数据，即model的数据类型
	 * @param fieldNames 要转化的属性集
	 * @return
	 */
	protected <T> String toJson(Class<?> clazz, List<T> list, String[] fieldNames) {
		Gson gson = null;
		Map<String, List<T>> map = new HashMap<String, List<T>>();
		map.put(clazz.getName().replace("com.xiaov.model.", ""), list);
		if (fieldNames != null) {
			TargetStrategy ts = null;
			ts = new TargetStrategy(clazz);
			ts.setReverse(true);
			ts.setFields(fieldNames);
			//设置排除属性策略
			gson = new GsonBuilder().setExclusionStrategies(ts).create();
		} else {
			gson = new Gson();
		}
		String json = gson.toJson(map);
		return json;
	}
	/**
	 * 
	 * @Description:将数据返回到客户端
	 * @param str JSON字符串
	 * @return void
	 * @throws
	 */
	public void writeJsonData(String str){
		response.setContentType("application/json;charset=utf-8");
		try {
			response.getWriter().write(str);
		} catch (IOException e) {
			logger.error(new Date()+":"+e.getMessage());
		}
	}

}

