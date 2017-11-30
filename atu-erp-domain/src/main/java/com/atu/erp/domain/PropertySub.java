package com.atu.erp.domain;

import java.io.Serializable;
import java.util.Date;

public class PropertySub implements Serializable{

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
    
    /** 创建时间 */
    private Date created;

    /** 修改时间 */
    private Date modified;

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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
}
