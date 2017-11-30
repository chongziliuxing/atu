package com.atu.erp.dao;

import java.util.List;

import com.atu.erp.domain.IndexPromItem;
import com.atu.erp.domain.query.IndexPromItemQuery;

public interface IndexPromItemDao{
	
	/**
	 * 添加商品信息
	 * @param item
	 * @return
	 */
	public Integer insert(IndexPromItem indexPromItem);

	/**
	 * 依据商品ID修改商品信息
	 * @param item
	 */
	public void modify(IndexPromItem indexPromItem);

	
	/**
	 * 根据相应的条件查询满足条件的商品信息的总数
	 * @param itemQuery
	 * @return
	 */
	public int countByCondition(IndexPromItemQuery indexPromItemQuery);
	
	/**
	 * 根据相应的条件查询商品信息
	 * @param itemQuery
	 * @return
	 */
	public List<IndexPromItem> selectByCondition(IndexPromItemQuery indexPromItemQuery);
	
	/**
	 * 根据相应的条件查询商品信息---分页查询
	 * @param itemQuery
	 * @return
	 */
	public List<IndexPromItem> selectByConditionForPage(IndexPromItemQuery indexPromItemQuery);
}