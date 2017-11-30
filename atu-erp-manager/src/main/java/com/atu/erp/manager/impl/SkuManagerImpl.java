package com.atu.erp.manager.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atu.erp.dao.SkuDao;
import com.atu.erp.domain.Sku;
import com.atu.erp.domain.query.SkuQuery;
import com.atu.erp.manager.SkuManager;


@Repository
public class SkuManagerImpl implements SkuManager{
	
	@Autowired
	private SkuDao skuDao;

	@Override
	public List<Sku> selectByCondition(SkuQuery skuQuery) {
		return skuDao.selectByCondition(skuQuery);
	}

	@Override
	public Integer insert(Sku sku) {
		return skuDao.insert(sku);
	}

	@Override
	public void modifyByItemId(Sku sku) {
		skuDao.modifyByItemId(sku);
	}

	@Override
	public void insertOrUpdate(Sku sku) {
		skuDao.insertOrUpdate(sku);
		
	}

	@Override
	public Sku selectBySkuId(int skuId) {
		return skuDao.selectBySkuId(skuId);
	}


	

}
