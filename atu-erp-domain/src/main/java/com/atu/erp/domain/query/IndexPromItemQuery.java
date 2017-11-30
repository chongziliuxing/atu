package com.atu.erp.domain.query;

import java.io.Serializable;
import java.util.Date;

import com.atu.erp.domain.common.BaseSearchForMysqlVo;

public class IndexPromItemQuery extends BaseSearchForMysqlVo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer promType;
	private String promName;
	private String promIntro;
	private String promUrl;
	private Integer skuId;
	private String promImgUrl;
	private Integer itemId;
	private Integer sortNum;
	private Date created;
	private Date modified;
	private Integer yn;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPromType() {
		return promType;
	}
	public void setPromType(Integer promType) {
		this.promType = promType;
	}
	public String getPromName() {
		return promName;
	}
	public void setPromName(String promName) {
		this.promName = promName;
	}
	public String getPromIntro() {
		return promIntro;
	}
	public void setPromIntro(String promIntro) {
		this.promIntro = promIntro;
	}
	public String getPromUrl() {
		return promUrl;
	}
	public void setPromUrl(String promUrl) {
		this.promUrl = promUrl;
	}
	public Integer getSkuId() {
		return skuId;
	}
	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}
	public String getPromImgUrl() {
		return promImgUrl;
	}
	public void setPromImgUrl(String promImgUrl) {
		this.promImgUrl = promImgUrl;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
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
	public Integer getYn() {
		return yn;
	}
	public void setYn(Integer yn) {
		this.yn = yn;
	}
}
