package com.atu.erp.manager;

import java.util.List;

import com.atu.erp.domain.IndexImage;
import com.atu.erp.domain.IndexPromItem;
import com.atu.erp.domain.query.IndexImageQuery;
import com.atu.erp.domain.query.IndexPromItemQuery;


public interface IndexPromItemManager {
	public Integer insert(IndexPromItem indexPromItem);
	
	public List<IndexPromItem> selectByCondition(IndexPromItemQuery indexPromItemQuery);
	
	public void modify(IndexPromItem indexPromItem);

}
