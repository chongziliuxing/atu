package com.atu.erp.dao;

import java.util.List;

import com.atu.erp.domain.OrderConsignee;
import com.atu.erp.domain.query.OrderConsigneeQuery;


public interface OrderConsigneeDao {
	/**
	 * 根据相应的条件查询收货人信息
	 * @param OrderConsigneeQuery
	 * @return
	 */
	public List<OrderConsignee> selectByCondition(OrderConsigneeQuery orderConsigneeQuery);
	
	/**
	 * 修改收货人信息
	 * @param orderConsignee
	 * @return
	 */
	public void modify(OrderConsignee orderConsignee);
	
}