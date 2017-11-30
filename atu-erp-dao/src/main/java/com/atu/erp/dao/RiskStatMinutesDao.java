package com.atu.erp.dao;

import com.atu.erp.domain.query.RiskStatMinutesQuery;

public interface RiskStatMinutesDao {
	
	/**
	 * 条件查询PV、UV总数
	 * @param riskStatMinutesQuery
	 * @return
	 */
	public Integer selectPVUVCount(RiskStatMinutesQuery riskStatMinutesQuery);
}
