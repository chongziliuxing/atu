package com.atu.erp.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 分销订单表
 *
 */
public class CPSUserOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 自增ID */
	private Integer id;
	
	/** 分销用户ID */
	private Integer cpsUserId;
	
	/** 佣金值 */
	private BigDecimal commissionAmout;
	
	/** 佣金状态 */
	private Integer commissionState;
	
	/** 创建时间 */
	private String createTime;
	
	/** 修改时间 */
	private String updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCpsUserId() {
		return cpsUserId;
	}

	public void setCpsUserId(Integer cpsUserId) {
		this.cpsUserId = cpsUserId;
	}

	public BigDecimal getCommissionAmout() {
		return commissionAmout;
	}

	public void setCommissionAmout(BigDecimal commissionAmout) {
		this.commissionAmout = commissionAmout;
	}

	public Integer getCommissionState() {
		return commissionState;
	}

	public void setCommissionState(Integer commissionState) {
		this.commissionState = commissionState;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
