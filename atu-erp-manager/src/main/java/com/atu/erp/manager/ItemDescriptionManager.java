package com.atu.erp.manager;


import com.atu.erp.domain.ItemDescription;


public interface ItemDescriptionManager {
	/**
	 * 添加商品描述信息
	 * @param itemDescription
	 * @return
	 */
	public Integer insert(ItemDescription itemDescription);
	/**
	 * 依据商品ID查询商品描述信息
	 * @param itemId
	 * @return
	 */
	public ItemDescription selectByItemId(int itemId);
	
	/**
	 * 依据商品ID修改商品描述信息
	 * @param itemDescription
	 */
	public void modify(ItemDescription itemDescription);

}
