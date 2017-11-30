package com.atu.erp.service;

import java.util.Map;

import com.atu.erp.common.utils.PaginatedList;
import com.atu.erp.domain.RiskStatPvuvOrders;
import com.atu.erp.domain.query.RiskStatPvuvOrdersQuery;

public interface RiskStatPvuvOrdersService {
	
	public PaginatedList<RiskStatPvuvOrders> getRiskStatPvuvOrdersForPage(RiskStatPvuvOrdersQuery riskStatPvuvOrdersQuery);
	
	public RiskStatPvuvOrders getRiskStatPvuvOrdersSurvey(Integer projectId);
	
	public Map<String, Object> getChartInfo(RiskStatPvuvOrdersQuery riskStatPvuvOrdersQuery);
}
