// Generate a simple captcha
function randomNumber(min, max) {
	return Math.floor(Math.random() * (max - min + 1) + min);
};
$("#captchaOperation").html(
		[ randomNumber(1, 100), "+", randomNumber(1, 200), "=" ].join(" "));

function initJcop() {
	var boundx, boundy, jcrop;
	// 预览图片区域
	var $preview = $(".preview-container");
	var preWidth = $preview.width();
	var preHeight = $preview.height();
	$(".srcImg").Jcrop({
		// 当所选区域变化
		onChange : updatePreView,
		// 当选中时
		onSelect : updatePreView,
		// 剪切宽高比
		aspectRatio : 1
	}, function() {
		// 获取实际图片的大小
		var bound = this.getBounds();
		boundx = bound[0];
		boundy = bound[1];
		jcrop = this;
		jcrop.animateTo([ 200, 200, 0, 0 ]);
		$(".preview-panel").appendTo(jcrop.ui.holder);
	});
	function updatePreView(rect) {
		if (rect.w > 0) {
			var rateX = preWidth / rect.w;
			var rateY = preHeight / rect.h;
			$(".preView").css({
				width : Math.round(rateX * boundx) + "px",
				height : Math.round(rateY * boundy) + "px",
				marginLeft : "-" + Math.round(rateX * rect.x) + "px",
				marginTop : "-" + Math.round(rateY * rect.y) + "px"
			});
			cutPosition(rect);
		}
	}
}
// 设置剪切点
function cutPosition(rct) {
	$("input[name=y]").val(rct.y);
	$("input[name=x]").val(rct.x);
	$("input[name=w]").val(rct.w);
	$("input[name=h]").val(rct.h);
	$("input[name=cw]").val($(".srcImg").width());
	$("input[name=ch]").val($(".srcImg").height());
}
// 显示预览图片
function showPreview(source) {
	var file = null;
	var fr = null;
	for ( var i = 0; i < source.files.length; i++) {
		file = source.files[i];
		if (!/image\/\w+/.test(file.type)) {
			alert("请确保文件为图像类型");
			return;
		}
		if (window.FileReader) {
			fr = new FileReader();
			fr.onloadend = function(e) {
				$(".srcImg").show().attr("src", e.target.result);
				$(".preView").attr("src", e.target.result);
				initJcop();
			};
			fr.readAsDataURL(file);
		}
	}
}

// 时间
$(".shortDate").datetimepicker({
	format : "yyyy-MM-dd",
	// 只选择日期
	minView : 2,
	// 显示与之相连的几个月
	// showMeridian: true,
	autoclose : true,
	todayBtn : true,
	// 选择中文
	language : "zh-CN"
});
$(".longDate").datetimepicker({
	format : "yyyy-MM-dd hh:mm:ss",
	// 只选择日期
	// minView :2,
	// 显示与之相连的几个月
	// showMeridian: true,
	autoclose : true,
	todayBtn : true,
	// 选择中文
	language : "zh-CN"
});
function formCheck() {
	var input = $(".check");
	for ( var i = 0; i < input.length; i++) {
		var item = input[i].value;
		if (item != "") {
			if (i == input.length - 1) {
				var companyId = $("#companys").val();
				if (companyId == "0") {
					if (confirm("未选择公司，是否添加超级管理员？")) {
						$("#sbs").attr("disabled", false);
					} else {
						$("#sbs").attr("disabled", true);
					}
				} else {
					$("#sbs").attr("disabled", false);
				}
			} else {
				continue;
			}
		} else {
			$("#sbs").attr("disabled", true);
			break;
		}
	}
}