package com.atu.erp.service;

import java.util.List;

import com.atu.erp.domain.IndexSellItem;
import com.atu.erp.domain.query.IndexSellItemQuery;


public interface IndexSellItemService {
	public Integer insert(IndexSellItem indexSellItem);
	
	public List<IndexSellItem> selectByCondition(IndexSellItemQuery indexSellItemQuery);
	
	public void modify(IndexSellItem indexSellItem);
}
