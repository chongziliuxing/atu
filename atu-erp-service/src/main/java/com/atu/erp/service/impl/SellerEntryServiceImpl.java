package com.atu.erp.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.atu.erp.dao.SellerEntryDao;
import com.atu.erp.domain.SellerEntry;
import com.atu.erp.service.SellerEntryService;

@Service(value="sellerEntryService")
public class SellerEntryServiceImpl implements SellerEntryService {

	private SellerEntryDao sellerEntryDao;
		
	@Override
	public BigDecimal selectSumPayMoneyByCondition(Integer orderId, Integer orderPayType) {
		SellerEntry sellerEntry = new SellerEntry();
		sellerEntry.setOrderId(orderId);
		sellerEntry.setOrderPayType(orderPayType);
		Integer result = sellerEntryDao.selectSumPayMoneyByCondition(sellerEntry);
		if(result != null){
			return new BigDecimal(result).divide(new BigDecimal(100));
		}
		return null;
	}

	public void setSellerEntryDao(SellerEntryDao sellerEntryDao) {
		this.sellerEntryDao = sellerEntryDao;
	}

}
