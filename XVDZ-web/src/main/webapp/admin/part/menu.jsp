<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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
						<a href="javascript:;">
							<span class="badge pull-right">10</span>
							<i class="fa fa-inbox"></i> 
							<span>Email</span>
						</a>
						<ul class="sub-menu">
						    <li><a href="#ajax/email_inbox.html">Inbox v1</a></li>
						    <li><a href="#ajax/email_inbox_v2.html">Inbox v2</a></li>
						    <li><a href="#ajax/email_compose.html">Compose</a></li>
						    <li><a href="#ajax/email_detail.html">Detail</a></li>
						</ul>
					</li>
					<li class="has-sub">
						<a href="javascript:;">
						    <b class="caret pull-right"></b>
						    <i class="fa fa-suitcase"></i>
						    <span>UI Elements</span> 
						</a>
						<ul class="sub-menu">
							<li><a href="#ajax/ui_general.html">General</a></li>
							<li><a href="#ajax/ui_typography.html">Typography</a></li>
							<li><a href="#ajax/ui_tabs_accordions.html">Tabs & Accordions</a></li>
							<li><a href="#ajax/ui_unlimited_tabs.html">Unlimited Nav Tabs</a></li>
						</ul>
					</li>
					<li class="has-sub">
						<a href="javascript:;">
						    <b class="caret pull-right"></b>
						    <i class="fa fa-file-o"></i>
						    <span>Form Stuff</span> 
						</a>
						<ul class="sub-menu">
							<li><a href="#ajax/form_elements.html">Form Elements</a></li>
							<li><a href="#ajax/form_plugins.html">Form Plugins</a></li>
							<li><a href="#ajax/form_slider_switcher.html">Form Slider + Switcher</a></li>
						</ul>
					</li>
					<li class="has-sub">
						<a href="javascript:;">
						    <b class="caret pull-right"></b>
						    <i class="fa fa-th"></i>
						    <span>Tables  <span class="label label-theme m-l-5">NEW</span></span>
						</a>
						<ul class="sub-menu">
							<li><a href="table_basic.html">Basic Tables</a></li>
							<li class="has-sub">
							    <a href="javascript:;"><b class="caret pull-right"></b> Managed Tables</a>
							    <ul class="sub-menu">
							        <li><a href="#ajax/table_manage.html">Default</a></li>
							        <li><a href="#ajax/table_manage_autofill.html">Autofill</a></li>
							        <li><a href="#ajax/table_manage_buttons.html">Buttons</a></li>
							    </ul>
							</li>
						</ul>
					</li>
					
			        <!-- begin sidebar minify button -->
					<li><a href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>
			        <!-- end sidebar minify button -->
				</ul>
	</div>
</div>
<div class="sidebar-bg"></div>
