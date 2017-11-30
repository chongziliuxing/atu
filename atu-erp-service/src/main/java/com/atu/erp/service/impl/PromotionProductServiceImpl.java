package com.atu.erp.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.atu.erp.common.utils.PaginatedList;
import com.atu.erp.domain.Item;
import com.atu.erp.domain.PromotionProduct;
import com.atu.erp.domain.Sku;
import com.atu.erp.domain.query.PromotionProductQuery;
import com.atu.erp.domain.query.SkuQuery;
import com.atu.erp.manager.PromotionProductManager;
import com.atu.erp.service.ItemService;
import com.atu.erp.service.PromotionProductService;
import com.atu.erp.service.SkuService;

@Service(value = "promotionProductService")
public class PromotionProductServiceImpl implements PromotionProductService{
	@Autowired
	private PromotionProductManager promotionProductManager;
	@Autowired
	ItemService itemService;
	@Autowired
	SkuService skuService;

	@Override
	public Map<String, Object> queryPromotionList(PromotionProduct promotionProduct) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		PaginatedList<PromotionProduct>  promotionProductList= promotionProductManager.queryPromotionList(promotionProduct); 
		resultMap.put("promotionProductList", promotionProductList);
		resultMap.put("promotionProduct", promotionProduct);	
		return resultMap;
	}

	@Override
	public Map<String, Object> queryPromotionPList(PromotionProduct promotionProduct) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		PaginatedList<PromotionProduct>  promotionProductList= promotionProductManager.queryPromotionPList(promotionProduct); 
		resultMap.put("promotionProductList", promotionProductList);
		resultMap.put("promotionProduct", promotionProduct);	
		return resultMap;
	}
	
	@Override
	public Map<String, Object> queryItem(Integer itemId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//查询是否有该商品
		Item item = itemService.selectByItemId(itemId);
		if(item == null){
			resultMap.put("msg", "error");//无商品
			return resultMap;
		}
		SkuQuery skuQuery=new SkuQuery();
		skuQuery.setItemId(itemId);
		List<Sku> skuList =skuService.selectByCondition(skuQuery);
		resultMap.put("msg", "success");//有商品
		resultMap.put("item", item);
		resultMap.put("skuList", skuList);
		return resultMap;
	}

	@Override
	public Integer insert(PromotionProduct promotionProduct) {
		
		return promotionProductManager.insert(promotionProduct);
	}

	@Override
	public void modify(PromotionProduct promotionProduct) {
		promotionProductManager.modify(promotionProduct);
		
	}

	@Override
	public void modifyByPromtionId(PromotionProduct promotionProduct) {
		promotionProductManager.modifyByPromtionId(promotionProduct);
		
	}

	@Override
	public List<PromotionProduct> selectByCondition(
			PromotionProductQuery promotionProductQuery) {
		
		return promotionProductManager.selectByCondition(promotionProductQuery);
	}





}
