package com.atu.erp.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.atu.erp.dao.RiskStatPvuvOrdersDao;
import com.atu.erp.domain.RiskStatPvuvOrders;
import com.atu.erp.domain.query.RiskStatPvuvOrdersQuery;

public class RiskStatPvuvOrdersDaoImpl extends SqlMapClientTemplate implements RiskStatPvuvOrdersDao{

	@Override
	public List<RiskStatPvuvOrders> selectByCondition(RiskStatPvuvOrdersQuery riskStatPvuvOrdersQuery) {
		return (List<RiskStatPvuvOrders>)queryForList("riskStatPvuvOrders.selectByCondition",riskStatPvuvOrdersQuery);
	}
	
	@Override
	public List<RiskStatPvuvOrders> selectByConditionForPage(
			RiskStatPvuvOrdersQuery riskStatPvuvOrdersQuery) {
		return (List<RiskStatPvuvOrders>)queryForList("riskStatPvuvOrders.selectByConditionForPage",riskStatPvuvOrdersQuery);
	}

	@Override
	public int countByCondition(RiskStatPvuvOrdersQuery riskStatPvuvOrdersQuery) {
		return (Integer)queryForObject("riskStatPvuvOrders.countByCondition",riskStatPvuvOrdersQuery);
	}

	@Override
	public RiskStatPvuvOrders riskStatPvuvOrdersSurvey(Integer projectId) {
		return (RiskStatPvuvOrders)queryForObject("riskStatPvuvOrders.riskStatPvuvOrdersSurvey",projectId);
	}
}
