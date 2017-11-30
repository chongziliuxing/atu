package com.atu.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atu.erp.dao.ItemDao;
import com.atu.erp.dao.SkuDao;
import com.atu.erp.domain.IndexRecommendItem;
import com.atu.erp.domain.IndexSellItem;
import com.atu.erp.domain.Item;
import com.atu.erp.domain.Sku;
import com.atu.erp.domain.query.IndexSellItemQuery;
import com.atu.erp.domain.query.SkuQuery;
import com.atu.erp.manager.IndexSellItemManager;
import com.atu.erp.service.IndexSellItemService;


@Service(value = "IndexSellItemService")
public class IndexSellItemServiceImpl implements IndexSellItemService{
	@Autowired
	private IndexSellItemManager indexSellItemManager;
	@Autowired
	private SkuDao skuDao;
	@Autowired
	private ItemDao itemDao;
	
	@Override
	public Integer insert(IndexSellItem indexSellItem) {
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.setYn(1);
		skuQuery.setItemId(indexSellItem.getItemId());
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
		indexSellItem.setSkuId(sku.getSkuId());
		return indexSellItemManager.insert(indexSellItem);
	}

	@Override
	public List<IndexSellItem> selectByCondition(IndexSellItemQuery indexSellItemQuery) {
		List<IndexSellItem> indexSellItemList = null;
		try{
			indexSellItemList =indexSellItemManager.selectByCondition(indexSellItemQuery);
			if(indexSellItemList!=null){
				for (IndexSellItem indexSellItem : indexSellItemList) {
					Item item = itemDao.selectByItemId(indexSellItem.getItemId());
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
			        indexSellItem.setItem(item);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return indexSellItemList;
	}

	@Override
	public void modify(IndexSellItem indexSellItem) {
		indexSellItemManager.modify(indexSellItem);
		
	}




}
