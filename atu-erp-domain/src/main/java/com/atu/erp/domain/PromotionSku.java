package com.atu.erp.domain;

import java.io.Serializable;
import java.util.Date;

public class PromotionSku implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 自增ID */
	private Integer id;
	
	/** 促销ID */
	private Integer promotionId;
	
	/** SKU_ID */
	private Integer skuId;
	
	/** 直降价格 */
	private Double deductionPrice;
	
	private Date modified;
	
	private Date created;
	
	/**yn */
	private Integer yn;

	/** 增加参数 */
	private Integer stock;
	private Integer itemStatus;
    private Double tbPrice;
    private Double lastPrice;
	private Integer itemId;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Double getDeductionPrice() {
		return deductionPrice;
	}

	public void setDeductionPrice(Double deductionPrice) {
		this.deductionPrice = deductionPrice;
	}

	public Integer getYn() {
		return yn;
	}

	public void setYn(Integer yn) {
		this.yn = yn;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(Integer itemStatus) {
		this.itemStatus = itemStatus;
	}

	public Double getTbPrice() {
		return tbPrice;
	}

	public void setTbPrice(Double tbPrice) {
		this.tbPrice = tbPrice;
	}

	public Double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
}
