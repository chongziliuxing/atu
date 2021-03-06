package com.atu.erp.domain.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.atu.erp.domain.common.BaseSearchForMysqlVo;


/**
 * 分销用户提取现金
 *
 */
public class CPSUserWithdrawCashQuery extends BaseSearchForMysqlVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 自增ID  */
	private Integer id;
	
	/** 分销用户ID  */
	private Integer cpsUserId;
	
	/** 微信用户标示 */
	private String openId;
	
	/**  卡类型（0 储蓄卡;1信用卡;2借记卡;3其他） */
	private Integer cardType;
	
	/**  银行ID */
	private Integer bankId;
	
	/**  银行账户 */
	private String bankAccount;
	
	/**  提取金额 */
	private BigDecimal withdrawAmount;
	
	/**  提取时间  */
	private Date widthdrawTime;
	
	/** 预计到账时间  */
	private Date predictTransferAccountTime;
	
	/**  提取状态(0提现申请中;1提现完成;2提现失败) */
	private Integer withdrawState;
	
	/** 微信转账订单号 **/
	private String transferOrderNumber;
	
	/**  备注 */
	private String remark;

	
	private Date widthdrawTimeStart;
	
	private Date widthdrawTimeEnd;
	
	
	public Date getWidthdrawTimeStart() {
		return widthdrawTimeStart;
	}

	public void setWidthdrawTimeStart(Date widthdrawTimeStart) {
		this.widthdrawTimeStart = widthdrawTimeStart;
	}

	public Date getWidthdrawTimeEnd() {
		return widthdrawTimeEnd;
	}

	public void setWidthdrawTimeEnd(Date widthdrawTimeEnd) {
		this.widthdrawTimeEnd = widthdrawTimeEnd;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getcpsUserId() {
		return cpsUserId;
	}

	public void setcpsUserId(Integer cpsUserId) {
		this.cpsUserId = cpsUserId;
	}
	
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public BigDecimal getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(BigDecimal withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public Date getWidthdrawTime() {
		return widthdrawTime;
	}

	public void setWidthdrawTime(Date widthdrawTime) {
		this.widthdrawTime = widthdrawTime;
	}

	public Date getPredictTransferAccountTime() {
		return predictTransferAccountTime;
	}

	public void setPredictTransferAccountTime(Date predictTransferAccountTime) {
		this.predictTransferAccountTime = predictTransferAccountTime;
	}

	public Integer getWithdrawState() {
		return withdrawState;
	}

	public void setWithdrawState(Integer withdrawState) {
		this.withdrawState = withdrawState;
	}

	public Integer getCpsUserId() {
		return cpsUserId;
	}

	public void setCpsUserId(Integer cpsUserId) {
		this.cpsUserId = cpsUserId;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTransferOrderNumber() {
		return transferOrderNumber;
	}

	public void setTransferOrderNumber(String transferOrderNumber) {
		this.transferOrderNumber = transferOrderNumber;
	}

}
