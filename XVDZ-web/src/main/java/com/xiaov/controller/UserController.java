package com.xiaov.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xiaov.model.DiscountCode;
import com.xiaov.model.DiscountCodeUseRecord;
import com.xiaov.service.impl.DiscountCodeServiceImpl;
import com.xiaov.service.impl.UserServiceImpl;
import com.xiaov.service.interfaces.DiscountCodeService;
import com.xiaov.service.interfaces.DiscountCodeUseRecordService;
import com.xiaov.web.support.SendMessage;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private UserServiceImpl userServiceimpl;

	@Autowired
	private DiscountCodeServiceImpl discountCodeService;

    @Autowired
    private DiscountCodeUseRecordService discountCodeUseRecordService;
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
	@RequestMapping(value = "/admin/user/save")
	@ResponseBody
	public MessageBean save(String telCode,String disCodeNo,String rePwd,String usPwd,String usTel,HttpSession session,String activeCode){

        String number = (String)session.getAttribute("RandNumber");

        if(number.equals(telCode)) {
			if(usPwd.equals(rePwd)) {
				try {
                    List<UserInfo>  userIsAdd = userServiceimpl.getByProperty("usTel",usTel);
                    if(userIsAdd.isEmpty()) {
                        if(disCodeNo.length()!=0) //是否绑定优惠码
                        {
                            List<DiscountCode> discode = discountCodeService.getByProperty("disCodeNo",disCodeNo);
                            if(discode.isEmpty())//优惠码是否存在
                            {
                                return new MessageBean(-1, "优惠码不存在或错误,请重新输入注册!");

                            }else {
                                    UserInfo user = new UserInfo();
                                    user.setUsTel(usTel);
                                    user.setUsName(usTel);
                                    user.setUsPwd(usPwd);
                                    user.setUsSex("保密");
                                    user.setAddTime(new Date());
                                    userService.save(user);  //注册用户
                                    List<UserInfo> userinfo = userServiceimpl.getByProperty("usTel",usTel);
                                    DiscountCodeUseRecord discountCodeUseRecord = new DiscountCodeUseRecord();
                                    discountCodeUseRecord.setUserInfo(userinfo.get(0));
                                    discountCodeUseRecord.setDisCodeId(discode.get(0).getId());
                                    discountCodeUseRecord.setUseTime(new Date());
                                    discountCodeUseRecordService.save(discountCodeUseRecord);//绑定优惠码
                                    return new MessageBean(1,"注册成功!");
                            }
                        }
                        UserInfo user = new UserInfo();
                        user.setUsTel(usTel);
                        user.setUsName(usTel);
                        user.setUsPwd(usPwd);
                        user.setUsSex("保密");
                        user.setAddTime(new Date());
                        userService.save(user);//注册用户
                        return new MessageBean(1,"注册成功!");
                    }else {
                        return new MessageBean(-2,"该手机号已被注册!");
                    }
				} catch (Exception e) {
					e.printStackTrace();
					return new MessageBean(-4,"系统错误!");
				}
			}else {
                return new MessageBean(-2,"两次密码不匹配");
			}
		}else {
			return new MessageBean(-3,"验证码错误!");
		}
	}
	
	/**
	 * 如果要向前途返回json格式数据，加@ResponseBody，返回要返回的对象，
	 * 如果查询的结果有代理对象调用LazyObjecUtil.LazyPageSetNull去除代理对象
	 * 如果关联对象数据也要带到前台，选择动态关闭延迟加载的方式查询数据库，方法见UserServiceImp page方法中的实例
	 * @param
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
	@RequestMapping("/admin/user/delete")
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
		@RequestMapping("/admin/user/update")
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
	@RequestMapping("/admin/telCode/getTelCode")
	public String SeedMessageCheck(@RequestParam("usTel") String usTel, HttpSession session){

		SendMessage sendMessage = new SendMessage();
		sendMessage.setMobile(usTel);
		String rand = String.valueOf(new Random().nextInt(999999));
		sendMessage.setMsg("您好，您的验证码是"+rand);
        session.setAttribute("RandNumber", rand);
        session.setMaxInactiveInterval(600);
		System.out.println(usTel);
		System.out.println(sendMessage.getMsg());
		sendMessage.SeedMessageAPI();
		return null;
	}

	@RequestMapping("/admin/user/login")
	@ResponseBody
	public MessageBean login(String usTel, String usPwd, HttpServletRequest request, HttpServletResponse response){
		UserInfo user = userServiceimpl.login(usPwd,usTel);

		if(user == null){
			return new MessageBean(-1,"手机或密码错误!");
		}else {
			CookieUtil until=new CookieUtil(request);
			until.setValue("user","userId",user.getId(),true);
			until.save(response,"user",true);
			System.out.println(user.getId());
			return new MessageBean(1,"登录成功!");
		}

	}
	@RequestMapping("/admin/user/adminLogin")
	@ResponseBody
	public MessageBean login(UserInfo user){

		return null;
	}
	
}
