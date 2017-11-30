package com.atu.erp.domain.query;

import java.io.Serializable;
import java.util.Date;

import com.atu.erp.domain.common.BaseSearchForMysqlVo;

public class RiskStatMinutesQuery extends BaseSearchForMysqlVo implements Serializable{

	private static final long serialVersionUID = 1L;

	/** ID */
	private Integer id;
	/** 项目编号(pc:10,h5:20,android:30,ios:40) */
	private Integer projectId;
	/** 页面ID(首页:100,搜索:1002,购物车:1003,订单列表:1004,收藏列表:1005,商品详情:1006,下单页面:1007,支付页面:1008) */
	private Integer pageId;
	/** 业务类型编号(pv:1,uv:2) */
	private Integer typeId;
	/** 分钟数 */
	private Integer minutes;
	/** 数量 */
	private Integer count;
	/** KEY时间 */
	private Date keyDate;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date modifiedTime;
	/** 分钟数值 */
	private Integer minuteValue;
	/** 分钟数开始值 */
	private Integer minuteValueStart;
	/** 分钟数结束值 */
	private Integer minuteValueEnd;
	
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
	public Integer getMinutes() {
		return minutes;
	}
	public void setMinutes(Integer minutes) {
		this.minutes=minutes;
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
	public Integer getMinuteValue() {
		return minuteValue;
	}
	public void setMinuteValue(Integer minuteValue) {
		this.minuteValue=minuteValue;
	}
	public Integer getMinuteValueStart() {
		return minuteValueStart;
	}
	public void setMinuteValueStart(Integer minuteValueStart) {
		this.minuteValueStart = minuteValueStart;
	}
	public Integer getMinuteValueEnd() {
		return minuteValueEnd;
	}
	public void setMinuteValueEnd(Integer minuteValueEnd) {
		this.minuteValueEnd = minuteValueEnd;
	}
}
