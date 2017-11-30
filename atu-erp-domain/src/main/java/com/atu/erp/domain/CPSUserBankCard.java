package com.atu.erp.domain;

import java.io.Serializable;

/**
 * 分销用户银行卡信息表
 *
 */
public class CPSUserBankCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	
	/** 分销用户ID */
	private Integer cpsUserId;
	
	/** 用户银行卡 */
	private String depositBank;
	
	/** 银行账号 */
	private Integer bankAccount;
	
	/** 身份证号 */
	private String idNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getcpsUserId() {
		return cpsUserId;
	}

	public void setcpsUserId(Integer cpsUserId) {
		this.cpsUserId = cpsUserId;
	}

	public String getDepositBank() {
		return depositBank;
	}

	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}

	public Integer getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(Integer bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
}
