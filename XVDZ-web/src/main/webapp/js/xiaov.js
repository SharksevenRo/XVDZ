$(function(){
	countPrice();
	$(".shopitem input").click(function(){
		var check = $(this).attr("checked");
		if(check=="checked"){
			$(this).attr("checked",false);
			$(this).siblings().find(".itemCount").attr("flag","0");			
		}else{
			$(this).attr("checked","checked");
			$(this).siblings().find(".itemCount").attr("flag","1");
		}
		countPrice();
	});
	
});

function countPrice(){
	var price = 0;
	$(".shoppingcart .itemCount").each(function(){
		var flag = $(this).attr("flag");
		if(flag!="0"){
			var count = $(this).text();
			price = parseFloat(price)+parseFloat(count);
		}
	});
	$("#allCost").text(price);
}