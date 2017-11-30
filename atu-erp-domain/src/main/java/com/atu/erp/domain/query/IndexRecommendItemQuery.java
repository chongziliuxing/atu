package com.atu.erp.domain.query;

import java.io.Serializable;
import java.util.Date;

/**
 * 推荐商品 
 *
 */
public class IndexRecommendItemQuery implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer recommendType;
	private String recommendName;
	private String recommendIntro;
	private String recommendUrl;
	private Integer skuId;
	private String recommendImgUrl;
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
	public Integer getRecommendType() {
		return recommendType;
	}
	public void setRecommendType(Integer recommendType) {
		this.recommendType = recommendType;
	}
	public String getRecommendName() {
		return recommendName;
	}
	public void setRecommendName(String recommendName) {
		this.recommendName = recommendName;
	}
	public String getRecommendIntro() {
		return recommendIntro;
	}
	public void setRecommendIntro(String recommendIntro) {
		this.recommendIntro = recommendIntro;
	}
	public String getRecommendUrl() {
		return recommendUrl;
	}
	public void setRecommendUrl(String recommendUrl) {
		this.recommendUrl = recommendUrl;
	}
	public Integer getSkuId() {
		return skuId;
	}
	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}
	public String getRecommendImgUrl() {
		return recommendImgUrl;
	}
	public void setRecommendImgUrl(String recommendImgUrl) {
		this.recommendImgUrl = recommendImgUrl;
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
