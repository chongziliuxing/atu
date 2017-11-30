package com.atu.erp.domain;

import java.io.Serializable;
import java.util.Date;

public class RiskPvuvInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	/** ID */
	private Integer id;
	/** 页面ID(首页:100,搜索:1002,购物车:1003,订单列表:1004,收藏列表:1005,商品详情:1006,下单页面:1007,支付页面:1008) */
	private Integer pageId;
	/** 页面名称 */
	private String pageName;
	/** 页面PV */
	private Integer pv;
	/** 页面UV */
	private Integer uv;
	/** 项目编号(pc:10,h5:20,android:30,ios:40) */
	private Integer projectId;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date modifiedTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id=id;
	}
	public Integer getPageId() {
		return pageId;
	}
	public void setPageId(Integer pageId) {
		this.pageId=pageId;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName=pageName;
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
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId=projectId;
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
}
