package com.atu.erp.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atu.erp.dao.IndexSellItemDao;
import com.atu.erp.domain.IndexSellItem;
import com.atu.erp.domain.query.IndexSellItemQuery;
import com.atu.erp.manager.IndexSellItemManager;


@Repository
public class IndexSellItemManagerImpl implements IndexSellItemManager{
	
	@Autowired
	private IndexSellItemDao indexSellItemDao;
	private final static Log LOG = LogFactory.getLog(IndexSellItemManagerImpl.class);
	@Override
	public Integer insert(IndexSellItem indexSellItem) {

		return indexSellItemDao.insert(indexSellItem);
	}
	@Override
	public List<IndexSellItem> selectByCondition(IndexSellItemQuery indexSellItemQuery) {
		// TODO Auto-generated method stub
		return indexSellItemDao.selectByCondition(indexSellItemQuery);
	}
	@Override
	public void modify(IndexSellItem indexSellItem) {
		indexSellItemDao.modify(indexSellItem);
		
	}

	

}
