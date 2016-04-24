<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<!-- begin #content -->
<div id="content" class="content" style="margin-bottom: 25px">
	<ol class="breadcrumb pull-right">
		<li><a href="javascript:;">首页</a>
		</li>
		<li><a href="javascript:;">表格</a>
		</li>
		<li class="active">分页表格</li>
	</ol>
	<h1 class="page-header">
		商品<small></small>
	</h1>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-default"
							data-click="panel-expand"><i class="fa fa-expand"></i>
						</a> <a href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-success"
							data-click="panel-reload"><i class="fa fa-repeat"></i>
						</a> <a href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-warning"
							data-click="panel-collapse"><i class="fa fa-minus"></i>
						</a> <a href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-danger"
							data-click="panel-remove"><i class="fa fa-times"></i>
						</a>
					</div>
					<h4 class="panel-title">商品</h4>			
				</div>
				<div class="panel-body">
				
		        <div class="btn-group" data-toggle="buttons-checkbox"  style="margin-bottom:10px">
			      <button type="button" class="btn btn-primary" id="new_button">添加</button>
			      <button type="button" class="btn btn-primary" id="edit_button">修改</button>
			      <button type="button" class="btn btn-primary" id="select_button">查看</button>
			      <button type="button" class="btn btn-primary" id="dele_button">删除</button>
			    </div>
					<div class="table-responsive" style="overflow: hidden;">
						<div class="jqGrid_wrapper">
                        	<table id="grid-table"></table>
                            <div id="grid-pager"></div>
                        </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



 
<div class="modal" id="mymodal" style="padding-top: 150px;">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content" style="width: 700px">
            <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title">添加商品</h4>
			</div>
			<div class="modal-body">
<!-- 				<form action="../main/enterGoods/saveOrUpdateEnterGoods" class="form-horizontal" method="post"> -->
				<form id="enteringGoodsForm" class="form-horizontal" method="post">
					<fieldset>
						<div class="form-group">
<!--                         </div> -->
<!--                    		<div class="form-group"> -->
                            <label class="col-md-2 control-label">商品名称：</label>
                            <div class="col-md-4"> 
                                <input class="form-control" placeholder="请输入商品名称"  type="text" name="pdtName">
                            </div>
                             <label class="col-md-2 control-label">商品介绍：</label>
                            <div class="col-md-4"> 
                                <input class="form-control" placeholder="请输入商品名称"  type="text" name="pdtPc">
                            </div>
                        </div>
                        <div class="form-group">
                         	<label class="col-md-2 control-label">商品标签：</label>
                            <div class="col-md-4">
                                <input class="form-control" placeholder="请输入标签"  type="text" name="pdtLabel">
                            </div>
                            <label class="col-md-2 control-label">单价：</label>
                            <div class="col-md-4">
                                <input class="form-control" placeholder="0"  type="text" name="pdtPrc">
                            </div>
						</div>
						<div class="modal-footer">
						 <div class="form-group">
<!-- 						    隐藏ID -->
						 	<input style="display:none;"  type="text"  name="enteringGoodsId">
                        	<div class="col-md-12" style="text-align: right;">	
								<button id="closeButton" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								<button id="saveButton" type="button" class="btn btn-primary">保存</button>
<!-- 								隐藏重置按钮 -->
								<input type="reset" style="display:none;" /> 
							</div>
						</div>
					</div>
					</fieldset>
				</form>	
					</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<script src="${pageContext.request.contextPath }/js/jquery-2.2.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="${pageContext.request.contextPath }/js/plugins/jquery-ui/jquery-ui.min.js"></script>

<!-- jqGrid -->

<script src="${pageContext.request.contextPath }/js/plugins/jqGrid/i18n/grid.locale-en.js"></script>
<script src="${pageContext.request.contextPath }/js/plugins/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootbox.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap-dialog.js"></script>
<script type="text/javascript">
</script>

<script type="text/javascript">

	//表单类下拉列表加载department,并根据oper是否是更新来设置deparmentId为对应选中状态
	function loadDepartment(oper, enterGoodsTypeId) {
		$("select[name=enterGoodsTypeId]").html("");
		$.ajax({
			type : "get",
			url : "${pageContext.request.contextPath}/admin/product/page.do",
			dataType : "json",
			async : true,
			success : function(data) {
				debugger;
				var list = data.data;
				for ( var i = 0; i < list.length; i++) {
					$("select[name=enterGoodsTypeId]").append(
							"<option value='"+list[i].enterGoodsTypeId+"'>"
									+ list[i].enterGoodsTypeName + "</option>");
				}
				//如果是更新记录
				if (oper == "update" && enterGoodsTypeId != null) {
					//给下拉框赋值
					$("#enterGoodsTypeId").val(parseInt(enterGoodsTypeId));
				}
			},
			error : function() {
				alert("网络错误！");
				$.jstree.rollback(data.rlbk);
			}
		});
	}

	$(function($) {
	
	//调整适应于页面大小的最合适大小
		$(window).on("resize.jqGrid", function () {
			$("#grid-table").jqGrid( "setGridWidth", $(".jqGrid_wrapper").width() );
		});
				
	//jqgrid显示界面
	$("#grid-table").jqGrid({ 
		//提交类型
		type:"post",
        url: "${pageContext.request.contextPath}/admin/product/page.do",
        //数据类型
        datatype: "json", 
         jsonReader: {  
	        	root:"result", page:"pageNo", total:"totalItems", records:"10", repeatitems : false,//userdata: "userdata",
	        },
        //高度自动调整
        height : "100%",
		colNames:["编辑/删除","","商品名称","商品编号","商品价格","折扣","销售数量","标签","商品介绍","上架时间","点赞数","分享数"], //,"点赞数","分享数","添加时间","修改时间","删除时间","是否开放","备注","删除标识"
		colModel:[ 
				   {name: 'myac', index: '', width: 80, fixed: true, sortable: false, resize: false,search:false,
					　　　　formatter: 'actions',
					　　　　formatoptions: {
							 //设置为true可以使用 [Enter]保存数据或者[Esc] 取消编辑
						　　　 keys: true,
							 //删除按钮
						　　　 delbutton:true,　　
							 //删除操作 
						     delOptions: { recreateForm: true, 
						     			   onclickSubmit : function(params, postdata) { //提交数据结构后触发
											    var rowId = $("#grid-table").jqGrid("getGridParam", "selrow");
											    var rowData = $("#grid-table").jqGrid("getRowData",rowId);
											    var id=rowData.id;
											    postdata = {"id":id};
												return postdata;
											}, 
											reloadAfterSubmit : false,
											url : "${pageContext.request.contextPath}/admin/product/deleteAjax",/* beforeShowForm: beforeDeleteCallback */ 
										},
							 editbutton:true, 
							 editformbutton: true,
							 editOptions:{  closeOnEscape: true,
					                        closeAfterAdd: true,
					                        closeAfterEdit: true,
							 				onclickSubmit : function(params, postdata) { //提交数据结构后触发
											    var rowId = $("#grid-table").jqGrid("getGridParam", "selrow");
											    var rowData = $("#grid-table").jqGrid("getRowData",rowId);
											    var id=rowData.pdtId;
											    debugger;
											    postdata = {"id":id};
												return postdata;
											}, 
											reloadAfterSubmit : false,
											url : "${pageContext.request.contextPath}/admin/product/updateAjax.do",
							 }
						} 
					}, 
					{name:"id",index:"id",hidden : true, editable: true, width:90, align:"center"},
				   {name:"pdtName",index:"pdtName",hidden : false, editable: true, width:90, align:"center"},
				   {name:"pdtNo",index:"pdtNo",hidden : false, editable: true, width:90, align:"center"},
				   {name:"pdtPrc",index:"pdtPrc", editable: false, width:90, align:"center"}, 
				   {name:"pdtDsct",index:"pdtDsct",hidden : false, editable: false, width:90, align:"center"}, 
				   {name:"pdtSaleCount",index:"pdtSaleCount",  editable: true,width:80, align:"center"}, 
				   {name:"pdtLabel",index:"pdtLabel",  editable: true,width:80, align:"center"}, 
				   {name:"pdtPc",index:"pdtPc",  editable: true,width:80, align:"center"},
				   {name:"addTime",index:"addTime",  editable: true,width:80, align:"center"}, 
				   {name:"pdtGdCount",index:"pdtGdCount",  editable: true,width:80, align:"center"}, 
				   {name:"pdtShareCount",index:"pdtShareCount",  editable: true,width:80, align:"center"}
				  ],
   		 rowNum:10, 
   		 rowList:[10,20,30],
   		 //定义是否可以多选
   		 multiselect : true,
   		 //只有在multiselect设置为ture时起作用，定义使用那个key来做多选。shiftKey，altKey，ctrlKey 
   		 //multikey : "ctrlKey",
   		 //只有当multiselect = true.起作用，当multiboxonly 为ture时只有选择checkbox才会起作用
   		 multiboxonly: true,
   		 //此属性用来说明当初始化列宽度时候的计算类型，如果为ture，则按比例初始化列宽度。如果为false，则列宽度使用colModel指定的宽度
   		 shrinkToFit: false,
   		 //自动滚动条
   		 autoScroll: false,
   		 //是否要显示总记录数
   		 viewrecords: true,
//    		 toolbar : [ true, "top" ],
   		 prmNames : {
						search : "search",
						rows : "pageSize",//每页多少条
						page : "pageNo",//页码
					},
   		 pager: "#grid-pager", 
   		 sortname: "addTime", 
   		 viewrecords: true, 
   		 sortorder: "desc",
   		 //jqgrid名称 
//    	 caption:"商品", 
   	});
   
   	//加载底部操作按钮及页码
//    	$("#grid-table").jqGrid("navGrid","#grid-pager",{
// 	   	edit:true,
// 	   	edittext : "编辑",
// 	   	add:true,
// 	   	addtext : "添加",
// 	   	del:true,
// 	   	deltext : "删除",
// 	   	search : true,
// 		searchtext : "搜索",
// 		view : true,
// 		viewtext : "查看 ",
// 		refreshtitle : "刷新",
// 		refreshtext : "刷新 "},
// 		{ //编辑操作
// 			closeAfterEdit : true,
// 			beforeShowForm : function(formid) { 
// 				var dlgDiv = $("#editmodgrid-table");
// 				setBoxCenter(dlgDiv);
// 				var form = $(formid[0]);
// 				form.closest('.ui-jqdialog').find(
// 						'.ui-jqdialog-titlebar').wrapInner(
// 						'<div class="widget-header" />')
// 				style_edit_form(form);
// 			},
// 			onclickSubmit : function(params, postdata) { //提交数据结构后触发
// 			    var rowId = $("#grid-table").jqGrid("getGridParam", "selrow");
// 			    var rowData = $("#grid-table").jqGrid("getRowData",rowId);
// 			    var id=rowData.customerId;
// 			    postdata = {"customerId":id};
// 				return postdata;
// 			}, 
// 			reloadAfterSubmit : false,
// 			url : "../main/customer/updateCustomer",
// 		 	//afterComplete: submitSucceed 
// 		});

   		//toorbar展现按钮
//    	$("#t_grid-table").append( "<input type='button' value='Click Me' style='height:30px;font-size:-3'/>"); $("input", "#t_toolbar1").click(function() { alert("Hi! I'm added button at this toolbar of top"); });	
   		
   		
   	//显示编辑和删除的图标
   	function beforeDeleteCallback(e) {
		var form = $(e[0]);
		if(form.data("styled"))
			return false;
		form.closest(".ui-jqdialog").find(".ui-jqdialog-titlebar").wrapInner('<div class="widget-header" />');
		style_delete_form(form);
		form.data("styled", true);
	}
	
	//新增
	 $("#new_button").click(function() { 
// 	 	$("#grid-table").jqGrid('editGridRow', "new", { height : 500,width:500, reloadAfterSubmit : false });
		//清空表单数据
		$("input[type=reset]").trigger("click");
		//加载商品类型
		loadDepartment(null, null);
		//手动加载模态框
	 	$("#mymodal").modal("toggle");
	 });
	 
	//编辑
	 $("#edit_button").click(function() { 
	 	//获取选中行
	 	var rowId = $("#grid-table").jqGrid("getGridParam", "selrow");
	 	
	 	//获取选中行数据
	 	var rowData = $("#grid-table").jqGrid("getRowData",rowId);
	 	loadDepartment("update", rowData.enterGoodsTypeId);
	 	//获取选中行数据的ID
// 		var id=rowData.enteringGoodsId;
		 	if (rowId)  {		
				$("#grid-table").jqGrid("GridToForm", rowId, "#mymodal");
				//弹出对话框
				$("#mymodal").modal("toggle");
		 	}else {
		 		alert("请选择一行数据进行编辑！"); 	 
		 	}
	 });
	 
	 //编辑
	 $("#saveButton").click(function() { 
	 //获取选中行
 	 	var rowId = $("#grid-table").jqGrid("getGridParam", "selrow");
	 	
	 	//获取选中行数据
// 	 	var rowData = $("#grid-table").jqGrid("getRowData",rowId);
	 	//获取选中行数据的ID
// 		var id=rowData.enteringGoodsId;
	 	//ajax提交保存
	 	 $.ajax({
					async : false,  
			        cache:false,  
			        type: 'POST',  
			        dataType : 'text', 
                    url: "${pageContext.request.contextPath}/admin/product/saveAjax.do",
                    //序列化form表单
			    	data: $("#enteringGoodsForm").serializeArray(),
			        error: function () {//请求失败处理函数 
						//错误提示	 
			        },  
			        success:function(data ){ //请求成功后处理函数。
						$("#mymodal").modal("hide");
						$("#grid-table").trigger("reloadGrid");
						$("#grid-table").jqGrid("setSelection", rowId);
			       	}
				}); 
	 });
	  
	 //查看
	 $("#select_button").click(function() { 
	 	//获取选中行
	 	var rowId = $("#grid-table").jqGrid("getGridParam", "selrow");
	 	
	 	//获取选中行数据
// 	 	var rowData = $("#grid-table").jqGrid("getRowData",rowId);
	 	//获取选中行数据的ID
// 		var id=rowData.enteringGoodsId;
		 	if (rowId)  {		
				$("#grid-table").jqGrid("GridToForm", rowId, "#mymodal");
				//弹出对话框
				$("#mymodal").modal("toggle");
		 	}else {
		 		alert("请选择一行数据进行编辑！"); 	 
		 	}
	 });
	  
	 //删除数据
	 $("#dele_button").click(function() {
	 	//获取选中行
	 	var rowId = $("#grid-table").jqGrid("getGridParam", "selrow");
		if(rowId=="" || rowId==null){
		 	alert("请至少选中一个需要删除的项目！");
		}else{
			//获取选中行数据
		 	var rowData = $("#grid-table").jqGrid("getRowData",rowId);
		 	//获取选中行数据的ID
			var id=rowData.enteringGoodsId;
			 //设置显示中文
			  bootbox.setLocale("zh_CN"); 
// 			  $(".modal-body").css({"transform":"translateY(150px)"});
// 			  bootbox.css({"padding-top":"150px"});
			  //确定删除 
		      bootbox.confirm("您确定要删除选中的数据？", function(result) {  
		        if (result) {  
		           //ajax删除 
		           $.ajax({
							async : false,  
					        cache:false,  
					        type: 'POST',  
					        dataType : 'text', 
	                        url: "../main/enterGoods/deleteEnterGoods?enteringGoodsId="+id,
					    	data:rowData,
					        error: function () {//请求失败处理函数 
								//错误提示	 
					        },  
					        success:function(data ){ //请求成功后处理函数。
								alert("删除成功"+data);
								$("#grid-table").trigger("reloadGrid");
					       	}  
						});       
		        	}  
	      		}); 
			} 
	 });

		

		
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
});

//浮动框居中
/* 	function setBoxCenter(dlgDiv) {
		var width1 = document.body.clientWidth;
		var hight1 = document.body.clientHeight;
		dlgDiv[0].style.top = Math.round(hight1 / 2) + "px";
		dlgDiv[0].style.left = Math.round(width1 / 2) + "px";
	}; */
</script>
<!-- ================== END PAGE LEVEL JS ================== -->