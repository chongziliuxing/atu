$(function(){
	$("#query").bind("click",function(){
		$("#f1").submit();
	});
});

function checkOrderNum(orderNumber){
	var num = /^[0-9]{18}$/;
	if(num.test(orderNumber)){
		return true;
	}else{
		alert("微信转账订单号必须为18位");
		return false;
	}
}

function updateBtn1(id){
		var orderNumber = $("#"+id).val();
		if(checkOrderNum(orderNumber)){
			if(confirm("确定提现完成？")){
				$.post("/withdrawals/editState",{id:id,withdrawState:1,transferOrderNumber:orderNumber},function(data){
					if (data.success ) {
						$("#"+id).attr("readOnly",true);
						$("#"+id).css("border","0");
						var html = '<font color="red">提现完成</font>';
						$("#btn_"+id).html(html);
						$("#state_"+id).html('提现完成');
					}else {
						alert("网络异常，请稍后重试！");
					}
			});
			}
		}
}

function updateBtn2(id){
		var orderNumber = $("#"+id).val();
		if(checkOrderNum(orderNumber)){
			if(confirm("确定提现失败？")){
				$.post("/withdrawals/editState",{id:id,withdrawState:2,transferOrderNumber:orderNumber},function(data){
					if (data.success) {
						$("#"+id).attr("readOnly",true);
						$("#"+id).css("border","0");
						var html = '<font color="red">提现失败</font>';
						$("#btn_"+id).html(html);
						$("#state_"+id).html('提现失败');
					}else {
						alert("网络异常，请稍后重试！");
					}
			});
			}
		}
}










