package com.atu.erp.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *  分销用户信息表
 *
 */
public class CPSUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 分销者用户ID */
	private Integer cpsUserId;
	
	/** 分销者标记(0默认不是分销者;1分销者) */
	private String cpsUserFlag;
	
	/** 支付密码 */
	private String paymentPwd;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 修改时间 */
	private Date updateTime;
	
	/** 总入账 */
	private BigDecimal allEnterMoney;
	
	/** 总出账 */
	private BigDecimal allOutMoney;
	

	public Integer getCpsUserId() {
		return cpsUserId;
	}

	public void setCpsUserId(Integer cpsUserId) {
		this.cpsUserId = cpsUserId;
	}

	public String getCpsUserFlag() {
		return cpsUserFlag;
	}

	public void setCpsUserFlag(String cpsUserFlag) {
		this.cpsUserFlag = cpsUserFlag;
	}

	public String getPaymentPwd() {
		return paymentPwd;
	}

	public void setPaymentPwd(String paymentPwd) {
		this.paymentPwd = paymentPwd;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getAllEnterMoney() {
		return allEnterMoney;
	}

	public void setAllEnterMoney(BigDecimal allEnterMoney) {
		this.allEnterMoney = allEnterMoney;
	}

	public BigDecimal getAllOutMoney() {
		return allOutMoney;
	}

	public void setAllOutMoney(BigDecimal allOutMoney) {
		this.allOutMoney = allOutMoney;
	}
}	
