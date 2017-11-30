package com.atu.erp.manager;

import com.atu.erp.common.utils.PaginatedList;
import com.atu.erp.domain.PromotionInfo;



public interface PromotionInfoManager {

	PaginatedList<PromotionInfo> queryPromotionList(PromotionInfo promotionInfo);
	/**
	 * 添加促销信息
	 * @param promotionInfo
	 * @return
	 */
	public Integer insert(PromotionInfo promotionInfo);
	/**
	 * 依据促销ID修改促销信息
	 * @param promotionInfo
	 */
	public void modify(PromotionInfo promotionInfo);
	PromotionInfo selectByPromotionId(int promotionId);




}
