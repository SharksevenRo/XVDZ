package com.xiaov.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaov.constant.APPConstant;
import com.xiaov.model.UserInfo;
import com.xiaov.orm.core.MessageBean;
import com.xiaov.orm.core.Page;
import com.xiaov.service.interfaces.UserService;
import com.xiaov.web.support.CookieUtil;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	//获取所有
	@RequestMapping("/admin/user/all")
	@ResponseBody
	public List<UserInfo> getAll(UserInfo user){
		List<UserInfo> users = userService.loadAll(user);
		//没有关联对象
		//LazyObjecUtil.LazySetNull(users, "")
		return users;
	}
	//添加
	@RequestMapping("/admin/user/saveAjax")
	@ResponseBody
	public MessageBean save(UserInfo user){
		
		try {
			user.setAddTime(new Date());
			userService.save(user);
			return new MessageBean(APPConstant.SUCCESS, "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageBean(APPConstant.ERROR, "添加失败");
		}
		
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
	@RequestMapping("/admin/user/page")
	@ResponseBody
	public Page<UserInfo> page(UserInfo page){
		
		//Hibnernate 的延迟加载默认是打开的，例如UserInfo有个成员是RoleModel 也是个持久化对象，正常查询是只能得到一个代理对象，里面只有主键id
		//但是有的时候需要得到完整的RoleModel对象，在userService里有动态关闭某一属性的延迟加载的实例
		Page<UserInfo> results = null;
		try {
			results= userService.page(page);
			//去除代理对象，现在只是简单处理，后期考虑转换成带有id主键的关联对象,user没有，所以注释
			//results=LazyObjecUtil.LazyPageSetNull(results,"role" );
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			results.setCode(APPConstant.ERROR);
			results.setMessage("服务器正忙，请重试");
			return results;
		}
		
		
	}
	//删除
	@RequestMapping("/admin/user/deleteAjax")
	@ResponseBody
	public MessageBean deleteAjax(UserInfo user){
		try {
			user=userService.getOne(user.getClass(), user.getId());
			userService.delete(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageBean(APPConstant.ERROR,"删除失败");
		}
		return new MessageBean(APPConstant.SUCCESS, "删除成功");
	}
	//删除
		@RequestMapping("/admin/user/updateAjax")
		@ResponseBody
		public MessageBean updateAjax(UserInfo user){
			try {
				user=userService.getOne(user.getClass(), user.getId());
				userService.delete(user);
			} catch (Exception e) {
				e.printStackTrace();
				return new MessageBean(APPConstant.ERROR,"删除失败");
			}
			return new MessageBean(APPConstant.SUCCESS, "删除成功");
		}
	@RequestMapping("/admin/user/getOne")
	@ResponseBody
	public UserInfo getOne(UserInfo user){
		user = userService.getOne(user.getClass(),user.getId());
		//results=LazyObjecUtil.LazyPageSetNull(user,"role" );无关联对象，注释
		System.out.println("11");
		return user;
	}
	/**
	 * 在进行获取之前需要请求http://localhost:8080/XVDZ-web/weixin/oauth2/monitor创建模拟环境
	 * 获取微信用户信息实例,只提供OpenID,等用户信息的持久化完成后会对OpenId进行封装，但cookie里会一直保存openID
	 * 所以需要openid时只需获取即可
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/example/test")
	public String getOne(HttpServletRequest request,HttpServletResponse response){
		
		String openId = new CookieUtil(request).getValue("user", "openId", true);
		return null;
	}
	
	
}
