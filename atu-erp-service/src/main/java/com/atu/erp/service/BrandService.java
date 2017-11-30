package com.atu.erp.service;

import java.util.List;

import com.atu.erp.domain.Brand;
import com.atu.erp.domain.query.BrandQuery;

public interface BrandService {

	public Integer insert(Brand brand);
	
	public void modify(Brand brand);
	
	public void delete(Integer brandId);
	
	public List<Brand> selectByCondition(BrandQuery brandQuery);
	
	public List<Brand> selectByConditionForPage(BrandQuery brandQuery);
}
