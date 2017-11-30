package com.atu.erp.dao;

import java.util.List;

import com.atu.erp.domain.BalanceInfo;
import com.atu.erp.domain.query.BalanceInfoQuery;

public interface BalanceInfoDao{
	
	public Integer insert(BalanceInfo balanceInfo);

	public int modify(BalanceInfo balanceInfo);

	public int countByCondition(BalanceInfoQuery balanceInfoQuery);
	
	public List<BalanceInfo> selectByCondition(BalanceInfoQuery balanceInfoQuery);
	
	public List<BalanceInfo> selectByConditionForPage(BalanceInfoQuery balanceInfoQuery);
	
}