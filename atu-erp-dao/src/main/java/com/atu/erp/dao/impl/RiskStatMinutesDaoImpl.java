package com.atu.erp.dao.impl;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.atu.erp.dao.RiskStatMinutesDao;
import com.atu.erp.domain.query.RiskStatMinutesQuery;

public class RiskStatMinutesDaoImpl extends SqlMapClientTemplate implements RiskStatMinutesDao{

	@Override
	public Integer selectPVUVCount(RiskStatMinutesQuery riskStatMinutesQuery) {
		return (Integer) queryForObject("riskStatMinutes.selectPVUVCount",riskStatMinutesQuery);
	}

}
