package com.atu.erp.service;

import java.util.List;

import com.atu.erp.domain.IndexPromItem;
import com.atu.erp.domain.query.IndexPromItemQuery;


public interface IndexPromItemService {
	public Integer insert(IndexPromItem indexPromItem);
	
	public List<IndexPromItem> selectByCondition(IndexPromItemQuery indexPromItemQuery);
	
	public void modify(IndexPromItem indexPromItem);
}
