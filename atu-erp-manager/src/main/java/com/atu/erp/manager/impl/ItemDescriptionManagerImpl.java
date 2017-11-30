package com.atu.erp.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atu.erp.dao.ItemDescriptionDao;
import com.atu.erp.domain.ItemDescription;
import com.atu.erp.manager.ItemDescriptionManager;


@Repository
public class ItemDescriptionManagerImpl implements ItemDescriptionManager{
	
	@Autowired
	private ItemDescriptionDao itemDescriptionDao;
	private final static Log LOG = LogFactory.getLog(ItemDescriptionManagerImpl.class);
	@Override
	public Integer insert(ItemDescription itemDescription) {
		return itemDescriptionDao.insert(itemDescription);
	}
	@Override
	public ItemDescription selectByItemId(int itemId) {
		
		return itemDescriptionDao.selectByItemId(itemId);
	}
	@Override
	public void modify(ItemDescription itemDescription) {
		itemDescriptionDao.modify(itemDescription);
		
	}

	

}
