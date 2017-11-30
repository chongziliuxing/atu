package com.atu.erp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atu.erp.dao.PaymentInfoDao;
import com.atu.erp.domain.PaymentInfo;
import com.atu.erp.domain.query.PaymentInfoQuery;
import com.atu.erp.service.PaymentInfoService;

@Service(value="paymentInfoService")
public class PaymentInfoServiceImpl implements PaymentInfoService {
	private PaymentInfoDao paymentInfoDao;
	
	@Override
	public PaymentInfo getPaymentInfoByOrderId(Integer orderId, Integer orderPayType) {
		PaymentInfoQuery paymentInfoQuery = new PaymentInfoQuery();
		paymentInfoQuery.setOrderId(orderId);
		paymentInfoQuery.setOrderPayType(orderPayType);
		paymentInfoQuery.setPaymentInfoType(2);//支付成功确认信息

		List<PaymentInfo> list = paymentInfoDao.selectByCondition(paymentInfoQuery);
		if(list == null || list.size() == 0){
			return null;
		}
		return list.get(0);
	}

	public void setPaymentInfoDao(PaymentInfoDao paymentInfoDao) {
		this.paymentInfoDao = paymentInfoDao;
	}

}
