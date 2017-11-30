package com.atu.erp.manager;


import java.util.List;

import com.atu.erp.domain.PromotionSku;
import com.atu.erp.domain.query.PromotionSkuQuery;


public interface PromotionSkuManager {
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
