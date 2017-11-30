package com.atu.erp.service;

import java.util.Map;

import com.atu.erp.domain.PromotionInfo;

public interface PromotionInfoService {

	Map<String, Object> queryPromotionList(PromotionInfo promotionInfo);

	Map<String, Object> queryItem(Integer itemId);

	public Integer insert(PromotionInfo promotionInfo);

	public void modify(PromotionInfo promotionInfo);
	
	public PromotionInfo selectByPromotionId(int promotionId);


}
