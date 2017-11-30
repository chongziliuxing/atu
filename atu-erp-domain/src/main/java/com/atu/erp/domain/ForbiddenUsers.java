package com.atu.erp.domain;

import java.io.Serializable;
import java.util.Date;

public class ForbiddenUsers   implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer forbiddenId;

    private Integer userId;

    private String mobile;

    private Date createTime;

    private String reason;

    private Integer createUserId;

    public Integer getForbiddenId() {
        return forbiddenId;
    }

    public void setForbiddenId(Integer forbiddenId) {
        this.forbiddenId = forbiddenId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }
}