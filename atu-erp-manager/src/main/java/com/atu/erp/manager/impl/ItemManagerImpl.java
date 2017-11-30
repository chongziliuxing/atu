package com.atu.erp.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atu.erp.dao.ItemDao;
import com.atu.erp.domain.Item;
import com.atu.erp.domain.query.ItemQuery;
import com.atu.erp.manager.ItemManager;


@Repository
public class ItemManagerImpl implements ItemManager{
	
	@Autowired
	private ItemDao itemDao;
	private final static Log LOG = LogFactory.getLog(ItemManagerImpl.class);
	@Override
	public List<Item> selectByConditionForPage(ItemQuery itemQuery) {
		
		return itemDao.selectByConditionForPage(itemQuery);
	}
	@Override
	public int countByConditionForPage(ItemQuery itemQuery) {
		
		return itemDao.countByConditionForPage(itemQuery);
	}
	@Override
	public List<ItemQuery> selectByConditionWithPage(ItemQuery itemQuery) {
		// TODO Auto-generated method stub
		if(itemQuery.getCategoryId1() == null || itemQuery.getCategoryId1() == 1)
			itemQuery.setCategoryId1(null);
		if(itemQuery.getCategoryId2() == null || itemQuery.getCategoryId2() == 1)
			itemQuery.setCategoryId2(null);
		
		return itemDao.selectByConditionWithPage(itemQuery);
	}
	@Override
	public int countByCondition(ItemQuery itemQuery) {
		return itemDao.countByCondition(itemQuery);
	}
	@Override
	public Integer insert(Item item) {
		return itemDao.insert(item);
	}
	@Override
	public void modify(Item item) {
		itemDao.modify(item);
		
	}
	@Override
	public Item selectByItemId(int itemId) {
		
		return itemDao.selectByItemId(itemId);
	}


}
