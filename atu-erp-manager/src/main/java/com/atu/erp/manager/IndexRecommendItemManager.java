package com.atu.erp.manager;

import java.util.List;

import com.atu.erp.domain.IndexImage;
import com.atu.erp.domain.IndexRecommendItem;
import com.atu.erp.domain.query.IndexImageQuery;
import com.atu.erp.domain.query.IndexRecommendItemQuery;


public interface IndexRecommendItemManager {
	public Integer insert(IndexRecommendItem indexRecommendItem);
	
	public List<IndexRecommendItem> selectByCondition(IndexRecommendItemQuery indexRecommendItemQuery);
	
	public void modify(IndexRecommendItem indexRecommendItem);

}
