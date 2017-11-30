package com.atu.erp.domain.query;

import java.io.Serializable;
import java.math.BigDecimal;

import com.atu.erp.domain.common.BaseSearchForMysqlVo;

/**
 * 供应商 
 *
 */
public class CPSSupplierQuery extends BaseSearchForMysqlVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 自增ID */
	private Integer cpsSupplierId;
	
	/** 用户ID */
	private Integer userId;
	
	/** 商家ID */
	private Integer venderUserId;
	
	/** 分销标志(0默认未开通;1已开通) */
	private String supplierFlag;
	
	/** 佣金比例 */
	private BigDecimal comissionRate;
	
	public Integer getCpsSupplierId() {
		return cpsSupplierId;
	}

	public void setCpsSupplierId(Integer cpsSupplierId) {
		this.cpsSupplierId = cpsSupplierId;
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

	public String getSupplierFlag() {
		return supplierFlag;
	}

	public void setSupplierFlag(String supplierFlag) {
		this.supplierFlag = supplierFlag;
	}

	public BigDecimal getComissionRate() {
		return comissionRate;
	}

	public void setComissionRate(BigDecimal comissionRate) {
		this.comissionRate = comissionRate;
	}
}
