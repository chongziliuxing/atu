package com.atu.erp.dao;

import java.util.List;

import com.atu.erp.domain.PromotionInfo;


public interface PromotionDAO {

	int queryPromotionCount(PromotionInfo promotionInfo);

	List<PromotionInfo> queryPromotionList(PromotionInfo promotionInfo);

}