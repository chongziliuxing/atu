//添加销售属性值
$("#addProVal").click(function() {
	var serviceName = $("#serviceName").val();
	if(serviceName.length==0){
		alert("服务名不能为空！");
		return;
	}
	var sortNumber = $("#sortNumber").val();
	if(sortNumber.length==0){
		alert("排序不能为空！");
		return;
	}
	//数字判断
	var txNum = /^[0-9]+$/;
	if (!txNum.test(sortNumber)) {
		alert("排序应该为数字！");
		return;
	}
	if(sortNumber.length>2){
		alert("排序不能大于两位数字！");
		return;
	}
	
	$.ajax( {
		url : "/services/addServs",
		type : "post",
		dataType : "json",
		data : $("#f1").serialize(),
		success : function(data) {
			if (data.msg == "success") {
				//增加成功
				//window.location.href = "/item/addPro";
				alert("服务添加成功！");
				jQuery.ajax( {
					url : "/services/getServicesList",
					type : "post",
					dataType : "json",
					data : "",
					success : function(data) {
						if (data.msg == "success") {
								$("#propertyTH").show();
								$("#propertyTab").empty();
								var propertyValueList=data.serviceinfoList;
								//properId存放在页面上
								$("#propertyId").val(propertyValueList[0].propertyId);
								
								$("#propertyTab").append(	"<tr style='font-weight: bold;'>"+
										"<th class='tdgoods' style='width: 100px' >服务名</th>"+
										"<th class='tdgoods' style='width: 100px'>优先级排序</th>"+
										"<th class='tdgoods' style='width: 100px' >操作</th>");
								for(var i=0; i<propertyValueList.length; i++){
									var property=propertyValueList[i];
									
									//动态添加属性值区
									
									$("#propertyTab").append(
											"<tr><td class='tdgoods' style='width: 100px'>" + property.serviceName + 
											"</td><td class='tdgoods' style='width: 100px'>" + property.sortNumber
													+ "<td class='tdgoods' style='width: 60px'>"+
													"<a id='"+property.serviceId+"' href='javascript:void(0)' onclick='removePbss(this);'>删除</a></td></tr>");
									
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
	if(confirm("确定要删除服务吗？")){
		$.ajax( {
			url : "/services/deletService",
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

//一级分类联动，获取二级分类
$("#categoryId1").change(
		function() {
			$.ajax( {
				url : "/services/getLevel",
				type : "post",
				dataType : "json",
				data : "level=2&pId="+$("#categoryId1").val(),
				success : function(data) {
					if (data.msg == "success") {
						$("#categoryId2").empty(); // 清空分类选项
//						$("<option value=''> 全选 </option>").appendTo("#categoryId2");
						var categoryList=data.categoryList;
						for ( var i = 0; i < categoryList.length; i++) {
							var category = categoryList[i];
							
							$("<option value='" + category.categoryId + "'>" 
									+ category.categoryName + "</option>").appendTo("#categoryId2");
							
						}

						$("#categoryId2").change();


					} else {
						alert("无二级分类！");
					}
				},
				errot : function() {
					alert("系统超时！");
				}
		});
});