package com.atu.erp.domain.query;

import java.io.Serializable;

public class PropertySubQuery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 商品子属性ID */
    private Integer propertySubId;
    
    /** 商品属性ID */
    private Integer propertyId;

    /** 商品子属性名称 */
    private String propertySubName;
    
	public Integer getPropertySubId() {
		return propertySubId;
	}

	public void setPropertySubId(Integer propertySubId) {
		this.propertySubId = propertySubId;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertySubName() {
		return propertySubName;
	}

	public void setPropertySubName(String propertySubName) {
		this.propertySubName = propertySubName;
	}
}
