package com.atu.erp.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单信息
 *
 */
public class OrderInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /** 订单ID */
    private Integer orderId;
    
    /** 订单类型（1、全额订单  2、定金） */
    private Integer orderType;

    /** 商家ID */
    private Integer venderUserId;

    /** 用户ID */
    private Integer userId;

    /** 收货人姓名 */
    private String consigneeName;

    /** 收货人省份 */
    private Integer consigneeProvince;

    /** 收货人市区 */
    private Integer consigneeCity;

    /** 收货人县区 */
    private Integer consigneeCounty;

    /** 收货人详细地址 */
    private String consigneeAddress;

    /** 收货人手机号 */
    private String consigneeMobile;

    /** 备注 */
    private String remark;

    /** 订单总金额 */
    private BigDecimal orderMoney;

    /** 优惠总金额 */
    private BigDecimal discountMoney;

    /** 优惠明细 */
    private String discountInfo;

    /** 应付金额 */
    private BigDecimal oughtPayMoney;

    /** 实际支付金额 */
    private BigDecimal payMoney;

    /** 应收尾款金额 */
    private BigDecimal oughtFinalMoney;

    /** 实收尾款金额 */
    private BigDecimal finalMoney;

    /** 下单时间 */
    private Date orderTime;

    /** 发货时间 */
    private Date sendOutTime;

    /** 订单完成时间 */
    private Date finishTime;

    /** 订单状态（0-新建订单  1-支付完成  2-确认收款  3-尾款支付完成  4-确认尾款 5-商家已发货 6-用户收货 确认 	50-订单完成） */
    private Integer orderStatus;

    /** 下单IP */
    private String ip;

    /** 锁定状态 */
    private Integer lockStatus;

    /** 锁定原因 */
    private String lockReason;

    /** 创建时间 */
    private Date created;

    /** 修改时间 */
    private Date modified;
    
    /** 订单详情集合 */
    private List<OrderDetail> orderDetail;
    
    /** 订单收货人集合*/
    private List<OrderConsignee> orderConsigneeList;
    
    /** 订单贷款金额，在计算页详情会用到*/
    private BigDecimal paymentGoodsPrice;
	
    /** 支付时间*/
    private Date payTime;
    
    /** 结算单id */
    private Integer balanceId;
    
    /** 结算时间 */
    private Date balanceDate;
    
    /** 佣金*/
    private BigDecimal commission;
    
    /** 用户信息*/
    private UserInfo userInfo;
    
    /** 订单收货人信息*/
    private OrderConsignee orderConsignee;
    
    public Integer getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(Integer balanceId) {
		this.balanceId = balanceId;
	}

	public Date getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}
    public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
    
    public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
    
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getVenderUserId() {
        return venderUserId;
    }

    public void setVenderUserId(Integer venderUserId) {
        this.venderUserId = venderUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getConsigneeCity() {
        return consigneeCity;
    }

    public void setConsigneeCity(Integer consigneeCity) {
        this.consigneeCity = consigneeCity;
    }

    public Integer getConsigneeCounty() {
        return consigneeCounty;
    }

    public void setConsigneeCounty(Integer consigneeCounty) {
        this.consigneeCounty = consigneeCounty;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDiscountInfo() {
        return discountInfo;
    }

    public void setDiscountInfo(String discountInfo) {
        this.discountInfo = discountInfo;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getSendOutTime() {
        return sendOutTime;
    }

    public void setSendOutTime(Date sendOutTime) {
        this.sendOutTime = sendOutTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }

    public String getLockReason() {
        return lockReason;
    }

    public void setLockReason(String lockReason) {
        this.lockReason = lockReason;
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

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}
	
	public List<OrderConsignee> getOrderConsigneeList() {
		return orderConsigneeList;
	}

	public void setOrderConsigneeList(List<OrderConsignee> orderConsigneeList) {
		this.orderConsigneeList = orderConsigneeList;
	}
	
	// 获取订单最终需要支付的总金额
	public BigDecimal getBigDecimalOrderPrice(){
		if(this.getDiscountMoney() == null){
			return this.getOrderMoney();
		}
		return this.getOrderMoney().subtract(this.getDiscountMoney());
	}

	public BigDecimal getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}

	public BigDecimal getDiscountMoney() {
		return discountMoney;
	}

	public void setDiscountMoney(BigDecimal discountMoney) {
		this.discountMoney = discountMoney;
	}

	public BigDecimal getOughtPayMoney() {
		return oughtPayMoney;
	}

	public void setOughtPayMoney(BigDecimal oughtPayMoney) {
		this.oughtPayMoney = oughtPayMoney;
	}

	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	public BigDecimal getOughtFinalMoney() {
		return oughtFinalMoney;
	}

	public void setOughtFinalMoney(BigDecimal oughtFinalMoney) {
		this.oughtFinalMoney = oughtFinalMoney;
	}

	public BigDecimal getFinalMoney() {
		return finalMoney;
	}

	public void setFinalMoney(BigDecimal finalMoney) {
		this.finalMoney = finalMoney;
	}

	public BigDecimal getPaymentGoodsPrice() {
		return paymentGoodsPrice;
	}

	public void setPaymentGoodsPrice(BigDecimal paymentGoodsPrice) {
		this.paymentGoodsPrice = paymentGoodsPrice;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public OrderConsignee getOrderConsignee() {
		return orderConsignee;
	}

	public void setOrderConsignee(OrderConsignee orderConsignee) {
		this.orderConsignee = orderConsignee;
	}
	
}