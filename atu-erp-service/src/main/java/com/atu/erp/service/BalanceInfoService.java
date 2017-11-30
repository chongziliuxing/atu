package com.atu.erp.service;

import java.util.List;
import java.util.Map;

import com.atu.erp.common.utils.PaginatedList;
import com.atu.erp.domain.BalanceInfo;
import com.atu.erp.domain.query.BalanceInfoQuery;
import com.atu.erp.service.result.Result;

public interface BalanceInfoService {
	/**
	 * 分页查询
	 * @param orderInfoQuery
	 * @return
	 */
	public PaginatedList<BalanceInfo> getBalanceInfosByPage(BalanceInfoQuery balanceInfoQuery);
	
	/**
	 * 根据订单号以及商家id查询订单详细信息
	 * @param orderInfoQuery
	 * @return
	 */
	public BalanceInfo getBalanceInfoByBalanceId(BalanceInfoQuery balanceInfoQuery);
	
	/**
	 * 确认结算
	 * @param orderId
	 * @param venderUserId
	 * @return
	 */
	
	public Map<String, Object> doAgree(BalanceInfo balanceInfo);
	/**
	 * 获取所有，导出用
	 * @param balanceInfoQuery
	 * @return
	 */
	
	public List<BalanceInfo> selectByCondition(BalanceInfoQuery balanceInfoQuery);
	
	/**
	 * 添加结算信息
	 * @param balanceInfo
	 * @param orderId
	 * @return
	 */
	public Map<String, Object> addBalanceInfo(BalanceInfo balanceInfo,Integer orderId);
}
