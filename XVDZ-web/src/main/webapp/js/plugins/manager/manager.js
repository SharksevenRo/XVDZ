function getUserInfo() {
	obj=$.ajax({url:"/XVDZ-web/main/manager/getAllUser",
		data : {
					"username" : "朱杰"
				},
		success: handle,
		async:true
	});
}

function handle(data) {
	alert(data);
}