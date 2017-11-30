package com.atu.erp.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atu.erp.domain.ItemImage;
import com.atu.erp.manager.ItemImageManager;
import com.atu.erp.service.ItemImageService;


@Service(value = "itemImageService")
public class ItemImageServiceImpl implements ItemImageService{
	@Autowired
	private ItemImageManager itemImageManager;

	@Override
	public Integer insert(ItemImage itemImage) {
		return itemImageManager.insert(itemImage);
	}

	@Override
	public List<ItemImage> selectByItemId(int itemId) {
		
		return itemImageManager.selectByItemId(itemId);
	}

	@Override
	public void modify(ItemImage itemImage) {
		itemImageManager.modify(itemImage);
		
	}



}
