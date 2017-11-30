package com.atu.erp.service;

import java.util.List;

import com.atu.erp.domain.ItemImage;


public interface ItemImageService {
	public Integer insert(ItemImage itemImage);
	
	public List<ItemImage> selectByItemId(int itemId);
	/**
	 * 依据商品图片ID修改商品图片信息
	 * @param itemImage
	 */
	public void modify(ItemImage itemImage);


}
