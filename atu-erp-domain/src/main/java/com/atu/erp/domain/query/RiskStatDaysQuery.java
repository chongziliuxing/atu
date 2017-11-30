package com.atu.erp.domain.query;

import java.io.Serializable;
import java.util.Date;

import org.apache.velocity.runtime.visitor.BaseVisitor;

public class RiskStatDaysQuery extends BaseVisitor implements Serializable{

	private static final long serialVersionUID = 1L;

	/** ID */
	private Integer id;
	/** 项目编号(pc:10,h5:20,android:30,ios:40) */
	private Integer projectId;
	/** 页面ID(首页:100,搜索:1002,购物车:1003,订单列表:1004,收藏列表:1005,商品详情:1006,下单页面:1007,支付页面:1008) */
	private Integer pageId;
	/** 业务类型编号(pv:1,uv:2) */
	private Integer typeId;
	/** 天数 */
	private Integer days;
	/** 数量 */
	private Integer count;
	/** KEY时间 */
	private Date keyDate;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date modifiedTime;
	/** 天数值 */
	private Integer dayValue;
	
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
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId=typeId;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days=days;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count=count;
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
	public Integer getDayValue() {
		return dayValue;
	}
	public void setDayValue(Integer dayValue) {
		this.dayValue=dayValue;
	}
}
