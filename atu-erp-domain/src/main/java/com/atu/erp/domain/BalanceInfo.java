package com.atu.erp.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BalanceInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String bankNumber;
	private Integer balanceId;
	private Date balanceDateEnd;
	private Date balanceDateStart;
	private Date payDate;
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	private Integer venderUserId;
	private BigDecimal orderMoneyAll;
	private BigDecimal paymentGoods;
	private BigDecimal commission;
	private BigDecimal balanceMoney;//应结金额
	private BigDecimal balanceMoneyFact;//实结金额
	public BigDecimal getbalanceMoneyFact() {
		return balanceMoneyFact;
	}
	public void setbalanceMoneyFact(BigDecimal balanceMoneyFact) {
		this.balanceMoneyFact = balanceMoneyFact;
	}
	private BigDecimal oughtPayMoney;//本期应付

	public BigDecimal getOughtPayMoney() {
		return oughtPayMoney;
	}
	public void setOughtPayMoney(BigDecimal oughtPayMoney) {
		this.oughtPayMoney = oughtPayMoney;
	}
	private Integer balanceStatus;
	private Date created;
	public Date getBalanceDateEnd() {
		return balanceDateEnd;
	}
	public void setBalanceDateEnd(Date balanceDateEnd) {
		this.balanceDateEnd = balanceDateEnd;
	}
	public Date getBalanceDateStart() {
		return balanceDateStart;
	}
	public void setBalanceDateStart(Date balanceDateStart) {
		this.balanceDateStart = balanceDateStart;
	}
	private Date modified;
	private String reason;
	/**
     * 商家店铺名称
     */
    private String venderUserShopName;
	
	private List<OrderInfo> orderInfoList;
	
	public Integer getBalanceId() {
		return balanceId;
	}
	public void setBalanceId(Integer balanceId) {
		this.balanceId = balanceId;
	}
	public Integer getVenderUserId() {
		return venderUserId;
	}
	public void setVenderUserId(Integer venderUserId) {
		this.venderUserId = venderUserId;
	}
	public BigDecimal getOrderMoneyAll() {
		return orderMoneyAll;
	}
	public void setOrderMoneyAll(BigDecimal orderMoneyAll) {
		this.orderMoneyAll = orderMoneyAll;
	}
	public BigDecimal getPaymentGoods() {
		return paymentGoods;
	}
	public void setPaymentGoods(BigDecimal paymentGoods) {
		this.paymentGoods = paymentGoods;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public BigDecimal getBalanceMoney() {
		return balanceMoney;
	}
	public void setBalanceMoney(BigDecimal balanceMoney) {
		this.balanceMoney = balanceMoney;
	}
	public Integer getBalanceStatus() {
		return balanceStatus;
	}
	public void setBalanceStatus(Integer balanceStatus) {
		this.balanceStatus = balanceStatus;
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
	public List<OrderInfo> getOrderInfoList() {
		return orderInfoList;
	}
	public void setOrderInfoList(List<OrderInfo> orderInfoList) {
		this.orderInfoList = orderInfoList;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getVenderUserShopName() {
		return venderUserShopName;
	}
	public void setVenderUserShopName(String venderUserShopName) {
		this.venderUserShopName = venderUserShopName;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	
	
}
