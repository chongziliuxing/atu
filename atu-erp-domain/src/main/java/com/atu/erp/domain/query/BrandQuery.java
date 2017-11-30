package com.atu.erp.domain.query;

import java.io.Serializable;

import com.atu.erp.domain.common.BaseSearchForMysqlVo;

public class BrandQuery extends BaseSearchForMysqlVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 品牌ID */
    private Integer brandId;

    /** 品牌名称 */
    private String brandName;
    
    public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
}
