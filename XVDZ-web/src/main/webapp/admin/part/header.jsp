<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- begin #header -->
<div id="header" class="header navbar navbar-default navbar-fixed-top">
	<!-- begin container-fluid -->
	<div class="container-fluid">
		<!-- begin mobile sidebar expand / collapse button -->
		<div class="navbar-header">
			<a href="index.jsp" class="navbar-brand" style="width:400px">
				<span class="navbar-logo"></span>
				南昌竹新科技有限公司
			</a>
			<button type="button" class="navbar-toggle"
				data-click="sidebar-toggled">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<!-- end mobile sidebar expand / collapse button -->

		<!-- begin header navigation right -->
		<ul class="nav navbar-nav navbar-right">
			<li>
				<form class="navbar-form full-width">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="请输入关键词" />
						<button type="submit" class="btn btn-search">
							<i class="fa fa-search"></i>
						</button>
					</div>
				</form>
			</li>
			<li class="dropdown">
	          <a href="javascript:;" data-toggle="dropdown" class="dropdown-toggle f-s-14">
	            <i class="fa fa-flag-o"></i>
	            <span class="label label-danger">9</span>
	          </a>
	          <ul class="dropdown-menu media-list pull-right animated fadeInDown">
	            <li class="dropdown-header">任务（9）</li>
	            <li class="media">
	            	<a href="javascript:;">
	                    <div class="media-left">
							<i class="fa fa-bug media-object bg-red"></i>
						</div>
	                    <div class="media-body">
		                    <h6 class="media-heading">进行玉米农药残留检测</h6>
		                    <div class="progress progress-striped active" style="margin-bottom: 0px">
	                        	<div class="progress-bar" style="width: 80%"></div>
	                        </div>
	                    </div>
	                </a>
	            </li>
	            <li class="dropdown-footer text-center">
					<a href="javascript:;">查看更多</a>
				</li>
	          </ul>
	        </li>
			<li class="dropdown">
				<a href="javascript:;" data-toggle="dropdown" class="dropdown-toggle f-s-14">
					<i class="fa fa-envelope-o"></i>
					<span class="label">5</span>
				</a>
				<ul class="dropdown-menu media-list pull-right animated fadeInDown">
					<li class="dropdown-header">邮件消息(2)</li>
					<li class="media">
						<a href="javascript:;">
							<div class="media-left">
								<i class="fa fa-bug media-object bg-red"></i>
							</div>
							<div class="media-body">
								<h6 class="media-heading">服务错误报告</h6>
								<div class="text-muted f-s-11">3分钟之前</div>
							</div>
						</a>
					</li>
					<li class="media">
						<a href="javascript:;">
							<div class="media-left">
								<img src="../js/assets/img/user-1.jpg" class="media-object" alt="" />
							</div>
							<div class="media-body">
								<h6 class="media-heading">张思</h6>
								<p>公司生产部有一份文件需要你看看！</p>
								<div class="text-muted f-s-11">25分钟之前</div>
							</div>
						</a>
					</li>
					<li class="dropdown-footer text-center">
						<a href="javascript:;">查看更多</a>
					</li>
				</ul>
			</li>
			<li class="dropdown">
				<a href="javascript:;" data-toggle="dropdown" class="dropdown-toggle f-s-14">
					<i class="fa fa-bell-o"></i>
					<span class="label">5</span>
				</a>
				<ul class="dropdown-menu media-list pull-right animated fadeInDown">
					<li class="dropdown-header">通知 (2)</li>
					<li class="media">
						<a href="javascript:;">
							<div class="media-left">
								<i class="fa fa-bug media-object bg-red"></i>
							</div>
							<div class="media-body">
								<h6 class="media-heading">服务错误报告</h6>
								<div class="text-muted f-s-11">3分钟之前</div>
							</div>
						</a>
					</li>
					<li class="media">
						<a href="javascript:;">
							<div class="media-left">
								<img src="../js/assets/img/user-1.jpg" class="media-object" alt="" />
							</div>
							<div class="media-body">
								<h6 class="media-heading">张思</h6>
								<p>公司生产部有一份文件需要你看看！</p>
								<div class="text-muted f-s-11">25分钟之前</div>
							</div>
						</a>
					</li>
					<li class="dropdown-footer text-center">
						<a href="javascript:;">查看更多</a>
					</li>
				</ul>
			</li>
			<li class="dropdown navbar-user">
				<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
					<img src="../img/image-1.jpg" alt="" />
					<span class="hidden-xs"></span>
					<b class="caret"></b>
				</a>
				<ul class="dropdown-menu animated fadeInDown">
					<li class="arrow"></li>
					<li><a href="javascript:;">个人信息</a>
					</li>
					<li><a href="javascript:;"><span
							class="badge badge-danger pull-right">2</span>收件箱</a>
					</li>
					<li><a href="javascript:;">日历</a>
					</li>
					<li><a href="javascript:;">设置</a>
					</li>
					<li class="divider"></li>
					<li><a href="../main/employee/exit">注销</a>
					</li>
				</ul>
			</li>
		</ul>
		<!-- end header navigation right -->
	</div>
	<!-- end container-fluid -->
</div>
<!-- end #header -->
