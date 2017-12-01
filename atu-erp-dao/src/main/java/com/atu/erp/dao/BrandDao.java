package com.atu.erp.dao;

import java.util.List;

import com.atu.erp.domain.Brand;
import com.atu.erp.domain.query.BrandQuery;

public interface BrandDao {

	/**
	 * 添加品牌信息1
	 * @param category
	 * @return
	 */
	public Integer insert(Brand brand);

	/**
	 * 依据品牌ID修改分类信息
	 * @param category
	 */
	public void modify(Brand brand);
	
	/**
	 * 根据相应的条件查询品牌信息
	 * @param categoryQuery
	 * @return
	 */
	public List<Brand> selectByCondition(BrandQuery brandQuery);
	
	/**
	 * 根据相应的条件查询分类信息---分页查询
	 * @param categoryQuery
	 * @return
	 */
	public List<Brand> selectByConditionForPage(BrandQuery brandQuery);
	
	public void delete(Integer brandId);
}
