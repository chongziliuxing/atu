package com.atu.erp.domain.query;

import java.io.Serializable;
import java.util.Date;

import com.atu.erp.domain.common.BaseSearchForMysqlVo;

public class CommentQuery  extends BaseSearchForMysqlVo implements Serializable{


	private static final long serialVersionUID = 1L;

	private Integer id;
	private String itemName;//商品名称
	private String userName;//用户名称
	private Integer categoryId1;//一级分类id
	private Integer categoryId2;//二级分类id
	private Integer categoryId3;//三级分类id
	private Integer categoryId4;//四级分类id
	private Date modifiedStart;//结算开始时间
	private Date modifiedEnd;//结算截止时间
	public Date getModifiedStart() {
		return modifiedStart;
	}

	public void setModifiedStart(Date modifiedStart) {
		this.modifiedStart = modifiedStart;
	}

	public Date getModifiedEnd() {
		return modifiedEnd;
	}

	public void setModifiedEnd(Date modifiedEnd) {
		this.modifiedEnd = modifiedEnd;
	}

	public Integer getCategoryId1() {
		return categoryId1;
	}

	public void setCategoryId1(Integer categoryId1) {
		this.categoryId1 = categoryId1;
	}

	public Integer getCategoryId2() {
		return categoryId2;
	}

	public void setCategoryId2(Integer categoryId2) {
		this.categoryId2 = categoryId2;
	}

	public Integer getCategoryId3() {
		return categoryId3;
	}

	public void setCategoryId3(Integer categoryId3) {
		this.categoryId3 = categoryId3;
	}

	public Integer getCategoryId4() {
		return categoryId4;
	}

	public void setCategoryId4(Integer categoryId4) {
		this.categoryId4 = categoryId4;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.sku_id
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private Integer skuId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.item_id
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private Integer itemId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.user_id
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private Integer userId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.title
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private String title;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.content
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private String content;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.score
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private Byte score;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.useless_count
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private Integer uselessCount;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.useful_count
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private Integer usefulCount;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.advantage
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private String advantage;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.disadvantage
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private String disadvantage;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.order_id
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private Integer orderId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.topped
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private Byte topped;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.status
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private Byte status;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.deleted
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private Byte deleted;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.integral
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private Short integral;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.ip
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private String ip;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.sort_number
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private Integer sortNumber;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.created
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private Date created;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column comment.modified
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	private Date modified;

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.id
	 * @return  the value of comment.id
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.id
	 * @param id  the value for comment.id
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.sku_id
	 * @return  the value of comment.sku_id
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public Integer getSkuId() {
		return skuId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.sku_id
	 * @param skuId  the value for comment.sku_id
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.item_id
	 * @return  the value of comment.item_id
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public Integer getItemId() {
		return itemId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.item_id
	 * @param itemId  the value for comment.item_id
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.user_id
	 * @return  the value of comment.user_id
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.user_id
	 * @param userId  the value for comment.user_id
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.title
	 * @return  the value of comment.title
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.title
	 * @param title  the value for comment.title
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.content
	 * @return  the value of comment.content
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public String getContent() {
		return content;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.content
	 * @param content  the value for comment.content
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.score
	 * @return  the value of comment.score
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public Byte getScore() {
		return score;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.score
	 * @param score  the value for comment.score
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setScore(Byte score) {
		this.score = score;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.useless_count
	 * @return  the value of comment.useless_count
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public Integer getUselessCount() {
		return uselessCount;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.useless_count
	 * @param uselessCount  the value for comment.useless_count
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setUselessCount(Integer uselessCount) {
		this.uselessCount = uselessCount;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.useful_count
	 * @return  the value of comment.useful_count
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public Integer getUsefulCount() {
		return usefulCount;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.useful_count
	 * @param usefulCount  the value for comment.useful_count
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setUsefulCount(Integer usefulCount) {
		this.usefulCount = usefulCount;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.advantage
	 * @return  the value of comment.advantage
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public String getAdvantage() {
		return advantage;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.advantage
	 * @param advantage  the value for comment.advantage
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.disadvantage
	 * @return  the value of comment.disadvantage
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public String getDisadvantage() {
		return disadvantage;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.disadvantage
	 * @param disadvantage  the value for comment.disadvantage
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setDisadvantage(String disadvantage) {
		this.disadvantage = disadvantage;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.order_id
	 * @return  the value of comment.order_id
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.order_id
	 * @param orderId  the value for comment.order_id
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.topped
	 * @return  the value of comment.topped
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public Byte getTopped() {
		return topped;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.topped
	 * @param topped  the value for comment.topped
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setTopped(Byte topped) {
		this.topped = topped;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.status
	 * @return  the value of comment.status
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.status
	 * @param status  the value for comment.status
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.deleted
	 * @return  the value of comment.deleted
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public Byte getDeleted() {
		return deleted;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.deleted
	 * @param deleted  the value for comment.deleted
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setDeleted(Byte deleted) {
		this.deleted = deleted;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.integral
	 * @return  the value of comment.integral
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public Short getIntegral() {
		return integral;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.integral
	 * @param integral  the value for comment.integral
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setIntegral(Short integral) {
		this.integral = integral;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.ip
	 * @return  the value of comment.ip
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.ip
	 * @param ip  the value for comment.ip
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.sort_number
	 * @return  the value of comment.sort_number
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public Integer getSortNumber() {
		return sortNumber;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.sort_number
	 * @param sortNumber  the value for comment.sort_number
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.created
	 * @return  the value of comment.created
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.created
	 * @param created  the value for comment.created
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column comment.modified
	 * @return  the value of comment.modified
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column comment.modified
	 * @param modified  the value for comment.modified
	 * @abatorgenerated  Tue Feb 10 11:09:20 CST 2015
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}
}