function getUserInfo(info) {

	obj=$.ajax({url:"/XVDZ-web/main/manager/getAllUser",
		data : {
					"username" : info
				},
		success: handle,
		async:true
	});
}

function handle(data) {
	alert(data);
}