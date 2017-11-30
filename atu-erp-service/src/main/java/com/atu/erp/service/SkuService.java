package com.atu.erp.service;

import java.util.List;


import com.atu.erp.domain.Sku;
import com.atu.erp.domain.query.SkuQuery;

public interface SkuService {
	public List<Sku> selectByCondition(SkuQuery skuQuery);
	
	public Integer insert(Sku sku);
	
	public void modifyByItemId(Sku sku);
	
	public void insertOrUpdate(Sku sku);

	public Sku selectBySkuId(int skuId);
}
