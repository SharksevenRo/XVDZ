/***
 * 漫画Jquery时间插件
 * 编写时间：2012年7月14号
 * version:manhuaTip.1.0.js
***/
$(function() {
});

//日期格式化
function dateFormatter(cellvalue, options, cell ) {
	if(cellvalue==""||cellvalue==undefined){
		return "未设置";
	}else{
		return new Date(cellvalue).toLocaleDateString();
	}
}	

//图片处理
function imghander(cellvalue, options, cell ) {
	if(cellvalue==""||cellvalue==undefined){
		return "";
	}else{
		return "<img onclick='getbig(this)' width='34px' height='34px' src='../"+cellvalue+"'/>";
	}
}