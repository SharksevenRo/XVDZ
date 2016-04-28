//时间
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