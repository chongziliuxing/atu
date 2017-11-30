package com.atu.erp.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.atu.erp.common.utils.PaginatedList;
import com.atu.erp.domain.OrderInfo;
import com.atu.erp.domain.query.OrderInfoQuery;
import com.atu.erp.service.result.Result;

public interface OrderInfoService {
	/**
	 * 分页查询
	 * @param orderInfoQuery
	 * @return
	 */
	public PaginatedList<OrderInfo> getOrderInfosByPage(OrderInfoQuery orderInfoQuery);
	
	/**
	 * 根据订单号以及商家id查询订单详细信息
	 * @param orderInfoQuery
	 * @return
	 */
	public OrderInfo getOrderInfoByOrderId(OrderInfoQuery orderInfoQuery);
	
	/**
	 * 锁定订单
	 * @param orderId
	 * @param venderUserId
	 * @return
	 */
	public Map<String, Object> lockOrder(Integer orderId, Integer venderUserId, String lockReason);
	
	/**
	 * 解锁订单
	 * @param orderId
	 * @param venderUserId
	 * @return
	 */
	public Map<String, Object> unLockOrder(Integer orderId, Integer venderUserId);
	
	/**
	 * 确认收款
	 * @param orderInfo
	 * @return
	 */
	public Map<String, Object> confirmGetPrice(Integer orderId, Integer venderUserId);
	
	/**
	 * 尾款收款
	 * @param orderInfo
	 * @return
	 */
	public Map<String, Object> confirmGetLastPrice(Integer orderId, Integer venderUserId);
	
	/**
	 * 订单完成
	 * @param orderInfo
	 * @return
	 */
	public Map<String, Object> updateOrderInfoFinish(Integer orderId, Integer venderUserId);
	
	/**
	 * 添加商家补录信息
	 * @param orderId
	 * @param orderPayType
	 * @param paymentMode
	 * @param paymentMoney
	 * @return
	 */
	public Map<String, Object> addSellerEntry(Integer orderId, Integer orderPayType, Integer paymentMode, BigDecimal paymentMoney, Integer venderUserId);
	
	public Map<String, Object> sendGoods(Integer orderId, Integer venderId);
	
	/**
	 * 获取所有，导出用
	 * @param orderInfoQuery
	 * @return
	 */
	public List<OrderInfo> selectByCondition(OrderInfoQuery orderInfoQuery);
	
	/**
	 * 根据订单号查询订单详细信息
	 * @param orderInfoQuery
	 * @return
	 */
	public Map<String, Object> selectOrderDetail(OrderInfoQuery orderInfoQuery);
	
	/**
	 * 下线确认收款
	 * @param orderId paymentNumber
	 * @return
	 */
	public Map<String, Object> confirmMoney(Integer orderId,String paymentNumber,Date payTime);
}
