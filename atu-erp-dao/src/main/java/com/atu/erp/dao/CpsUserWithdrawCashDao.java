package com.atu.erp.dao;

import java.util.List;

import com.atu.erp.domain.CPSUserWithdrawCash;
import com.atu.erp.domain.OrderInfo;
import com.atu.erp.domain.query.BalanceInfoQuery;
import com.atu.erp.domain.query.CPSUserWithdrawCashQuery;
import com.atu.erp.domain.query.OrderInfoQuery;

public interface CpsUserWithdrawCashDao {

	/**
	 * 添加提现信息
	 * @param orderInfo
	 * @return
	 */
	public Integer insert(CPSUserWithdrawCash cpsUserWithdrawCash);

	/**
	 * 依据订单ID修改提现信息
	 * @param orderInfo
	 */
	public int modify(CPSUserWithdrawCash cpsUserWithdrawCash);

	/**
	 * 依据订单ID查询提现信息
	 * @param userId
	 * @return
	 */
	public CPSUserWithdrawCash selectByCPSUserWithdrawCashId(Integer id);
	
	/**
	 * 根据相应的条件查询满足条件的订单信息的总数
	 * @param orderInfoQuery
	 * @return
	 */
	public int countByCondition(CPSUserWithdrawCashQuery cpsUserWithdrawCashQuery);
	
	/**
	 * 根据相应的条件查询订单信息
	 * @param orderInfoQuery
	 * @return
	 */
	public List<CPSUserWithdrawCash> selectByCondition(CPSUserWithdrawCashQuery cpsUserWithdrawCashQuery);
	
	/**
	 * 根据相应的条件查询订单信息---分页查询
	 * @param orderInfoQuery
	 * @return
	 */
	public List<CPSUserWithdrawCash> selectByConditionForPage(CPSUserWithdrawCashQuery cpsUserWithdrawCashQuery);
	
	
}
