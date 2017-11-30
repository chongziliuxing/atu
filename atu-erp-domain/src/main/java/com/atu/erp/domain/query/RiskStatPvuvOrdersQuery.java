package com.atu.erp.domain.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.atu.erp.domain.common.BaseSearchForMysqlVo;

public class RiskStatPvuvOrdersQuery extends BaseSearchForMysqlVo implements Serializable{

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
	/** 下单商品数量 */
	private Integer itemCount;
	/** KEY时间 */
	private Date keyDate;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date modifiedTime;
	/** 开始时间 */
	private Date startTime;
	/** 结束时间 */
	private Date endTime;
	/** 天数 */
	private Integer days;
	
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
	public Integer getItemCount() {
		return itemCount;
	}
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	
	
}
