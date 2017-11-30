package com.atu.erp.manager;

import java.util.List;

import com.atu.erp.domain.IndexImage;
import com.atu.erp.domain.IndexSellItem;
import com.atu.erp.domain.query.IndexImageQuery;
import com.atu.erp.domain.query.IndexSellItemQuery;


public interface IndexSellItemManager {
	public Integer insert(IndexSellItem indexSellItem);
	
	public List<IndexSellItem> selectByCondition(IndexSellItemQuery indexSellItemQuery);
	
	public void modify(IndexSellItem indexSellItem);

}
