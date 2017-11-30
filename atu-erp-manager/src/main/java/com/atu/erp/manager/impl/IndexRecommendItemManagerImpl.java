package com.atu.erp.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atu.erp.dao.IndexRecommendItemDao;
import com.atu.erp.domain.IndexRecommendItem;
import com.atu.erp.domain.query.IndexRecommendItemQuery;
import com.atu.erp.manager.IndexRecommendItemManager;


@Repository
public class IndexRecommendItemManagerImpl implements IndexRecommendItemManager{
	
	@Autowired
	private IndexRecommendItemDao indexRecommendItemDao;
	
	private final static Log LOG = LogFactory.getLog(IndexRecommendItemManagerImpl.class);
	@Override
	public Integer insert(IndexRecommendItem indexRecommendItem) {

		return indexRecommendItemDao.insert(indexRecommendItem);
	}
	@Override
	public List<IndexRecommendItem> selectByCondition(IndexRecommendItemQuery indexRecommendItemQuery) {
		// TODO Auto-generated method stub
		return indexRecommendItemDao.selectByCondition(indexRecommendItemQuery);
	}
	@Override
	public void modify(IndexRecommendItem indexRecommendItem) {
		indexRecommendItemDao.modify(indexRecommendItem);
		
	}

	

}
