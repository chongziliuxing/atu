package com.atu.erp.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atu.erp.dao.BrandDao;
import com.atu.erp.domain.Brand;
import com.atu.erp.domain.query.BrandQuery;
import com.atu.erp.manager.BrandManager;

@Repository
public class BrandManagerImpl implements BrandManager {

	@Autowired
	private BrandDao brandDao;
	
	private final static Log LOG = LogFactory.getLog(BrandManagerImpl.class);
	
	public Integer insert(Brand brand) {
		return brandDao.insert(brand);
	}
	
	public void modify(Brand brand) {
		brandDao.modify(brand);
	}
	
	public List<Brand> selectByCondition(BrandQuery brandQuery) {
		return brandDao.selectByCondition(brandQuery);
	}
	
	public List<Brand> selectByConditionForPage(BrandQuery brandQuery) {
		return brandDao.selectByConditionForPage(brandQuery);
	}

	public void delete(Integer brandId) {
		brandDao.delete(brandId);
	}
}
