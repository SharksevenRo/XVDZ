<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
		用户列表<small></small>
	</h1>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-default"
							data-click="panel-expand"> <i class="fa fa-expand"></i> </a> <a
							href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-success"
							data-click="panel-reload"> <i class="fa fa-repeat"></i> </a> <a
							href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-warning"
							data-click="panel-collapse"> <i class="fa fa-minus"></i> </a> <a
							href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-danger"
							data-click="panel-remove"> <i class="fa fa-times"></i> </a>
					</div>
					<h4 class="panel-title">用户信息表</h4>
				</div>

				<div class="panel-body-reset">
					<div class="table-responsive" >
						<div class="btn-group" data-toggle="buttons-checkbox" style="margin-bottom:10px">
							<button type="button" class="btn btn-info" >添加</button> <!-- id="new_button" -->
							<button type="button" class="btn btn-primary" id="edit_button">修改</button>
							<button type="button" class="btn btn-danger" id="dele_button">删除</button>
						</div>
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

<div class="modal" id="mymodal" style="margin-top: 150px;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">添加用户</h4>
			</div>
			<div class="modal-body">
				<form id="roleForm" class="form-horizontal form-bordered">
					<fieldset>
						<input type="hidden" name="roleId" value="">
						<div class="form-group">
							<label class="col-md-2 control-label">账户ID：</label>
							<div class="col-md-4">
								<input class="form-control" placeholder="账户ID" type="text"
									name="act_id">
							</div>
							<label class="col-md-2 control-label">账号总额：</label>
							<div class="col-md-4">
								<input class="form-control" placeholder="账号总额" type="text"
									name="act_mm">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">总收益：</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="总收益"
									name="act_tt_en"></textarea>
							</div>
							<label class="col-md-2 control-label">账号余额：</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="账号余额"
									name="act_blc"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">冻结资金：</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="冻结资金"
									name="act_fm"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">添加时间：</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="添加时间："
									name="add_time"></textarea>
							</div>
							<label class="col-md-2 control-label">删除标识：</label>
							<div class="col-md-4">
								<select class="form-control" name="deleteFlag"> -->
                                 	<option>true</option> 
                                 	<option>false</option>
                               </select>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
			<div class="modal-footer">
				<div id="success" class="hide left-32">
					<span class="fa-stack fa-1x text-success"> <i
						class="fa fa-circle fa-stack-2x"></i> <i
						class="fa fa-check fa-stack-1x fa-inverse"></i> </span> <label
						class="font-14 green"></label>
				</div>
				<div id="fail" class="hide left-32">
					<span class="fa-stack fa-1x text-danger"> <i
						class="fa fa-circle fa-stack-2x"></i> <i
						class="fa fa-times fa-stack-1x fa-inverse"></i> </span> <label
						class="font-12 red"></label>
				</div>
				<input type="reset" class="hide" /> 
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button id="saveButton" type="button" class="btn btn-primary">确定</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- ================== BEGIN PAGE LEVEL JS ================== -->
<!-- jqGrid -->
<script src="../js/plugins/jqGrid/i18n/grid.locale-en.js"></script>
<script src="../js/plugins/jqGrid/jquery.jqGrid.min.js"></script>
<script src="../js/bootbox.js"></script>
<script src="../js/bootstrap-dialog.js"></script>
<script type="text/javascript">
	App.restartGlobalFunction();
	//App.setPageTitle("安鑫宝 | 企业追溯平台");

	$.getScript("../js/assets/js/table-manage-default.demo.js").done(function() {
		//initiate dataTables plugin
		TableManageDefault.init();
	});
</script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	//表单类下拉列表加载department,并根据oper是否是更新来设置deparmentId为对应选中状态
	function loadDepartment(oper, departmentId) {
		$("select[name=departmentId]").html("");
		$.ajax({
			type : "get",
			url : "../main/department/getdptmt",
			dataType : "json",
			async : true,
			success : function(data) {
				var list = data.data;
				for ( var i = 0; i < list.length; i++) {
					$("select[name=departmentId]").append(
							"<option value='"+list[i].departmentId+"'>"
									+ list[i].departmentName + "</option>");
				}
				//如果是更新记录
				if (oper == "update" && departmentId != null) {
					$("#departmentSelect").val(parseInt(departmentId));
				}
			},
			error : function() {
			//	alert("网络错误！");
				$.jstree.rollback(data.rlbk);
			}
		});
	}
	function resetInfoStyle(){
		$("#success").addClass("hide");
		$("#fail").addClass("hide");
	}
	$(function($) {
		//调整适应于页面大小的最合适大小
		$(window).on("resize.jqGrid",function() {
			$(grid_selector).jqGrid("setGridWidth",$(".jqGrid_wrapper").width());
		});

		//边栏重叠/扩展时调整大小
		var parent_column = $(grid_selector).closest('[class*="col-"]');

		//and also set width when tab pane becomes visible
		//当标签面板成为可见时设置宽度
		/* $("#myTab a[data-toggle='tab']").on("shown.bs.tab",function(e) {
			if ($(e.target).attr("href") == "#mygrid") {
				var parent_width = $(grid_selector).closest('.tab-pane').width();
				$(grid_selector).jqGrid('setGridWidth', parent_width);
			}
		}); */

		//新增
		$("#new_button").click(function() {
			loadDepartment(null, null);
			$("#mymodal").modal("toggle");
			//清空表单数据
			$("input[type=reset]").trigger("click");
		});

		//编辑
		$("#edit_button").click(function() {
			//获取选中行
			var rowId = $(grid_selector).jqGrid("getGridParam", "selrow");
			//获取选中行数据
			var rowData = $("#grid-table").jqGrid("getRowData", rowId);
			loadDepartment("update", rowData.departmentId);
			//获取选中行数据的ID
			var id = rowData.enteringGoodsId;
			if (rowId) {
				jQuery("#grid-table").jqGrid("GridToForm", rowId, "#mymodal");
				//弹出对话框
				$("#mymodal").modal("toggle");
			} else {
				$(".manhuaTip").attr({
					"ty":"error",
					"msg":"请选择一行后重试操作!",
					"value":"失败"
				}).trigger("click");
			}
		});

		//编辑
		$("#saveButton").click(function() {
			//获取选中行
			var rowId = $("#grid-table").jqGrid("getGridParam", "selrow");
			//获取选中行数据
			//var rowData = $("#grid-table").jqGrid("getRowData",rowId);
			//获取选中行数据的ID
			//var id=rowData.enteringGoodsId;
			var url = "";
	/*		if(rowId == null){
				url = "../main/role/adrl";
			}else{
				url = "../main/role/updtrl";
			} */
			//ajax提交保存
			$.ajax({
				async : true,
				cache : false,
				type : "POST",
				dataType : "json",
				url : "/XVDZ-web/main/manager/saveUserInfo",
				//序列化form表单
				data : $("#roleForm").serializeArray(),
				error : function() {//请求失败处理函数 
					$(".manhuaTip").attr({
						"ty":"error",
						"msg":"网络错误，可能是网速有点慢!",
						"value":"失败"
					}).trigger("click"); 
				},
				success : function(data) { //请求成功后处理函数。
					if(data.status=="1"){
						$("#success label").text(data.info);
						if(!$("#fail").hasClass("hide")){
							$("#fail").addClass("hide");
						}
						$("#success").removeClass("hide");
						setTimeout(function(){
							$("#mymodal").modal("hide");
							$("#grid-table").trigger("reloadGrid");
							resetInfoStyle();
							//$("#grid-table").jqGrid("setSelection", rowId);
						}, 2000);
					}else if(data.status=="0"){
						$("#fail label").text(data.info);
						if($("#fail").hasClass("hide")&&$("#success").hasClass("hide"))
							$("#fail").removeClass("hide");
					}
				}
			});
		});

		//删除数据
		$("#dele_button").click(function() {
			//获取选中行
			var rowId = $("#grid-table").jqGrid("getGridParam", "selrow");
			if (rowId == "" || rowId == null) {
				$(".manhuaTip").attr({
					"ty":"error",
					"msg":"请选择一行后重试操作!",
					"value":"失败"
				}).trigger("click");
			} else {
				//获取选中行数据
				var rowData = $("#grid-table").jqGrid("getRowData", rowId);
				//获取选中行数据的ID
				var id = rowData.roleId;
				//设置显示中文
				bootbox.setLocale("zh_CN");
				//确定删除 
				bootbox.confirm("您确定要删除选中的数据？",function(result) {
					if (result) {
						//ajax删除 
						$.ajax({
							async : false,
							cache : false,
							type : "POST",
							dataType : "json",
							url : "../main/role/dltrl",
							data : {roleId:id},
							error : function() {
								$(".manhuaTip").attr({
									"ty":"error",
									"msg":"网络错误，可能是网速有点慢!",
									"value":"失败"
								}).trigger("click");
							},
							success : function(data){
								if(data.status=="1"){
									$("#success label").text(data.info);
									if(!$("#fail").hasClass("hide")){
										$("#fail").addClass("hide");
									}
									$("#success").removeClass("hide");
									setTimeout(function(){
										$("#mymodal").modal("hide");
										$("#grid-table").trigger("reloadGrid");
										resetInfoStyle();
										//$("#grid-table").jqGrid("setSelection", rowId);
									}, 2000);
								}else if(data.status=="0"){
									$("#fail label").text(data.info);
									if($("#fail").hasClass("hide"))
										$("#fail").removeClass("hide");
								}
							}
						});
					}
				});
			}
		});
		$(grid_selector).jqGrid(
				{
					datatype : "json",
					url : "",
					height : "auto",
					autowidth : true,
					sortname : "addTime",
					colNames : [ "编辑/删除","账户ID", "用户ID", "总额", "总收益",
							"余额", "冻结资金", "账号状态","修改时间", "备注","添加时间", "删除时间"],  //, "修改时间", "删除时间", "备注", "删除标识"  
					colModel : [ {
						name : "",
						index : "",
						width : "80px",
						fixed : true,
						sortable : false,
						resize : false,
						formatter : "actions",
						formatoptions : {
							keys : true,
							//delbutton: false,//disable delete button
							delOptions : {
								recreateForm : true,
								beforeShowForm : beforeDeleteCallback
							},
						//editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
						}
					}, {
						name : "act_id",
						index : "act_id",
						editable : true,
						hidden : false,
						width : "10px",
						sorttype : "text",
						search : true,
						align : "center"
					},{
						name : "us_id",
						index : "us_id",
						editable : true,
						hidden : false,
						width : "10px",
						sorttype : "text",
						search : true,
						align : "center"
					}, {
						name : "act_mm",
						index : "act_mm",
						editable : true,
						width : "10px",
						sorttype : "text",
						search : true,
						align : "center"
					}, {
						name : "act_tt_en",
						index : "act_tt_en",
						hidden : false,
						editable : true,
						width : "10px",
						sorttype : "text",
						align : "center"
					}, {
						name : "act_blc",
						index : "act_blc",
						editable : true,
						width : "10px",
						sorttype : "text",
						align : "center"
					}, {
						name : "act_fm",
						index : "act_fm",
						editable : true,
						width : "10px",
						sorttype : "date",
						align : "center",
						unformat : pickDate
					}, {
						name : "act_state",
						index : "act_state",
						editable : true,
						width : "10px",
						sorttype : "date",
						align : "center",
						unformat : pickDate
					} ,{
						name : "update_time",
						index : "update_time",
						hidden : false,
						editable : true,
						width : "10px",
						sorttype : "text",
						align : "center"
					},{
						name : "remark",
						index : "remark",
						hidden : false,
						editable : true,
						width : "10px",
						sorttype : "text",
						align : "center"
					},{
						name : "add_time",
						index : "add_time",
						hidden : false,
						editable : true,
						width : "10px",
						sorttype : "text",
						align : "center"
					},{
						name : "delete_time",
						index : "delete_time",
						sortable : false,
						editable : true,
						width : "10px",
						edittype : "textarea",
						editoptions : {
							rows : "2",
							cols : "20"
						},
						align : "center"
					} ],
					viewrecords : true,
					rowNum : 10,
					rowList : [ 10, 20, 30 ],
					pager : pager_selector,
					altRows : true,
					//toppager: true,
					multiselect : true,
					shrinkToFit : true,
					//multikey: "ctrlKey",
					multiboxonly : true,
					loadComplete : function() {
						var table = this;
						setTimeout(function() {
							styleCheckbox(table);
							updateActionIcons(table);
							updatePagerIcons(table);
							enableTooltips(table);
						}, 0);
					},
					editurl : "/main/XVDZ-web/manager/getAllUser",//nothing is saved
					//caption : "职位信息",

					prmNames : {
						search : "search",
						rows : "pageSize",//每页多少条
						page : "showPage",//页码
					//sort:"page.orderBy",
					//order:"page.order"
					},
				});
		$(window).triggerHandler("resize.jqGrid");//触发窗口调整大小使得grid得到正确的尺寸

		//使之能够搜索/过滤的工具栏
		//jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
		//jQuery(grid_selector).filterToolbar({});

		//日期选择器
		function pickDate(cellvalue, options, cell) {
			setTimeout(function() {
				$(cell).find('input[type=text]').datepicker({
					format : 'yyyy-mm-dd hh:mm:ss',
					autoclose : true
				});
			}, 0);
		}

		//导航栏按钮
		$(grid_selector).jqGrid(
				'navGrid',
				pager_selector,
				{
					edit : true,
					add : true,
					del : true,
					search : true,
					refresh : true,
					view : true,
				/* editicon : 'ace-icon fa fa-pencil blue',
				addicon : 'ace-icon fa fa-plus-circle purple',
				delicon : 'ace-icon fa fa-trash-o red',
				searchicon : 'ace-icon fa fa-search orange',
				refreshicon : 'ace-icon fa fa-refresh green',
				viewicon : 'ace-icon fa fa-search-plus grey', */
				},
				{
					//edit record form
					//closeAfterEdit: true,
					//width: 700,
					recreateForm : true,
					beforeShowForm : function(e) {
						var form = $(e[0]);
						form.closest('.ui-jqdialog').find(
								'.ui-jqdialog-titlebar').wrapInner(
								'<div class="widget-header" />');
						style_edit_form(form);
					}
				},
				{
					//width: 700,
					id : 'add_id',
					closeAfterAdd : true,
					recreateForm : true,
					viewPagerButtons : false,
					beforeShowForm : function(e) {
						var form = $(e[0]);
						form.closest('.ui-jqdialog').find(
								'.ui-jqdialog-titlebar').wrapInner(
								'<div class="widget-header" />');
						style_edit_form(form);
					}
				},
				{
					id : 'delete_id',
					recreateForm : true,
					beforeShowForm : function(e) {
						var form = $(e[0]);
						if (form.data('styled'))
							return false;
						form.closest('.ui-jqdialog').find(
								'.ui-jqdialog-titlebar').wrapInner(
								'<div class="widget-header" />');
						style_delete_form(form);
						form.data('styled', true);
					},
					onClick : function(e) {
						//alert(1);
					}
				},
				{
					id : 'search_id',
					recreateForm : true,
					afterShowSearch : function(e) {
						var form = $(e[0]);
						form.closest('.ui-jqdialog').find('.ui-jqdialog-title')
								.wrap('<div class="widget-header" />');
						style_search_form(form);
					},
					afterRedraw : function() {
						style_search_filters($(this));
					},
					multipleSearch : true,
				/**
				multipleGroup:true,
				showQuery: true
				 */
				},
				{
					id : 'view_id',
					recreateForm : true,
					beforeShowForm : function(e) {
						var form = $(e[0]);
						form.closest('.ui-jqdialog').find('.ui-jqdialog-title')
								.wrap('<div class="widget-header" />');
					}
				});

		function style_search_filters(form) {
			form.find('.delete-rule').val('X');
			form.find('.add-rule').addClass('btn btn-xs btn-primary');
			form.find('.add-group').addClass('btn btn-xs btn-success');
			form.find('.delete-group').addClass('btn btn-xs btn-danger');
		}

		function beforeDeleteCallback(e) {
			var form = $(e[0]);
			if (form.data('styled'))
				return false;
			form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
					.wrapInner('<div class="widget-header" />');
			style_delete_form(form);
			form.data('styled', true);
		}

		function beforeEditCallback(e) {
			var form = $(e[0]);
			form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
					.wrapInner('<div class="widget-header" />');
			style_edit_form(form);
		}
		//it causes some flicker when reloading or navigating grid
		//it may be possible to have some custom formatter to do this as the grid is being created to prevent this
		//or go back to default browser checkbox styles for the grid
		function styleCheckbox(table) {
			/*
			$(table).find('input:checkbox').addClass('ace')
					.wrap('<label />')
					.after('<span class="lbl align-top" />')
			$('.ui-jqgrid-labels th[id*="_cb"]:first-child').find('input.cbox[type=checkbox]').addClass('ace')
						.wrap('<label />').after('<span class="lbl align-top" />');
			 */
		}

		function updateActionIcons(table) {
			/*
			var replacement = 
				{
					'ui-ace-icon fa fa-pencil' : 'ace-icon fa fa-pencil blue',
					'ui-ace-icon fa fa-trash-o' : 'ace-icon fa fa-trash-o red',
					'ui-icon-disk' : 'ace-icon fa fa-check green',
					'ui-icon-cancel' : 'ace-icon fa fa-times red'
			};
			$(table).find('.ui-pg-div span.ui-icon').each(function(){
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
				if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
			})*/
		}

		function updatePagerIcons(table) {
			/* var replacement = 
				{
					'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
					'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
					'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
					'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
				};
				$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
					var icon = $(this);
					var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
					if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
				}) */
		}

		function enableTooltips(table) {
			$('.navtable .ui-pg-button').tooltip({
				container : 'body'
			});
			$(table).find('.ui-pg-div').tooltip({
				container : 'body'
			});
		}

		//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

		$(document).one('ajaxloadstart.page', function(e) {
			$(grid_selector).jqGrid('GridUnload');
			$('.ui-jqdialog').remove();
		});
	});
</script>
<!-- ================== END PAGE LEVEL JS ================== -->