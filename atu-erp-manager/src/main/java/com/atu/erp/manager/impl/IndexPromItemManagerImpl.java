package com.atu.erp.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atu.erp.dao.IndexPromItemDao;
import com.atu.erp.domain.IndexPromItem;
import com.atu.erp.domain.query.IndexPromItemQuery;
import com.atu.erp.manager.IndexPromItemManager;


@Repository
public class IndexPromItemManagerImpl implements IndexPromItemManager{
	
	@Autowired
	private IndexPromItemDao indexPromItemDao;
	private final static Log LOG = LogFactory.getLog(IndexPromItemManagerImpl.class);
	@Override
	public Integer insert(IndexPromItem indexPromItem) {

		return indexPromItemDao.insert(indexPromItem);
	}
	@Override
	public List<IndexPromItem> selectByCondition(IndexPromItemQuery indexPromItemQuery) {
		// TODO Auto-generated method stub
		return indexPromItemDao.selectByCondition(indexPromItemQuery);
	}
	@Override
	public void modify(IndexPromItem indexPromItem) {
		indexPromItemDao.modify(indexPromItem);
		
	}

	

}
