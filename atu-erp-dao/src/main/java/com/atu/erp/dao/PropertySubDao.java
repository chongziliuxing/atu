package com.atu.erp.dao;

import java.util.List;

import com.atu.erp.domain.PropertySub;
import com.atu.erp.domain.query.PropertySubQuery;

public interface PropertySubDao {

	/**
	 * 添加品牌信息
	 * @param category
	 * @return
	 */
	public Integer insert(PropertySub propertySub);

	/**
	 * 依据品牌ID修改分类信息
	 * @param category
	 */
	public void modify(PropertySub propertySub);
	
	/**
	 * 根据相应的条件查询品牌信息
	 * @param categoryQuery
	 * @return
	 */
	public List<PropertySub> selectByCondition(PropertySubQuery propertySubQuery);
	
}
