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
	
	/**
	 * 如果要向前途返回json格式数据，加@ResponseBody，返回要返回的对象，
	 * 如果查询的结果有代理对象调用LazyObjecUtil.LazyPageSetNull去除代理对象
	 * 如果关联对象数据也要带到前台，选择动态关闭延迟加载的方式查询数据库，方法见UserServiceImp page方法中的实例
	 * @param request
	 * @param page
	 * @return
	 */
	//分页查询
	@RequestMapping("/example/page")
	@ResponseBody
	public Page<UserModel> page(HttpServletRequest request,UserModel page){
		
		//分页必带的参数 pageNo,pageSize  eg:http://localhost:8080/XVDZ-web/example/page?pageNo=5&pageSize=10
		
		//从request参数中封装分页条件（即查询条件）//有多个构造函数,PropertyFilters 
		//用name过滤条件参数名为 filter_name_EQS=1 filter固定，EQ表示过滤条件是EQ(相等),S表示name的类型是String
		//实例：http://localhost:8080/XVDZ-web/example/page?filter_name_EQS=1
		//List<PropertyFilter> build = PropertyFilters.build(request);
		//没有过滤条件传nul即可
		
		//在service也提供HQL和Criteria的方式分页的例子
		Page<UserModel> results = userService.page(page,null);
		
		//Hibnernate 的延迟加载默认是打开的，例如UserModel有个成员是RoleModel 也是个持久化对象，正常查询是只能得到一个代理对象，里面只有主键id
		//但是有的时候需要得到完整的RoleModel对象，在userService里有动态关闭某一属性的延迟加载的实例
		
		try {
			//去除代理对象，现在只是简单处理，后期考虑转换成带有id主键的关联对象
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