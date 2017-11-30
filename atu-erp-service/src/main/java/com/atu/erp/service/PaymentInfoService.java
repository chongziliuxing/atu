package com.atu.erp.service;

import com.atu.erp.domain.PaymentInfo;

public interface PaymentInfoService {
	/**
	 * 获取支付成功的支付信息
	 * @param orderId
	 * @return
	 */
	public PaymentInfo getPaymentInfoByOrderId(Integer orderId, Integer orderPayType);
}
