package com.atu.erp.domain;

import java.io.Serializable;
import java.util.Date;

public class Brand implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 品牌ID */
    private Integer brandId;

    /** 品牌名称 */
    private String brandName;
    
    /** 创建时间 */
    private Date created;

    /** 修改时间 */
    private Date modified;

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
