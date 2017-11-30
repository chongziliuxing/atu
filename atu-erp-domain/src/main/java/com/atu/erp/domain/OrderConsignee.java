package com.atu.erp.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单多收货地址
 *
 */
public class OrderConsignee implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** ID */
    private Integer id;
    
    /** 用户ID */
    private Integer userId;
    
    /** 商家ID */
    private Integer venderUserId;
    
    /** 订单ID */
    private Integer orderId;

    /** 购买数量 */
    private Integer buyNum;
    
    /** 购买金额 */
    private BigDecimal buyMoney;

    /** 收货人姓名 */
    private String consigneeName;

    /** 收货人省份 */
    private Integer consigneeProvince;

    /** 收货人省份名称 */
    private String consigneeProvinceName;

    /** 收货人市区 */
    private Integer consigneeCity;

    /** 收货人市区名称 */
    private String consigneeCityName;

    /** 收货人县区 */
    private Integer consigneeCounty;

    /** 收货人县区名称 */
    private String consigneeCountyName;

    /** 收货人详细地址 */
    private String consigneeAddress;

    /** 收货人手机号 */
    private String consigneeMobile;
    
    /** 快递信息 */
    private String express;
    
    /** 创建时间 */
    private Date created;

    /** 修改时间 */
    private Date modified;
    
    /** 是否可用 */
    private Integer yn;

    private String address;
    
    private List<OrderDetail> orderDetailList;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getVenderUserId() {
		return venderUserId;
	}

	public void setVenderUserId(Integer venderUserId) {
		this.venderUserId = venderUserId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public BigDecimal getBuyMoney() {
		return buyMoney;
	}

	public void setBuyMoney(BigDecimal buyMoney) {
		this.buyMoney = buyMoney;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public Integer getConsigneeProvince() {
		return consigneeProvince;
	}

	public void setConsigneeProvince(Integer consigneeProvince) {
		this.consigneeProvince = consigneeProvince;
	}

	public String getConsigneeProvinceName() {
		return consigneeProvinceName;
	}

	public void setConsigneeProvinceName(String consigneeProvinceName) {
		this.consigneeProvinceName = consigneeProvinceName;
	}

	public Integer getConsigneeCity() {
		return consigneeCity;
	}

	public void setConsigneeCity(Integer consigneeCity) {
		this.consigneeCity = consigneeCity;
	}

	public String getConsigneeCityName() {
		return consigneeCityName;
	}

	public void setConsigneeCityName(String consigneeCityName) {
		this.consigneeCityName = consigneeCityName;
	}

	public Integer getConsigneeCounty() {
		return consigneeCounty;
	}

	public void setConsigneeCounty(Integer consigneeCounty) {
		this.consigneeCounty = consigneeCounty;
	}

	public String getConsigneeCountyName() {
		return consigneeCountyName;
	}

	public void setConsigneeCountyName(String consigneeCountyName) {
		this.consigneeCountyName = consigneeCountyName;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getConsigneeMobile() {
		return consigneeMobile;
	}

	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
	
}