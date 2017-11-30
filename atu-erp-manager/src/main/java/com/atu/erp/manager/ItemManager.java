package com.atu.erp.manager;

import java.util.List;

import com.atu.erp.domain.Item;
import com.atu.erp.domain.query.ItemQuery;

public interface ItemManager {
	public List<Item> selectByConditionForPage(ItemQuery itemQuery);
	
	public int countByConditionForPage(ItemQuery itemQuery);
	
	public int countByCondition(ItemQuery itemQuery);
	
	public List<ItemQuery> selectByConditionWithPage(ItemQuery itemQuery);
	
	public Integer insert(Item item);
	
	public void modify(Item item);
	
	public Item selectByItemId(int itemId);

}
