<%@ page language="java" contentType="text/xml; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script Charset="UTF-8" type="text/javascript" src="../js/plugins/manager/manager.js"></script>
<script Charset="UTF-8" type="text/javascript" src="../js/plugins/manager/jquery-1.3.min.js"></script>
<!--

//-->
</script>
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
					<li class="nav-header">Navigation</li>
					<li class="active">
						<a  href="#ajax/index.html">
						    <i class="fa fa-laptop"></i>
						    <span>总控制台</span>
					    </a>
					</li>
					<li class="has-sub">
						<a onclick="getUserInfo('User');" href="#ajax/role.html">						<!-- /XVDZ-web/main/manager/people?usernmae=zhujie -->
							<span class="badge pull-right">2</span>
							<i class="fa fa-inbox"></i> 
							<span>人员管理</span>
						</a>
						<ul class="sub-menu">
							<li onclick="getUserInfo('User');"><a href="#ajax/role.html">用户管理</a></li>
							<li><a onclick="getUserInfo('Staff')" href="#ajax/managerStaff.html">公司员工管理</a></li>
						</ul> 
					<!--	<ul class="sub-menu">
						    <li><a href="#ajax/email_inbox.html">管理会员信息</a></li>
						    <li><a href="#ajax/email_inbox_v2.html">管理产品信息</a></li>
						    <li><a href="#ajax/email_compose.html">产品留言管理</a></li>
						    <li><a href="#ajax/email_detail.html">上传产品</a></li>
						</ul> -->
					</li>
					<li class="has-sub">
						<a href="#ajax/enteringgoods.jsp">
						 <!--    <b class="caret pull-right"></b>--> 
						    <i class="fa fa-suitcase"></i>
						    <span>产品管理</span> 
						</a>
					</li>
					<li class="has-sub">
						<a href="javascript:;">
						 <!--     <b class="caret pull-right"></b> -->
						    <i class="fa fa-file-o"></i>
						    <span>订单管理</span> 
						</a>
					<!--	<ul class="sub-menu">
							<li><a href="#ajax/form_elements.html">管理有说明退款</a></li>
							<li><a href="#ajax/form_plugins.html">管理无说明退款</a></li>
						</ul> -->
					</li>
					<li class="has-sub">
						<a href="javascript:;">
						    <b class="caret pull-right"></b>
						    <i class="fa fa-th"></i>
						    <span>销售统计 <span class="label label-theme m-l-5">NEW</span> </span>
						</a>
					<!--	<ul class="sub-menu">
							<li class="has-sub">
							    <a href="javascript:;"><b class="caret pull-right"></b> 已发货</a>
							    <ul class="sub-menu">
							        <li><a href="#ajax/table_manage.html">包邮</a></li>
							        <li><a href="#ajax/table_manage_autofill.html">不包邮</a></li>
							    </ul>
							</li>
							<li class="has-sub">
							    <a href="javascript:;"><b class="caret pull-right"></b> 未发货</a>
							    <ul class="sub-menu">
							        <li><a href="#ajax/table_manage.html">包邮</a></li>
							        <li><a href="#ajax/table_manage_autofill.html">不包邮</a></li>
							    </ul>
							</li>
						</ul>-->
					</li>
					
			        <!-- begin sidebar minify button -->
					<li><a href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>
			        <!-- end sidebar minify button -->
				</ul>
	</div>
</div>
<div class="sidebar-bg"></div>
