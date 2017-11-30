$(function(){
	//radio点击添加和清除
	/*$(":radio").click(function () {
        var r = $(this).attr("name");
        $(":radio[name=" + r + "]:not(:checked)").attr("tag", 0);
        if ($(this).attr("tag") == 1) {
            $(this).attr("checked", false);
            $(this).attr("tag", 0);
        }
        else {
            $(this).attr("tag",1);
        }
    });*/
	
	$("#btn1").click(function(){
		if(checkCoupon()){
			$.ajax( {
				url : "/coupon/apply",
				type : "post",
				dataType : "json",
				data : $("#f1").serialize(),
				success : function(data) {
					if (data.msg == "success") {
						alert("申请成功!");
						emptyContent();
					}else if(data.msg == "endTimeError"){
						alert("结束时间必须大于当前时间");
						$("#promoEndtime").val('');
						$("#promoEndtime").focus();
					}
					else {
						alert("系统异常，申请失败！");
					}
				},
				errot : function() {
					alert("系统超时，申请失败！");
				}
			});
		}
	});
	
	$("#editCoupon").click(function(){
		if(checkCoupon()){
			$.ajax( {
				url : "/coupon/editCoupon",
				type : "post",
				dataType : "json",
				data : $("#f1").serialize(),
				success : function(data) {
					if (data.msg == "success") {
						alert("优惠券修改保存成功！转到跳转页面！");
						window.location.href = "/coupon/myCoupon";
					} else {
						alert("系统异常，申请失败！");
					}
				},
				errot : function() {
					alert("系统超时，申请失败！");
				}
			});
		}
	});
	
	$("#query").click(function(){
		$("#f1").attr("method","post");
		$("#f1").attr("action","/coupon/myCoupon");
		$("#f1").submit();
	});
	
	$("#query1").click(function(){
		$("#f1").attr("method","post");
		$("#f1").attr("action","/coupon/statistics");
		$("#f1").submit();
	});
	
	
	$("#couponBusinessType").change(function(){
		$("#span4").html('');
		$("#span1").text($("#couponBusinessType option:selected").text());
		if($("#couponBusinessType option:selected").text() == "用户行为相关"){
			$("#span4").text("用户注册完成APP则自动发放");
		}else{
			$("#span4").text("所有注册用户");
		}
	});
});

function getCouponType(obj){
	$("#span2").html('');
	if(obj.value == "0"){
		$("#span2").html("订单金额大于等于<input type='text' id='orderQuota' name='orderQuota' style='width:30px' />元," +
				"减<input type='text' id='couponAmount' name='couponAmount'  style='width:30px' />元");
	}
	if(obj.value == "1"){
		$("#span2").html("订单金额直减<input type='text' id='couponAmount' name='couponAmount' style='width:30px' />元");
	}
	if(obj.value == "2"){
		$("#span2").html("免运费券 （仅限39元以下订单使用）" );
	}
}

function getcouponValidType(obj){
	$("#span3").html('');
	if(obj.value == "0"){
		$("#span3").html("有效期为<input type='text' name='couponValidDays'  style='width:30px'/>天");
	}
	if(obj.value == "1"){
		$("#span3").html("<input type='text' id='promoEndtime' name='promoEndtime'  onclick='WdatePicker({dateFmt:\"yyyy-MM-dd \"})'/>（结束时间默认为23:59:59）");
	}
	if(obj.value == "2"){
		$("#span3").html("开始时间 <input type='text' name='promoStarttime'  onclick='WdatePicker({dateFmt:\"yyyy-MM-dd \"})' />"+
                		"结束时间 <input type='text' name='promoEndtime'  onclick='WdatePicker({dateFmt:\"yyyy-MM-dd \"})' />");
	}
}

function online(couponPromoId){
	if(confirm("你确定要发布优惠券吗？")){
		$.ajax({
			url : "/coupon/online",
			type : "post",
			dataType : "json",
			data : {"couponPromoId":couponPromoId},
			success : function(data) {
				if (data.msg == "success") {
					$("#"+couponPromoId).find("td").eq("10").text("正在进行");
					var couponBusinessType = $("#"+couponPromoId).find("td").eq("2").text().trim();
					if(couponBusinessType == "通过链接领取"){
						$("#"+couponPromoId).find("td").eq("11").html("<a href='javascript:void(0);' onclick='offline("+couponPromoId+")'>下线</a>" +
																	  "<a href='javascript:void(0);' onclick='detail("+couponPromoId+")'>【查看详情】</a>" +
																	  "<a href='javascript:void(0);' onclick='addNum("+couponPromoId+")'>追加</a>" +
																	  "<a href='javascript:void(0);' onclick='getLink("+couponPromoId+")'>获取链接</a>");
					}else{
						$("#"+couponPromoId).find("td").eq("11").html("<a href='javascript:void(0);' onclick='offline("+couponPromoId+")'>下线</a>" +
																	  "<a href='javascript:void(0);' onclick='detail("+couponPromoId+")'>【查看详情】</a>" +
																	  "<a href='javascript:void(0);' onclick='addNum("+couponPromoId+")'>追加</a>" );
					}
				} else {
					alert("系统异常，请联系管理员！");
				}
			},
			errot : function() {
				alert("系统超时，请联系管理员！");
			}
		});
	}
}

function offline(couponPromoId){
	if(confirm("你确定要下线优惠券吗？")){
		$.ajax({
			url : "/coupon/offline",
			type : "post",
			dataType : "json",
			data : {"couponPromoId":couponPromoId},
			success : function(data) {
				if (data.msg == "success") {
					$("#"+couponPromoId).find("td").eq("10").text("已结束");
					$("#"+couponPromoId).find("td").eq("11").html("<a href='javascript:void(0);' onclick='detail("+couponPromoId+")'>【查看详情】</a>")
				} else {
					alert("系统异常，请联系管理员！");
				}
			},
			errot : function() {
				alert("系统超时，请联系管理员！");
			}
		});
	}
}

function addNum(couponPromoId){
	var addNum = prompt('请输入追加的数量');
	var reg = /^[1-9]\d*$/;
	if(!reg.test(addNum)){
		alert("数量不符合标准!");  
		return;
	}
	$.ajax({
		url : "/coupon/addNum",
		type : "post",
		dataType : "json",
		data : {"addNum":addNum,"couponPromoId":couponPromoId},
		success : function(data) {
			if (data.msg == "success") {
				$("#"+couponPromoId).find("td").eq(5).empty();
				$("#"+couponPromoId).find("td").eq(5).append(data.couponApplyAmount);
			} else {
				alert("系统异常，请联系管理员！");
			}
		},
		errot : function() {
			alert("系统超时，请联系管理员！");
		}
	});
}

function myCoupon(){
	$.ajax({
		url : "/coupon/myCoupon",
		type : "post",
		dataType : "json",
		data : {"time":new Date()},
		success : function(data) {
			if (data.msg == "success") {
				
			} else {
				alert("系统异常，请联系管理员！");
			}
		},
		errot : function() {
			alert("系统超时，请联系管理员！");
		}
	});
}



function getLink(couponPromoId){
	var url = 'www.atu360.com';
	alert(url+'/index?coupondraw_'+couponPromoId);
}


function toUpdate(couponPromoId){
	
	window.location.href="/coupon/toUpdate?couponPromoId="+couponPromoId+"&time="+new Date().getMilliseconds();
}


function showSimpleModel(){
    $('.simplemodal-container').show();
    $('.simplemodal-overlay').show();
}

function changeSimpleModelCss(width,height,top){
    $("#simplemodal-container").css("width",width+"px");
	$("#simplemodal-container").css("height",height+"px");
	if(top){
	$("#simplemodal-container").css("top",top+"px");
	}
}

function detail(couponPromoId){
	showSimpleModel();
	$.post("/coupon/detail?couponPromoId="+couponPromoId+"&x="+new Date().getMilliseconds(), function(result){
		$("#addDialog").html(result);
	});
	$('#addDialog').modal({
	});
	changeSimpleModelCss(750,430,20);
	return;
};

function checkCoupon(){
	var applyUserName = $("#applyUserName").val();
	if(applyUserName == ""){
		alert("请输入申请人姓名");
		$("#applyUserName").focus();
		return false;
	}
	var couponPromoName = $("#couponPromoName").val();
	if(couponPromoName == ""){
		alert("请输入活动名称");
		$("#couponPromoName").focus();
		return false;
	}
	
	var reg = /^[1-9]\d*$/;
	var couponType = $("input[name='couponType']:checked").val()
	if(couponType == 0){
		var orderQuota = $("#orderQuota").val();
		var couponAmount = $("#couponAmount").val();
		if(!reg.test(orderQuota)){
			alert("订单金额不符合标准"); 
			$("#orderQuota").focus();
			return false;
		}
		if(!reg.test(couponAmount)){
			alert("减少金额不符合标准"); 
			$("#couponAmount").focus();
			return false;
		}
		if(parseInt(couponAmount) > parseInt(orderQuota)){
			alert("订单金额必须大于减少金额");
			$("#orderQuota").focus();
			return false;
		}
	}else if(couponType == 1){ 
		var couponAmount = $("#couponAmount").val();
		if(!reg.test(couponAmount)){
			alert("减少金额不符合标准"); 
			$("#couponAmount").focus();
			return false;
		}
	}
	
	var couponApplyAmount = $("#couponApplyAmount").val();
	if(!reg.test(couponApplyAmount)){
		alert("申请数量不符合标准"); 
		$("#couponApplyAmount").focus();
		return false;
	}
	
	var couponValidType = $("input[name='couponValidType']:checked").val();
	if(couponValidType == 0){
		var couponValidDays = $("#couponValidDays").val();
		if(!reg.test(couponValidDays)){
			alert("有效期间不符合标准"); 
			$("#couponValidDays").focus();
			return false;
		}
	}else if(couponValidType == 1){
		var promoEndtime = $("#promoEndtime").val();
		if(promoEndtime == ""){
			alert("结束时间不能为空");
			return false;
		}
	}
	return true;
}

function emptyContent(){
	$("#applyUserName").val('');
	$("#couponPromoName").val('');
	$("#orderQuota").val('');
	$("#couponAmount").val('');
	$("#couponApplyAmount").val('');
	$("#couponValidDays").val('');
	$("#promoEndtime").val('');
}
