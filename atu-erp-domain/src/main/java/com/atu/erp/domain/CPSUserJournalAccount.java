package com.atu.erp.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  用户账户日志表
 *
 */
public class CPSUserJournalAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 自增ID */
	private Integer id;
	
	/** 用户 */
	private Integer userId;
	
	/** 分销用户ID */
	private Integer cpsUserId;
	
	/** 增加金额 */
	private BigDecimal amountPlus;
	
	/** 操作时间 */
	private String operateTime;
	
	/** 操作类型(0佣金;1佣金扣除;2提现扣除;3提现锁住;) */
	private Integer operateType;
	
	/** 金额变化原因 */
	private String cause;
	
	/** 减少金额 */
	private BigDecimal amountMinus;
	
	/** 余额 */
	private BigDecimal amountRemain;
	
	/** 操作id[ 订单id 或者交易id ] */
	private Integer operateId;
	
	public void setCpsUserId(Integer cpsUserId) {
		this.cpsUserId = cpsUserId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCpsUserId() {
		return cpsUserId;
	}

	public void setCpseUserId(Integer cpsUserId) {
		this.cpsUserId = cpsUserId;
	}
	
	public BigDecimal getAmountPlus() {
		return amountPlus;
	}

	public void setAmountPlus(BigDecimal amountPlus) {
		this.amountPlus = amountPlus;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public BigDecimal getAmountMinus() {
		return amountMinus;
	}

	public void setAmountMinus(BigDecimal amountMinus) {
		this.amountMinus = amountMinus;
	}

	public BigDecimal getAmountRemain() {
		return amountRemain;
	}

	public void setAmountRemain(BigDecimal amountRemain) {
		this.amountRemain = amountRemain;
	}
	
	public Integer getOperateId() {
		return operateId;
	}

	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}
	
}
