package com.atu.erp.service;

import java.math.BigDecimal;

import com.atu.erp.domain.SellerEntry;

public interface SellerEntryService {
	/**
	 * 获取该订单商家补录金额
	 * @param orderId
	 * @param orderPayType
	 * @return
	 */
	public BigDecimal selectSumPayMoneyByCondition(Integer orderId, Integer orderPayType);
}
