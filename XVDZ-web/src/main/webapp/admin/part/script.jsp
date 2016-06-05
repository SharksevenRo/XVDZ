<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- ================== BEGIN BASE JS ================== -->
<script src="<%=basePath %>js/assets/plugins/jquery/jquery-1.9.1.min.js"></script>
<script src="<%=basePath %>js/assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
<script src="<%=basePath %>js/assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
<script src="<%=basePath %>js/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<!--[if lt IE 9]>
		<script src="<%=basePath %>js/assets/crossbrowserjs/html5shiv.js"></script>
		<script src="<%=basePath %>js/assets/crossbrowserjs/respond.min.js"></script>
		<script src="<%=basePath %>js/assets/crossbrowserjs/excanvas.min.js"></script>
	<![endif]-->
<script src="<%=basePath %>js/assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="<%=basePath %>js/bootstrap-validator.js"></script>
<!-- ================== END BASE JS ================== -->
