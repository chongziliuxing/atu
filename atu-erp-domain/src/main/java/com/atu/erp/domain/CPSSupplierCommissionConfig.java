package com.atu.erp.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 供货商佣金配置
 *
 */
public class CPSSupplierCommissionConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 自增ID */
	private Integer id;
	
	/** 用户ID */
	private Integer userId;
	
	/** 冗余商家ID */
	private Integer venderUserId;
	
	/** 供应商ID */
	private Integer cpsSupplierId;
	
	/** 佣金比例 */
	private BigDecimal commissionRatio;
	
	/** 分销商品ID */
	private Integer itemId;

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

	public Integer getVenderUserId() {
		return venderUserId;
	}

	public void setVenderUserId(Integer venderUserId) {
		this.venderUserId = venderUserId;
	}

	public Integer getCpsSupplierId() {
		return cpsSupplierId;
	}

	public void setCpsSupplierId(Integer cpsSupplierId) {
		this.cpsSupplierId = cpsSupplierId;
	}

	public BigDecimal getCommissionRatio() {
		return commissionRatio;
	}

	public void setCommissionRatio(BigDecimal commissionRatio) {
		this.commissionRatio = commissionRatio;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
}
