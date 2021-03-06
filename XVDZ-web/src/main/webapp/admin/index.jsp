<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<!--[if IE 8]>
	<html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
	<title id="page-title">平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	
	<link href="<%=basePath %>icons/enterprise.ico" type="image/x-icon" rel="shortcut icon" />
	
	<link href="${pageContext.request.contextPath}/css/google_font.css" type="text/css"  rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/js/assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/js/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/js/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/js/assets/css/animate.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/js/assets/css/style.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/js/assets/css/style-responsive.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/js/assets/css/theme/default.css" rel="stylesheet" id="theme" />
	<link href="${pageContext.request.contextPath}/js/assets/plugins/gritter/css/jquery.gritter.css" type="text/css"/>
	<link href="${pageContext.request.contextPath}/css/manhuaTip.1.0.css" type="text/css"></link>
	
	
    <link href="${pageContext.request.contextPath}/css/plugins/jQueryUI/jquery-ui-1.10.4.custom.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/jqGrid/ui.jqgrid.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet" type="text/css"/>
    
    <style type="text/css">
    	#navs span,a{font-size: 16px}
    </style>
	
	<script src="${pageContext.request.contextPath}/js/assets/plugins/pace/pace.min.js"></script>

</head>
<body>
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	
	<div id="page-container" class="fade page-sidebar-fixed page-header-fixed">
		<jsp:include page="part/header.jsp"></jsp:include>
		
		<jsp:include page="part/menu.jsp"></jsp:include>
		
		<div id="ajax-content" class="default-bg">

		</div>
		
        <jsp:include page="part/theme.jsp"></jsp:include>
		
		<jsp:include page="part/footer.jsp"></jsp:include>
		
		<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top">
			<i class="fa fa-angle-up"></i>
		</a>
		<input style="display:none" type="button"  value="成功" class="manhuaTip" ty="success" msg="操作成功！" />		
	</div>

	<jsp:include page="part/script.jsp"></jsp:include>

	<script src="${pageContext.request.contextPath}/js/assets/plugins/jquery-hashchange/jquery.hashchange.min.js"></script>	
	<script src="${pageContext.request.contextPath}/js/assets/plugins/gritter/js/jquery.gritter.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/manhuaTip.1.0.js" type="text/javascript" ></script>
	<script src="${pageContext.request.contextPath}/js/assets/js/apps.js"></script>
	
	<script type="text/javascript">	
		 var userName="${sessionScope.employee.employeeName}";
		$(document).ready(function() {
			$("#ajax-content").height($(document).height());
			$(".manhuaTip").manhuaTip({					       
					Event : "click",			       
					timeOut : 3000
			});
			App.init();
			if(userName!=""){
				$.gritter.add({
					title: "欢迎回来, "+userName+"!",
					text: "您好，您本次登陆的地址和上次登录的地址相同，请放心使用系统！",
					image: "${sessionScope.employee.employeePic }",
					sticky: false,
					time: 3000,
					class_name: "my-sticky-class"
				});
			}
		}); 
	</script>
</body>
</html>