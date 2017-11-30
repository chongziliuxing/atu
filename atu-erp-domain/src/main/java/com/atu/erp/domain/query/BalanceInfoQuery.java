package com.atu.erp.domain.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.atu.erp.domain.common.BaseSearchForMysqlVo;

public class BalanceInfoQuery extends BaseSearchForMysqlVo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer balanceId;
	private Date balanceDateStart;//结算开始时间
	private Date balanceDateEnd;//结算截止时间
	private Integer venderUserId;
	private BigDecimal orderMoneyAll;
	private BigDecimal paymentGoods;
	private BigDecimal commission;
	private BigDecimal balanceMoney;
	private BigDecimal balanceMoneyFact;//应结金额
	private Integer balanceStatus;
	private Date created;
	private Date modified;
	private String reason;
	private String venderUserShopName;
	private Date startTime;
	private Date endTime;
	private BigDecimal oughtPayMoney;//本期应付
	private String bankNumber; 
	
	public BigDecimal getbalanceMoneyFact() {
		return balanceMoneyFact;
	}
	public void setbalanceMoneyFact(BigDecimal balanceMoneyFact) {
		this.balanceMoneyFact = balanceMoneyFact;
	}
	
	public BigDecimal getOughtPayMoney() {
		return oughtPayMoney;
	}
	public void setOughtPayMoney(BigDecimal oughtPayMoney) {
		this.oughtPayMoney = oughtPayMoney;
	}
	public Integer getBalanceId() {
		return balanceId;
	}
	public void setBalanceId(Integer balanceId) {
		this.balanceId = balanceId;
	}
	public Date getBalanceDateStart() {
		return balanceDateStart;
	}
	public void setBalanceDateStart(Date balanceDateStart) {
		this.balanceDateStart = balanceDateStart;
	}
	public Date getBalanceDateEnd() {
		return balanceDateEnd;
	}
	public void setBalanceDateEnd(Date balanceDateEnd) {
		this.balanceDateEnd = balanceDateEnd;
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
