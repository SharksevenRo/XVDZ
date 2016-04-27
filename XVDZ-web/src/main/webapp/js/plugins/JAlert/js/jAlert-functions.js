
function jAlert(btnId,url,data){//'#delte_btn' url:"../main/productionbase/edtrfct?oper=delete"
	$.alertOnClick(btnId, {
	'title': '信息删除',//标题
    'content': '你确定删除这条信息么？',//内容
    'theme': 'default',//JAlert背景颜色
    'size': 'sm',//大小
    'class':'',//JAlert样式添加
    'backgroundColor':'white',//背景样式
    'replaceOtherAlerts':true,
    /* 'size': { height: 200, width: 200 },窗口大小设置 */
    'btns': [
     /* 'href': 'https://www.baidu.com', 'target':'_new' */
    {'text':'取消', 'theme': 'blue', 'closeAlert':true},
    {'text':'确定', 'theme': 'default', 'onClick': function(result) {
				if (result) {
				
				$.ajax({
							async : true,
							cache : false,
							type : "POST",
							dataType : "json",
							url : url,
							data : data,
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
										$("#jidiModel_1").modal("hide");
										$("#jidiTable").trigger("reloadGrid");
										resetInfoStyle();
										//$("#jidiTable").jqGrid("setSelection", rowId);
									}, 2000);
								}else if(data.status=="0"){
									$("#fail label").text(data.info);
									if($("#fail").hasClass("hide"))
										$("#fail").removeClass("hide");
								}
							}
						}).done(function(data) {
					$.jAlert({ //this is the normal usage
        			'content': '删除成功！',
    				'backgroundColor':'white',//背景样式
    				'size': 'sm',
    				'btns': [{'text':'确定', 'theme': 'blue'}]
  		});
				}).error(function(data) {
					errorAlert('出错啦', '查看网络后重试！');
				});
				}
			}}
    ],
    'closeBtnAlt':'alt'
    });
	
	}
/* Optional: Overwrites javascript's built-in alert function */
function alert(title, msg){
	if( typeof msg == 'undefined' )
	{
		msg = title;
		title = '';
	}
	$.jAlert({
		'title': title,
		'content': msg
	});
}

/* Optional: Overwrites javascript's built-in confirm function (DANGER: operates differently - returns true every time and doesn't stop execution!) - You must provide a callback */
function confirm(confirmCallback, denyCallback)
{
	$.jAlert({'type': 'confirm', 'onConfirm': confirmCallback, 'onDeny': denyCallback });
}

/* Optional Alert shortcuts based on color */
function showAlert(title, msg, theme)
{
	$.jAlert({
		'title': title,
		'content': msg,
		'theme': theme
	});		
}

function successAlert(title, msg)
{
	if( typeof msg == 'undefined' )
	{
		msg = title;
		title = 'Success';
	}

	showAlert(title, msg, 'green');
}

function errorAlert(title, msg)
{
	if( typeof msg == 'undefined' )
	{
		msg = title;
		title = 'Error';
	}

	showAlert(title, msg, 'red');
}

function infoAlert(title, msg)
{
	if( typeof msg == 'undefined' )
	{
		msg = title;
		title = 'Info';
	}

	showAlert(title, msg, 'blue');
}

function warningAlert(title, msg)
{
	if( typeof msg == 'undefined' )
	{
		msg = title;
		title = 'Warning';
	}

	showAlert(title, msg, 'yellow');
}

function blackAlert(title, msg)
{
	if( typeof msg == 'undefined' )
	{
		msg = title;
		title = 'Warning';
	}

	showAlert(title, msg, 'black');
}

function imageAlert(img, imgWidth)
{
	if( typeof imgWidth == 'auto' )
	{
		iframeHeight = false;
	}

	$.jAlert({
		'image': img,
		'imageWidth': imgWidth
	});
}

function videoAlert(video)
{
	$.jAlert({
		'video': video
	});
}

function iframeAlert(iframe, iframeHeight)
{
	if( typeof iframeHeight == 'undefined' )
	{
		iframeHeight = false;
	}

	$.jAlert({
		'iframe': iframe,
		'iframeHeight': iframeHeight
	});
}

function ajaxAlert(url, onOpen)
{
	if( typeof onOpen == 'undefined' )
	{
		onOpen = function(alert){ //on open call back. Fires just after the alert has finished rendering
				return false;
			};
	}

	$.jAlert({
		'ajax': url,
		'onOpen': onOpen
	});
}
