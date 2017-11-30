package com.atu.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atu.erp.domain.Sku;
import com.atu.erp.domain.query.SkuQuery;
import com.atu.erp.manager.SkuManager;
import com.atu.erp.service.SkuService;


@Service(value = "skuService")
public class SkuServiceImpl implements SkuService{
	@Autowired
	private SkuManager skuManager;

	@Override
	public List<Sku> selectByCondition(SkuQuery skuQuery) {

		return skuManager.selectByCondition(skuQuery);
	}

	@Override
	public Integer insert(Sku sku) {

		return skuManager.insert(sku);
	}

	@Override
	public void modifyByItemId(Sku sku) {
		skuManager.modifyByItemId(sku);
		
	}

	@Override
	public void insertOrUpdate(Sku sku) {
		skuManager.insertOrUpdate(sku);
		
	}

	@Override
	public Sku selectBySkuId(int skuId) {
		return skuManager.selectBySkuId(skuId);
	}
	
	



}
