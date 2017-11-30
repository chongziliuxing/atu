package com.atu.erp.domain.query;

import java.io.Serializable;

import com.atu.erp.domain.common.BaseSearchForMysqlVo;

/**
 * 分销商品表
 *
 */
public class CPSUserItemQuery extends BaseSearchForMysqlVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 自增ID */
	private Integer id;
	
	/** 用户ID */
	private Integer user_id;
	
	/** 商品ID */
	private Integer item_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
}
