//添加销售属性值
$("#addProVal").click(function() {
	var hotwordName = $("#hotwordName").val();
	if(hotwordName.length==0){
		alert("销售属性值不能为空！");
		return;
	}
	var sortNum = $("#sortNum").val();
	if(sortNum.length==0){
		alert("排序不能为空！");
		return;
	}
	//数字判断
	var txNum = /^[0-9]+$/;
	if (!txNum.test(sortNum)) {
		alert("排序应该为数字！");
		return;
	}
	if(sortNum.length>2){
		alert("排序不能大于两位数字！");
		return;
	}
	
	$.ajax( {
		url : "/operation/addHotwords",
		type : "post",
		dataType : "json",
		data : $("#f1").serialize(),
		success : function(data) {
			if (data.msg == "success") {
				//增加成功
				//window.location.href = "/item/addPro";
				alert("热搜词添加成功！");
				jQuery.ajax( {
					url : "/operation/getHotwords",
					type : "post",
					dataType : "json",
					data : "",
					success : function(data) {
						if (data.msg == "success") {
								$("#propertyTH").show();
								$("#propertyTab").empty();
								var propertyValueList=data.hotwordsList;
								//properId存放在页面上
								$("#propertyId").val(propertyValueList[0].propertyId);
								
								$("#propertyTab").append(	"<tr style='font-weight: bold;'>"+
										"<th class='tdgoods' style='width: 100px' >热搜词名</th>"+
										"<th class='tdgoods' style='width: 100px'>优先级排序</th>"+
										"<th class='tdgoods' style='width: 100px' >操作</th>");
								for(var i=0; i<propertyValueList.length; i++){
									var property=propertyValueList[i];
									
									//动态添加属性值区
									
									$("#propertyTab").append(
											"<tr><td class='tdgoods' style='width: 100px'>" + property.hotwordName + 
											"</td><td class='tdgoods' style='width: 100px'>" + property.sortNum
													+ "<td class='tdgoods' style='width: 60px'>"+
													"<a id='"+property.id+"' href='javascript:void(0)' onclick='removePbss(this);'>删除</a></td></tr>");
									
								}
							}

					},
					errot : function() {
						alert("系统超时！");
					}
			});


//				$("#categoryId4").change();
				//$(obj).parent().parent().remove();
			} else {
				alert("系统异常，添加失败！");
			}
		},
		errot : function() {
			alert("系统超时，添加失败！");
		}
});
	
	//$("#f1").attr("action", "/category/addProVal");
	//$("#f1").attr("method", "post");
	//$("#f1").submit();
	//window.location.href = "/item/addPro";//返回到原页面
});


function removePbss(obj){
	var id = obj.id;
	//删除属性值 根据属性值ID
	if(confirm("确定要删除热搜词吗？")){
		$.ajax( {
			url : "/operation/deletHotword",
			type : "post",
			dataType : "json",
			data : "id="+id,
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					$(obj).parent().parent().remove();
				} else {
					alert("系统异常，删除失败！");
				}
			},
			errot : function() {
				alert("系统超时，删除失败！");
			}
	});
	}

}

