package com.xiaov.example.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaov.controller.BaseController;


/**
 * @类名称:RoleFunctionController
 * @类描述:角色方法控制器
 * @创建时间:2016-3-9上午9:16:25
 * @作者: 龙华辉
 * @修改人:龙华辉
 * @修改时间:2016-3-9上午9:16:25
 * @修改备注:
 * @版本:v1.0
 */

@Controller
@RequestMapping("/role")
@Scope("prototype")
public class RoleController extends BaseController {

	//@Autowired
	//private RoleService roleService;

	/**
	 * 
	 * @Description: 添加角色
	 * @param @param roles
	 * @return void
	 * @throws
	 */
	/*@RequestMapping("/adrl")
	public void addRole(Roles roles) {
		// 定义要返回的消息
		String json = "";
		try {
			// 获取session中登陆的用户
			Employee employee = (Employee) session.getAttribute("employee");
			// 判断是否已登录
			if (employee == null) {
				json = AjaxUtil.messages(0, "请登录系统进行操作", null);
			} else {
				if (roles == null) {
					json = AjaxUtil.messages(0, "添加失败", null);
				} else {
					int result = roleService.insert(employee.getEmployeeId(),
							roles);
					if (result != -1) {
						json = AjaxUtil.messages(1, "添加成功", null);
					} else {
						json = AjaxUtil.messages(0, "添加失败", null);
					}
				}
			}
		} catch (Exception e) {
			// 将异常信息写入日志中
			LogBuilder.writeToLog(new Date() + ":" + e.getMessage());
		}
		writeJsonData(json);
	}*/

	/**
	 * @author 		 李俊民
	 * @Description: 查询角色信息，可以进行分页，也可一次性查询出公司全部角色
	 * @param @param showPage
	 * @param @param pageSize
	 * @return void
	 * @throws
	 */
	/*@RequestMapping("/gtrls")
	public void getRoles(Integer showPage, Integer pageSize) {
		// 定义要返回的JSON
		String json = "";
		try {
			Employee employee = (Employee) session.getAttribute("employee");
			// 判断用户是否登陆
			if (employee == null) {
				json = AjaxUtil.messages(0, "您已长时间没有操作，请重新登陆！", null);
			} else {
				// 定义总记录数
				int count = 0;
				// 定义总页数
				int pageCount = 0;
				// 定义返回的数据列表
				List<Roles> list = null;
				//得到公司ID
				Integer companyId = employee.getCompanyId();
				//一次性查询
				if (showPage == null|| pageSize==null) {
					list = roleService.selectRolesByCompanyId(companyId);
					if (list != null) {
						json = AjaxUtil.messages(1, "获取成功", list);
					} else {
						json = AjaxUtil.messages(0, "获取失败", null);
					}
				//分页查询
				} else {
					list = roleService.selectRolesByCompanyId(companyId, showPage, pageSize);	
					if (list != null) {
						// 获得记录总数
						count = roleService.getCountByCompanyId(companyId); // 总数
						// 记录页数
						pageCount = (count % pageSize) == 0 ? (count / pageSize) : ((count / pageSize) + 1);
						json = PageUtil.toPageJson(showPage, pageCount, count, list);
					} else {
						json = AjaxUtil.messages(0, "获取失败", null);
					}
				}
			}
		} catch (Exception e) {
			// 将异常信息写入日志中
			LogBuilder.writeToLog(new Date() + ":" + e.getMessage());
		}
		writeJsonData(json);
	}*/

	/**
	 * 
	 * @Description: 修改角色
	 * @param @param roles
	 * @return void
	 * @throws
	 */
	/*@RequestMapping("updtrl")
	public void updateRole(Roles roles) {
		// 定义要返回的消息
		String json = "";
		try {
			// 获取session中登陆的用户
			Employee employee = (Employee) session.getAttribute("employee");
			// 判断是否已登录
			if (employee == null) {
				json = AjaxUtil.messages(0, "请登录系统进行操作", null);
			} else {
				if (roles == null) {
					json = AjaxUtil.messages(0, "修改失败", null);
				} else {
					int result = roleService.updateByPrimaryKeySelective(
							employee.getEmployeeId(), roles);
					if (result != -1) {
						json = AjaxUtil.messages(1, "修改成功", null);
					} else {
						json = AjaxUtil.messages(0, "修改失败", null);
					}
				}
			}
		} catch (Exception e) {
			// 将异常信息写入日志中
			LogBuilder.writeToLog(new Date() + ":" + e.getMessage());
		}
		writeJsonData(json);
	}*/

	/**
	 * 
	 * @Description: 删除角色
	 * @param @param roles
	 * @return void
	 * @throws
	 */
	/*@RequestMapping("dltrl")
	public void deleteRole(Roles roles) {
		// 定义要返回的消息
		String json = "";
		try {
			// 获取session中登陆的用户
			Employee employee = (Employee) session.getAttribute("employee");
			// 判断是否已登录
			if (employee == null) {
				json = AjaxUtil.messages(0, "请登录系统进行操作", null);
			} else {
				if (roles == null) {
					json = AjaxUtil.messages(0, "删除失败", null);
				} else {
					roleService.fakeDelete(employee.getEmployeeId(),
							roles.getRoleId());
					json = AjaxUtil.messages(1, "删除成功", null);
				}
			}
		} catch (Exception e) {
			// 将异常信息写入日志中
			LogBuilder.writeToLog(new Date() + ":" + e.getMessage());
		}
		writeJsonData(json);
	}

	*//**
	 * 
	 * @Description: 编辑菜单，包括添加、修改、删除操作
	 * @param oper 操作符
	 * @param roles 封装的实体
	 * @return void
	 * @throws
	 *//*
	@RequestMapping("edtrl")
	public void editRole(String oper, Roles roles) {
		// 定义要返回的消息
		String json = "";
		try {
			// 获取session中登陆的用户
			Employee employee = (Employee) session.getAttribute("employee");
			if (employee == null) {
				json = AjaxUtil.messages(0, "您已长时间没有操作，请重新登陆！", null);
			} else {
				Integer employeeId = employee.getEmployeeId();
				if (oper != null && !"".equals(oper) && roles != null
						&& employeeId != null) {
					// 更新操作
					if (oper.equals("update")) {
						int result = roleService.updateByPrimaryKeySelective(
								employeeId, roles);
						if (result != -1) {
							json = AjaxUtil.messages(1, "修改成功", null);
						} else {
							json = AjaxUtil.messages(0, "修改失败", null);
						}
						// 删除操作
					} else if (oper.equals("delete")) {
						roleService.fakeDelete(employeeId, roles.getRoleId());
						json = AjaxUtil.messages(1, "删除成功", null);
						// 添加操作
					} else if (oper.equals("add")) {
						int result = roleService.insert(employeeId, roles);
						if (result != -1) {
							json = AjaxUtil.messages(1, "添加成功", null);
						} else {
							json = AjaxUtil.messages(0, "添加失败", null);
						}
						// 异常操作
					} else {
						json = AjaxUtil.messages(0, "操作失败", null);
					}
					// 非法操作
				} else {
					json = AjaxUtil.messages(0, "操作失败", null);
				}
			}
			writeJsonData(json);
		} catch (Exception e) {
			// 将异常信息写入日志中
			LogBuilder.writeToLog(new Date() + ":" + e.getMessage());
		}
	}*/

	/**
	 * 
	 * @Description: 真删除
	 * @param @param Id
	 * @return void
	 * @throws
	 */
	/*@RequestMapping("/rlrldlt")
	public void roleRealDelete(Integer Id) {
		// 定义要返回的消息
		String json = "";
		try {
			// 获取session中登陆的用户
			Employee employee = (Employee) session.getAttribute("employee");
			// 判断是否已登录
			if (employee == null) {
				json = AjaxUtil.messages(0, "请登录系统进行操作", null);
			} else {
				if (Id == null) {
					json = AjaxUtil.messages(0, "删除失败", null);
				} else {
					roleService.realDelete(Id);
					json = AjaxUtil.messages(1, "删除成功", null);
				}
			}
		} catch (Exception e) {
			// 将异常信息写入日志中
			LogBuilder.writeToLog(new Date() + ":" + e.getMessage());
		}
		writeJsonData(json);

	}*/

}
