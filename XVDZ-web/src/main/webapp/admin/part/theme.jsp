<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="theme-panel">
	<a href="javascript:;" data-click="theme-panel-expand" class="theme-collapse-btn">
		<i class="fa fa-cog"></i>
	</a>
	<div class="theme-panel-content">
		<h5 class="m-t-0">颜色主题</h5>
		<ul class="theme-list clearfix">
			<li class="active"><a href="javascript:;" class="bg-green"
				data-theme="default" data-click="theme-selector"
				data-toggle="tooltip" data-trigger="hover" data-container="body"
				data-title="Default">&nbsp;</a>
			</li>
			<li><a href="javascript:;" class="bg-red" data-theme="red"
				data-click="theme-selector" data-toggle="tooltip"
				data-trigger="hover" data-container="body" data-title="Red">&nbsp;</a>
			</li>
			<li><a href="javascript:;" class="bg-blue" data-theme="blue"
				data-click="theme-selector" data-toggle="tooltip"
				data-trigger="hover" data-container="body" data-title="Blue">&nbsp;</a>
			</li>
			<li><a href="javascript:;" class="bg-purple" data-theme="purple"
				data-click="theme-selector" data-toggle="tooltip"
				data-trigger="hover" data-container="body" data-title="Purple">&nbsp;</a>
			</li>
			<li><a href="javascript:;" class="bg-orange" data-theme="orange"
				data-click="theme-selector" data-toggle="tooltip"
				data-trigger="hover" data-container="body" data-title="Orange">&nbsp;</a>
			</li>
			<li><a href="javascript:;" class="bg-black" data-theme="black"
				data-click="theme-selector" data-toggle="tooltip"
				data-trigger="hover" data-container="body" data-title="Black">&nbsp;</a>
			</li>
		</ul>
		<div class="divider"></div>
		<div class="row m-t-10">
			<div class="col-md-5 control-label double-line">头部风格</div>
			<div class="col-md-7">
				<select name="header-styling" class="form-control input-sm">
					<option value="1">默认</option>
					<option value="2">相反</option>
				</select>
			</div>
		</div>
		<div class="row m-t-10">
			<div class="col-md-5 control-label">头部</div>
			<div class="col-md-7">
				<select name="header-fixed" class="form-control input-sm">
					<option value="1">固定</option>
					<option value="2">默认</option>
				</select>
			</div>
		</div>
		<div class="row m-t-10">
			<div class="col-md-5 control-label double-line">工具栏</div>
			<div class="col-md-7">
				<select name="sidebar-styling" class="form-control input-sm">
					<option value="1">默认</option>
					<option value="2">网格</option>
				</select>
			</div>
		</div>
		<div class="row m-t-10">
			<div class="col-md-5 control-label">侧边导航</div>
			<div class="col-md-7">
				<select name="sidebar-fixed" class="form-control input-sm">
					<option value="1">固定</option>
					<option value="2">默认</option>
				</select>
			</div>
		</div>
		<div class="row m-t-10">
			<div class="col-md-5 control-label double-line">变化风格</div>
			<div class="col-md-7">
				<select name="content-gradient" class="form-control input-sm">
					<option value="1">不使用</option>
					<option value="2">使用</option>
				</select>
			</div>
		</div>
		<div class="row m-t-10">
			<div class="col-md-5 control-label double-line">内容风格</div>
			<div class="col-md-7">
				<select name="content-styling" class="form-control input-sm">
					<option value="1">默认</option>
					<option value="2">黑色</option>
				</select>
			</div>
		</div>
		<div class="row m-t-10">
			<div class="col-md-12">
				<a href="#" class="btn btn-inverse btn-block btn-sm"
					data-click="reset-local-storage"> <i
					class="fa fa-refresh m-r-3"></i> 重置本地设置 </a>
			</div>
		</div>
	</div>
</div>