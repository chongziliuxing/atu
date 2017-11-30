package com.atu.erp.domain.query;

import java.io.Serializable;

import com.atu.erp.domain.common.BaseSearchForMysqlVo;

/**
 * 银行信息表
 *
 */
public class CPSBankQuery extends BaseSearchForMysqlVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	private Integer bankId;
	
	/**
	 * 银行名称
	 */
	private String bankName;

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
