package com.atu.erp.service;


import java.util.Map;

import com.atu.erp.common.utils.PaginatedList;
import com.atu.erp.domain.CPSUserWithdrawCash;
import com.atu.erp.domain.query.CPSUserWithdrawCashQuery;


public interface CpsUserWithdrawCashService {

	/**
	 * 分页查询
	 * @param CPSUserWithdrawCashQuery
	 * @return
	 */
	public Map<String, Object> getCPSUserWithdrawCashsByPage(CPSUserWithdrawCashQuery cpsUserWithdrawCashQuery);
	
	
}
