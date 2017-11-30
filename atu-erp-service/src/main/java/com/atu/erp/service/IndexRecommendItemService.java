package com.atu.erp.service;

import java.util.List;

import com.atu.erp.domain.IndexRecommendItem;
import com.atu.erp.domain.query.IndexRecommendItemQuery;


public interface IndexRecommendItemService {
	public Integer insert(IndexRecommendItem indexRecommendItem);
	
	public List<IndexRecommendItem> selectByCondition(IndexRecommendItemQuery indexRecommendItemQuery);
	
	public void modify(IndexRecommendItem indexRecommendItem);
}
