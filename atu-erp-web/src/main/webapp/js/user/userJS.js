$("#subButton").click(function() {
	var shopName=$("#shopName").val();
	if(shopName.length==0){
		alert("商家名称不能为空！");
		return;
	}
	var commision=$("#commision").val();
	if(commision.length==0){
		alert("佣金不能为空！");
		return;
	}
	$.ajax( {
		url : "/user/addBaseInfo",
		type : "post",
		dataType : "json",
		data : $("#f1").serialize(),
		success : function(data) {
			if (data.msg == "success") {
				//增加成功
				alert("成功！");
				window.location.href = "/user/baseInfoList";

			}else {
				alert("系统异常，失败！");
			}
		},
		errot : function() {
			alert("系统超时，失败！");
		}
});
});


$("#query").click(function() {
	var userId = $("#userId").val();
	if(userId != ""){
		var r =  /^[1-9]*[1-9][0-9]*$/;
		if(!r.test(userId)){
			alert("用户ID必须为正整数！");
			return false;
		}
	}
	$("#f1").attr("action", "/user/list");
	$("#f1").attr("method", "post");
	$("#f1").submit();
});

//用户生效
function effect(userId){
	var html = "<a href='javascript:void(0);' onclick='unEffect("+ userId +")'>用户失效</a>";
	var businessType = $("#"+userId).children('td').eq(5).text();
	if(businessType == "认证商家"){
		html += "<a href='javascript:void(0);' onclick='unCf("+ userId +")'> 解除认证</a>";
	}else if(businessType == "普通商家"){
		html += "<a href='javascript:void(0);' onclick='cf("+ userId +")';> 商家认证</a>";
	}
	if(confirm("确定要使用户生效？")){
		$.ajax( {
			url : "/user/effect",
			type : "post",
			dataType : "json",
			data : "userId="+userId,
			success : function(data) {
				if (data.msg == "success") {
					//用户生效
					alert("用户生效成功！");
					$("#"+userId).children('td').eq(3).html("有效");
					$("#"+userId).children('td').eq(6).html(html);
				} else {
					alert("系统异常，用户生效失败！");
				}
			},
			errot : function() {
				alert("系统超时，用户生效失败！");
			}
		});
	}
}

//用户失效
function unEffect(userId){
	if(confirm("确定要使用户失效？")){
		$.ajax( {
			url : "/user/unEffect",
			type : "post",
			dataType : "json",
			data : "userId="+userId,
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					alert("用户失效成功！");
					$("#"+userId).children('td').eq(3).html("失效");
 					$("#"+userId).children('td').eq(6).html("<a href='javascript:void(0);' onclick='effect("+ userId +")'>用户生效</a");
				} else {
					alert("系统异常，用户失效失败！");
				}
			},
			errot : function() {
				alert("系统超时，用户失效失败！");
			}
		});
	}
}


//认证商户
function cf(userId){
	if(confirm("确定要认证商户？")){
		$.ajax( {
			url : "/user/confirm",
			type : "post",
			dataType : "json",
			data : "userId="+userId,
			success : function(data) {
				if (data.msg == "success") {
					//认证成功
					alert("商户认证成功！");
					$("#"+userId).children('td').eq(5).html("认证商家");
					$("#"+userId).children('td').eq(6).html("<a href='javascript:void(0);' onclick='unEffect("+ userId +")'>用户失效</a>" +
															"<a href='javascript:void(0);' onclick='unCf("+ userId +")';> 解除认证</a>");
				} else {
					alert("系统异常，商户认证失败！");
				}
			},
			errot : function() {
				alert("系统超时，商户认证失败！");
			}
		});
	}
}

//解除商户
function unCf(userId){
	if(confirm("确定要解除商户认证？")){
		$.ajax( {
			url : "/user/unConfirm",
			type : "post",
			dataType : "json",
			data : "userId="+userId,
			success : function(data) {
				if (data.msg == "success") {
					//解除商户
					alert("解除商户认证成功！");
					$("#"+userId).children('td').eq(5).html("普通商家");
					$("#"+userId).children('td').eq(6).html("<a href='javascript:void(0);' onclick='unEffect("+ userId +")'>用户失效</a>" +
															"<a href='javascript:void(0);' onclick='cf("+ userId +")';> 商家认证</a>");
				} else {
					alert("系统异常，解除商户认证失败！");
				}
			},
			errot : function() {
				alert("系统超时，解除商户认证失败！");
			}
		});
	}
}

function changeLevel(userId, obj){
	var level = $(obj).val();
	if(confirm("确定要更改用户级别？")){
		$.post("/user/updateLevel",{userId:userId,level:level},function(data){
			if(data.msg == "success"){
				alert("更改成功！");
			}
		});
	}
};

