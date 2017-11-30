package com.atu.erp.service;

import java.util.List;
import java.util.Map;

import com.atu.erp.domain.Address;
import com.atu.erp.domain.Item;
import com.atu.erp.domain.query.AddressQuery;
import com.atu.erp.domain.query.ItemPropertyQuery;
import com.atu.erp.domain.query.ItemQuery;

public interface ItemService {
	public List<Item> selectByConditionForPage(ItemQuery itemQuery);

	public Map<String, Object> queryItemList(ItemQuery itemQuery);
	
	public Integer insert(Item item);
	
	public void modify(Item item);
	
	public Item selectByItemId(int itemId);

}
