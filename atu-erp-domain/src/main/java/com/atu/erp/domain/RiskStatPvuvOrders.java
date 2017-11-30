package com.atu.erp.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RiskStatPvuvOrders implements Serializable{

	private static final long serialVersionUID = 1L;

	/** ID */
	private Integer id;
	/** 项目编号 */
	private Integer projectId;
	/** 页面编号 */
	private Integer pageId;
	/** 页面PV */
	private Integer pv;
	/** 页面UV */
	private Integer uv;
	/** 下订单数量 */
	private Integer orderNumber;
	/** 下单客户数量 */
	private Integer orderConsumer;
	/** 下单金额 */
	private BigDecimal orderMoney;
	/** KEY时间 */
	private Date keyDate;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date modifiedTime;
	/** 客单价 */
	private BigDecimal guestUnitMoney;
	/** 下单商品件数*/
	private Integer ItemCount;
	/** 转化率 */
	private BigDecimal conversionRate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id=id;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId=projectId;
	}
	public Integer getPageId() {
		return pageId;
	}
	public void setPageId(Integer pageId) {
		this.pageId=pageId;
	}
	public Integer getPv() {
		return pv;
	}
	public void setPv(Integer pv) {
		this.pv=pv;
	}
	public Integer getUv() {
		return uv;
	}
	public void setUv(Integer uv) {
		this.uv=uv;
	}
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber=orderNumber;
	}
	public Integer getOrderConsumer() {
		return orderConsumer;
	}
	public void setOrderConsumer(Integer orderConsumer) {
		this.orderConsumer=orderConsumer;
	}
	public BigDecimal getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney=orderMoney;
	}
	public Date getKeyDate() {
		return keyDate;
	}
	public void setKeyDate(Date keyDate) {
		this.keyDate=keyDate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime=modifiedTime;
	}
	public BigDecimal getGuestUnitMoney() {
		return guestUnitMoney;
	}
	public void setGuestUnitMoney(BigDecimal guestUnitMoney) {
		this.guestUnitMoney = guestUnitMoney;
	}
	public Integer getItemCount() {
		return ItemCount;
	}
	public void setItemCount(Integer itemCount) {
		ItemCount = itemCount;
	}
	public BigDecimal getConversionRate() {
		return conversionRate;
	}
	public void setConversionRate(BigDecimal conversionRate) {
		this.conversionRate = conversionRate;
	}
	
}
