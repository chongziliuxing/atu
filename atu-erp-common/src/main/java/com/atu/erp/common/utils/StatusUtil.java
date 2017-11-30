package com.atu.erp.common.utils;

public class StatusUtil {

	public static String getBlanceStatusName(int balanceStatus) {
		String name = "";
		if (balanceStatus == 1) {
			name = "待审核";
		} else if (balanceStatus == 10) {
			name = "商家已确认";
		} else if (balanceStatus == 2) {
			name = "审核通过";
		} else if (balanceStatus == 3) {
			name = "审核驳回";
		} else if (balanceStatus == 4) {
			name = "结算完成";
		} else if (balanceStatus == 5) {
			name = "冻结";
		}
		return name;
	}
	
	public static String getOrderStatusName(int orderStatus) {
		String name = "";
		if (orderStatus == 0) {
			name = "新建订单";
		} else if (orderStatus == 1) {
			name = "支付完成";
		} else if (orderStatus == 5) {
			name = "商家发货确认";
		} else if (orderStatus == 6) {
			name = "用户收货确认";
		} else if (orderStatus == 50) {
			name = "订单完成";
		} else if (orderStatus == 51) {
			name = "已取消订单";
		}
		return name;
	}
}
