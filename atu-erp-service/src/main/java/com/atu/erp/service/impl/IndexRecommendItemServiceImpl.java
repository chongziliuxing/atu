package com.atu.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atu.erp.dao.ItemDao;
import com.atu.erp.dao.SkuDao;
import com.atu.erp.domain.IndexRecommendItem;
import com.atu.erp.domain.Item;
import com.atu.erp.domain.Sku;
import com.atu.erp.domain.query.IndexRecommendItemQuery;
import com.atu.erp.domain.query.SkuQuery;
import com.atu.erp.manager.IndexRecommendItemManager;
import com.atu.erp.service.IndexRecommendItemService;


@Service(value = "IndexRecommendItemService")
public class IndexRecommendItemServiceImpl implements IndexRecommendItemService{
	@Autowired
	private IndexRecommendItemManager indexRecommendItemManager;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private SkuDao skuDao;
	
	@Override
	public Integer insert(IndexRecommendItem indexRecommendItem) {
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.setYn(1);
		skuQuery.setItemId(indexRecommendItem.getItemId());
		List<Sku> skuList = skuDao.selectByCondition(skuQuery);
		Sku  sku = null;
		if( null != skuList && 0 < skuList.size() ){
	        	sku = skuList.get(0);
	    }
		for (Sku sku2 : skuList) {
			if( sku.getTbPrice().compareTo( sku2.getTbPrice()) == 1 ){
                sku = sku2;
            }
		}
		indexRecommendItem.setSkuId(sku.getSkuId());
		return indexRecommendItemManager.insert(indexRecommendItem);
	}

	@Override
	public List<IndexRecommendItem> selectByCondition(IndexRecommendItemQuery indexRecommendItemQuery) {
		List<IndexRecommendItem> indexRecommendItemList = null;
		try{
			indexRecommendItemList =indexRecommendItemManager.selectByCondition(indexRecommendItemQuery);
			if(indexRecommendItemList!=null){
				for (IndexRecommendItem indexRecommendItem : indexRecommendItemList) {
					Item item = itemDao.selectByItemId(indexRecommendItem.getItemId());
					SkuQuery skuQuery = new SkuQuery();
					skuQuery.setItemId(item.getItemId());
					skuQuery.setYn(1);
					List<Sku> skuList = skuDao.selectByCondition(skuQuery);
					Sku sku = null;
			        if( null != skuList && 0 < skuList.size() ){
			        	sku = skuList.get(0);
			        }
			        for(Sku sku2 :skuList ){
			            if( sku.getTbPrice().compareTo( sku2.getTbPrice()) == 1 ){
			                sku = sku2;
			            }
			        }
			        item.setSku(sku);
			        indexRecommendItem.setItem(item);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return indexRecommendItemList;
	}

	@Override
	public void modify(IndexRecommendItem indexRecommendItem) {
		indexRecommendItemManager.modify(indexRecommendItem);
		
	}

}
