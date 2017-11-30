package com.atu.erp.dao;

import java.util.List;

import com.atu.erp.domain.OrderShoppingCart;
import com.atu.erp.domain.query.OrderShoppingCartQuery;


public interface OrderShoppingCartDao{
	
	/**
	 * 添加订单购物车信息
	 * @param orderShoppingCart
	 * @return
	 */
	public Integer insert(OrderShoppingCart orderShoppingCart);


	/**
	 * 依据订单ID查询订单购物车信息
	 * @param orderId
	 * @return
	 */
	public List<OrderShoppingCart> selectByOrderId(int orderId);
	
	
}