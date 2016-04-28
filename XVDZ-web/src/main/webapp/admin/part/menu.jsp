<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div id="sidebar" class="sidebar">
	<div data-scrollbar="true" data-height="100%">
		<ul class="nav">
			<li class="nav-profile">
				<div class="image">
					<a href="javascript:void(0);">
						<img src="../img/image-1.jpg" alt="用户头像" />
					</a>
				</div>
				<div class="info center">
					<p class="white center">小龙</p>
					<small>超级管理员</small>
				</div>
			</li>
		</ul>
		
		<ul class="nav">
					<li class="nav-header">导航菜单</li>
					<li class="active">
						<a href="#ajax/index.html">
						    <i class="fa fa-laptop"></i>
						    <span>总控制台</span>
					    </a>
					</li>
					<li class="has-sub">
<<<<<<< HEAD
						<a>						
							<span class="badge pull-right">2</span>
=======
						<a href="javascript:;">
							<b class="caret pull-right"></b>
>>>>>>> 4b03ae6afb8a81f2aa8c3436668c48a85ec506e2
							<i class="fa fa-inbox"></i> 
							<span>人员管理</span>
						</a>
						<ul class="sub-menu">
							<li><a href="#ajax/userList.html">用户管理</a></li>
							<li><a href="#ajax/managerStaff.html">公司员工管理</a></li>
<<<<<<< HEAD
							<li><a href="#ajax/addr.html">用户收货地址</a></li>
						</ul> 
					<!--	<ul class="sub-menu">
						    <li><a href="#ajax/email_inbox.html">管理会员信息</a></li>
						    <li><a href="#ajax/email_inbox_v2.html">管理产品信息</a></li>
						    <li><a href="#ajax/email_compose.html">产品留言管理</a></li>
						    <li><a href="#ajax/email_detail.html">上传产品</a></li>
						</ul> -->
=======
							<li><a href="#ajax/role.html">角色管理</a></li>
						</ul>
>>>>>>> 4b03ae6afb8a81f2aa8c3436668c48a85ec506e2
					</li>
					<li class="has-sub">
						<a href="javascript:;">
							<b class="caret pull-right"></b>
							<i class="fa fa-inbox"></i> 
							<span>产品管理</span>
						</a>
						<ul class="sub-menu">
							<li><a href="#ajax/userList.jsp">所有产品</a></li>
							<li><a href="#ajax/managerStaff.html">公司员工管理</a></li>
							<li><a href="#ajax/role.html">角色管理</a></li>
						</ul>
					</li>
					<li class="has-sub">
						<a href="javascript:;">
							<b class="caret pull-right"></b>
							<i class="fa fa-inbox"></i> 
							<span>订单管理</span>
						</a>
						<ul class="sub-menu">
							<li><a href="#ajax/userList.jsp">所有产品</a></li>
							<li><a href="#ajax/managerStaff.html">公司员工管理</a></li>
							<li><a href="#ajax/role.html">角色管理</a></li>
						</ul>
					</li>
					<li class="has-sub">
						<a href="javascript:;">
							<b class="caret pull-right"></b>
							<i class="fa fa-inbox"></i> 
							<span>优惠管理</span>
						</a>
						<ul class="sub-menu">
							<li><a href="#ajax/userList.jsp">所有产品</a></li>
							<li><a href="#ajax/managerStaff.html">公司员工管理</a></li>
							<li><a href="#ajax/role.html">角色管理</a></li>
						</ul>
					</li>
					<li class="has-sub">
						<a href="javascript:;">
							<b class="caret pull-right"></b>
							<i class="fa fa-inbox"></i> 
							<span>广告管理</span>
						</a>
						<ul class="sub-menu">
							<li><a href="#ajax/userList.jsp">所有产品</a></li>
							<li><a href="#ajax/managerStaff.html">公司员工管理</a></li>
							<li><a href="#ajax/role.html">角色管理</a></li>
						</ul>
					</li>
					<li class="has-sub">
						<a href="javascript:;">
						    <b class="caret pull-right"></b>
						    <i class="fa fa-th"></i>
						    <span>销售统计</span>
						</a>
						<ul class="sub-menu">
							<li class="has-sub">
							    <a href="javascript:;"><b class="caret pull-right"></b>订单统计</a>
							    <ul class="sub-menu">
							        <li><a href="#ajax/table_manage.html">销售统计</a></li>
							        <li><a href="#ajax/table_manage_autofill.html">其他</a></li>
							    </ul>
							</li>
							<li class="has-sub">
							    <a href="javascript:;"><b class="caret pull-right"></b> 未发货</a>
							    <ul class="sub-menu">
							        <li><a href="#ajax/table_manage.html">包邮</a></li>
							        <li><a href="#ajax/table_manage_autofill.html">不包邮</a></li>
							    </ul>
							</li>
						</ul>
					</li>
					
					<li><a href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>
				</ul>
	</div>
</div>
<div class="sidebar-bg"></div>
