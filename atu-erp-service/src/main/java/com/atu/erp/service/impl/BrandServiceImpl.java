package com.atu.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atu.erp.domain.Brand;
import com.atu.erp.domain.query.BrandQuery;
import com.atu.erp.manager.BrandManager;
import com.atu.erp.service.BrandService;

@Service(value = "brandService")
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandManager brandManager;
	
	public Integer insert(Brand brand) {
		return brandManager.insert(brand);
	}
	
	public void modify(Brand brand) {
		brandManager.modify(brand);
	}
	
	public List<Brand> selectByCondition(BrandQuery brandQuery) {
		return brandManager.selectByCondition(brandQuery);
	}
	
	public List<Brand> selectByConditionForPage(BrandQuery brandQuery) {
		return brandManager.selectByConditionForPage(brandQuery);
	}

	public void delete(Integer brandId) {
		brandManager.delete(brandId);
	}
}
