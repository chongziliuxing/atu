package com.atu.erp.manager;

import java.util.List;

import com.atu.erp.domain.Brand;
import com.atu.erp.domain.query.BrandQuery;

public interface BrandManager {

	public Integer insert(Brand brand);
	
	public void modify(Brand brand);
	
	public List<Brand> selectByCondition(BrandQuery brandQuery);
	
	public List<Brand> selectByConditionForPage(BrandQuery brandQuery);
	
	public void delete(Integer brandId);
}
