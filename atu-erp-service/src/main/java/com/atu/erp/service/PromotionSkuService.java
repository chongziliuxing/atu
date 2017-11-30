package com.atu.erp.service;

import java.util.List;

import com.atu.erp.domain.PromotionInfo;
import com.atu.erp.domain.PromotionSku;
import com.atu.erp.domain.query.PromotionSkuQuery;

public interface PromotionSkuService {
	/**
	 * 添加促销信息
	 * @param promotionInfo
	 * @return
	 */
	public Integer insert(PromotionSku promotionSku);

	public List<PromotionSku> selectByCondition(
			PromotionSkuQuery promotionSkuQuery);
	
	public void modifyByPromotionId(PromotionSku promotionSku);

}
