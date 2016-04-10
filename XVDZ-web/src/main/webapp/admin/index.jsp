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
	
	<link href="../css/google_font.css" type="text/css"  rel="stylesheet"/>
	<link href="../js/assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css" rel="stylesheet" />
	<link href="../js/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
	<link href="../js/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
	<link href="../js/assets/css/animate.min.css" rel="stylesheet" />
	<link href="../js/assets/css/style.min.css" rel="stylesheet" />
	<link href="../js/assets/css/style-responsive.min.css" rel="stylesheet" />
	<link href="../js/assets/css/theme/default.css" rel="stylesheet" id="theme" />
	<link rel="stylesheet" href="../js/assets/plugins/gritter/css/jquery.gritter.css" type="text/css"/>
	<link rel="stylesheet" href="../css/manhuaTip.1.0.css" type="text/css"></link>
	
	
    <link href="../css/plugins/jQueryUI/jquery-ui-1.10.4.custom.min.css" rel="stylesheet">
    <link href="../css/plugins/jqGrid/ui.jqgrid.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet"/>
    <link href="../css/custom.css" rel="stylesheet" type="text/css"/>
    
    <style type="text/css">
    	#navs span,a{font-size: 16px}
    </style>
	
	<script src="../js/assets/plugins/pace/pace.min.js"></script>
	<!-- <script type="text/javascript" >
		 function openTab(text,url){ 
			//遍历tab标题窗口a标签
			var items = $("#tab-content .times a");
			var openState = false;
			items.each(function(){
				//如果url对应的页面已打开但没显示
				if($(this).text().equels(text)){
					$("#tab-content .tab-pane").hide();
					var href =$(this).attr("href");
					$("#"+href).show();
					openState = true;
				}
			});
			if(!openState){
				$("#tab-content .tab-pane").hide();
				var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/admin/ajax/"+url+"'></iframe>";
				$("#tab-content").append(content);
			}
		}
	</script> -->
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
		<!-- <div id="frameContent" class="panel panel-default panel-with-tabs" data-sortable-id="ui-unlimited-tabs-2">
        	<div class="panel-heading p-0">
             	begin nav-tabs
                <div class="tab-overflow">
                    <ul class="nav nav-tabs">
                    	<li class="prev-button">
                        	<a href="javascript:;" data-click="prev-tab" class="text-inverse">
                            	<i class="fa fa-arrow-left"></i>
                             </a>
                        </li>
                        <li class="active items">
                             <a href="#nav-tab2-1" data-toggle="tab" class="font-12">控制总台<i class="close fa fa-times"></i></a>
                        </li>
                        <li class="next-button"><a href="javascript:;" data-click="next-tab" class="text-inverse"><i class="fa fa-arrow-right"></i></a></li>
                    </ul>
                 </div>
            </div>
            <div id="tab-content" class="tab-content">
            	<div class="tab-pane fade active in" id="nav-tab2-1">
                	<h3 class="m-t-10">Nav Tab 12</h3>
                    	<p>Lorem</p>
                        <p>Aenean</p>
                </div>
            </div>
        </div> -->
		
        <jsp:include page="part/theme.jsp"></jsp:include>
		
		<jsp:include page="part/footer.jsp"></jsp:include>
		
		<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top">
			<i class="fa fa-angle-up"></i>
		</a>
		<input style="display:none" type="button"  value="成功" class="manhuaTip" ty="success" msg="操作成功！" />		
	</div>

	<jsp:include page="part/script.jsp"></jsp:include>

	<script src="../js/assets/plugins/jquery-hashchange/jquery.hashchange.min.js"></script>
	<!-- 
	<script src="../js/assets/plugins/DataTables/js/jquery.dataTables.min.js"></script>
	<script src="../js/assets/plugins/DataTables/js/dataTables.tableTools.js"></script> 
	<script src="../js/assets/plugins/fullcalendar/fullcalendar/fullcalendar.min.js"></script>-->
	
	<script src="../js/assets/plugins/gritter/js/jquery.gritter.min.js" type="text/javascript"></script>
	<script src="../js/manhuaTip.1.0.js" type="text/javascript" ></script>
	
	<script src="../js/assets/js/apps.js"></script>
	
	<script type="text/javascript">	
		var userName="${sessionScope.employee.employeeName}";
		$(document).ready(function() {
			$("#ajax-content").height($(document).height());
			$(".manhuaTip").manhuaTip({					       
					Event : "click",			       
					timeOut : 3000
			});
			if(userName!=""){
				//$(".manhuaTip").trigger("click");
				$(".manhuaTip").attr("error","验证码错误，请重试").trigger("click");
			}
			//if(userName!=""){
				//$(".manhuaTip").trigger("click");
			//}
			App.init();
			if(userName==""){
				$.gritter.add({
					title: "欢迎回来, "+userName+"!",
					text: "您好，您本次登陆的地址和上次登录的地址相同，请放心使用系统！",
					image: "${sessionScope.employee.employeePic }",
					sticky: false,
					time: 3000,
					class_name: "my-sticky-class"
				});
			}
			/* var sidebar_width = $("#sidebar").width();
			$("#frameContent").css({ "position":"fixed","left": sidebar_width,"height":"100%","width":"100%","overflow":"hidden"});
			$(window).resize(function(){
				var sidebar_width = $("#sidebar").width();
				$("#frameContent").css({ "position":"absolute","left": sidebar_width,"height":"100%","width":"100%","overflow":"hidden"});
			}); */
		});
	</script>
</body>
</html>
