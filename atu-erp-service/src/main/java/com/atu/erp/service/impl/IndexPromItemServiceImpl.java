package com.atu.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atu.erp.dao.ItemDao;
import com.atu.erp.dao.SkuDao;
import com.atu.erp.domain.IndexPromItem;
import com.atu.erp.domain.IndexRecommendItem;
import com.atu.erp.domain.Item;
import com.atu.erp.domain.Sku;
import com.atu.erp.domain.query.IndexPromItemQuery;
import com.atu.erp.domain.query.SkuQuery;
import com.atu.erp.manager.IndexPromItemManager;
import com.atu.erp.service.IndexPromItemService;


@Service(value = "IndexPromItemService")
public class IndexPromItemServiceImpl implements IndexPromItemService{
	@Autowired
	private IndexPromItemManager indexPromItemManager;
	@Autowired
	private SkuDao skuDao;
	@Autowired
	private ItemDao itemDao;
	
	@Override
	public Integer insert(IndexPromItem indexPromItem) {
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.setYn(1);
		skuQuery.setItemId(indexPromItem.getItemId());
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
		indexPromItem.setSkuId(sku.getSkuId());
		return indexPromItemManager.insert(indexPromItem);
	}

	@Override
	public List<IndexPromItem> selectByCondition(IndexPromItemQuery indexPromItemQuery) {
		List<IndexPromItem> indexPromItemList = null;
		try{
			indexPromItemList =indexPromItemManager.selectByCondition(indexPromItemQuery);
			if(indexPromItemList!=null){
				for (IndexPromItem indexPromItem : indexPromItemList) {
					Item item = itemDao.selectByItemId(indexPromItem.getItemId());
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
			        indexPromItem.setItem(item);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return indexPromItemList;
	}

	@Override
	public void modify(IndexPromItem indexPromItem) {
		indexPromItemManager.modify(indexPromItem);
		
	}




}
