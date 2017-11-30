package com.atu.erp.dao;

import java.util.List;

import com.atu.erp.domain.RiskStatPvuvOrders;
import com.atu.erp.domain.query.RiskStatPvuvOrdersQuery;

public interface RiskStatPvuvOrdersDao {
	/**
	 * 根据相应的条件查询统计信息
	 * @param riskStatPvuvOrdersQuery
	 * @return
	 */
	public List<RiskStatPvuvOrders> selectByCondition(RiskStatPvuvOrdersQuery riskStatPvuvOrdersQuery);
	
	/**
	 * 根据相应的条件查询统计总数
	 * @param riskStatPvuvOrdersQuery
	 * @return
	 */
	public int countByCondition(RiskStatPvuvOrdersQuery riskStatPvuvOrdersQuery);
	
	/**
	 * 根据相应的条件查询统计pv uv 订单信息---分页查询
	 * @param riskStatPvuvOrdersQuery
	 * @return
	 */
	public List<RiskStatPvuvOrders> selectByConditionForPage(RiskStatPvuvOrdersQuery riskStatPvuvOrdersQuery);
	
	/**
	 * 统计pvuv订单的概况
	 * @return
	 */
	public RiskStatPvuvOrders riskStatPvuvOrdersSurvey(Integer projectId);
	
}
