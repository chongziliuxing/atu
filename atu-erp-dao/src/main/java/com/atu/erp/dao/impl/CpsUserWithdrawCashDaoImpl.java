package com.atu.erp.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.atu.erp.dao.CpsUserWithdrawCashDao;
import com.atu.erp.domain.CPSUserWithdrawCash;
import com.atu.erp.domain.OrderInfo;
import com.atu.erp.domain.query.CPSUserWithdrawCashQuery;

public class CpsUserWithdrawCashDaoImpl extends SqlMapClientTemplate implements CpsUserWithdrawCashDao {

	@Override
	public Integer insert(CPSUserWithdrawCash cpsUserWithdrawCash) {
		return (Integer) insert("CPSUserWithdrawCash.insert",cpsUserWithdrawCash);
	}

	@Override
	public int modify(CPSUserWithdrawCash cpsUserWithdrawCash) {
		return update("CPSUserWithdrawCash.updateByPrimaryKey",cpsUserWithdrawCash);
	}

	@Override
	public CPSUserWithdrawCash selectByCPSUserWithdrawCashId(Integer id) {
		return (CPSUserWithdrawCash) queryForObject("CPSUserWithdrawCash.selectByPrimaryKey", id);
	}

	@Override
	public int countByCondition(CPSUserWithdrawCashQuery cpsUserWithdrawCashQuery) {
		return (Integer) queryForObject("CPSUserWithdrawCash.countByCondition", cpsUserWithdrawCashQuery);
	}

	@Override
	public List<CPSUserWithdrawCash> selectByCondition(
			CPSUserWithdrawCashQuery cpsUserWithdrawCashQuery) {
		return (List<CPSUserWithdrawCash>)queryForList("CPSUserWithdrawCash.selectByCondition", cpsUserWithdrawCashQuery);
	}

	@Override
	public List<CPSUserWithdrawCash> selectByConditionForPage(
			CPSUserWithdrawCashQuery cpsUserWithdrawCashQuery) {
		return (List<CPSUserWithdrawCash>)queryForList("CPSUserWithdrawCash.selectByConditionForPage", cpsUserWithdrawCashQuery);
	}

	

	
}
