package com.xiaov.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaov.example.model.RoleModel;
import com.xiaov.example.model.UserModel;
import com.xiaov.example.service.UserService;
import com.xiaov.orm.core.Page;
import com.xiaov.orm.core.PropertyFilter;
import com.xiaov.orm.core.PropertyFilters;
import com.xiaov.utils.LazyObjecUtil;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	//获取所有
	@RequestMapping("/example/all")
	public String getAll(){
		List<UserModel> users = userService.getUsers();
		System.out.println("11");
		return "/index.jsp";
	}
	//添加
	@RequestMapping("/example/save")
	public String save(UserModel user){
		System.out.println("11");
		RoleModel role=new RoleModel();
		role.setRoleId(new Long(1));
		user.setRole(role);
		for (int i=0; i<25 ; i++) {
			user =new UserModel();
			user.setName(i+"");
			user.setRole(role);
			userService.save(user);
		}
		
		return "/index.jsp";
	}
	//分页查询
	@RequestMapping("/example/page")
	@ResponseBody
	public Page<UserModel> page(HttpServletRequest request,UserModel page){
		
		//分页必带的参数 pageNo,pageSize  eg:http://localhost:8080/XVDZ-web/example/page?pageNo=5&pageSize=10
		
		//从request参数中封装分页条件（即查询条件）//有多个构造函数,PropertyFilters 
		//用name过滤条件参数名为 filter_name_EQS=1 filter固定，EQ表示过滤条件是EQ,S表示name的类型是String
		//实例：http://localhost:8080/XVDZ-web/example/page?filter_name_EQS=1
		//List<PropertyFilter> build = PropertyFilters.build(request);
		//没有过滤条件传nul即可
		Page<UserModel> results = userService.page(page,null);
		try {
			results=LazyObjecUtil.LazyPageSetNull(results,"role" );
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("11");
		
		return results;
	}
	//删除
	@RequestMapping("/example/delete")
	public String delete(){
		List<UserModel> users = userService.getUsers();
		System.out.println("11");
		return "/index.jsp";
	}
	//按字段查找
	@RequestMapping("/example/getByProperty")
	public String getByProperty(){
		List<UserModel> users = userService.getUsers();
		System.out.println("11");
		return "/index.jsp";
	}
	@RequestMapping("/example/getOne")
	public String getOne(){
		List<UserModel> users = userService.getUsers();
		System.out.println("11");
		return "/index.jsp";
	}
	
	
}
