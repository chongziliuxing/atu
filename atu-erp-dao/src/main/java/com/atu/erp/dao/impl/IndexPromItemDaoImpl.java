package com.atu.erp.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.atu.erp.dao.IndexPromItemDao;
import com.atu.erp.domain.IndexPromItem;
import com.atu.erp.domain.query.IndexPromItemQuery;

public class IndexPromItemDaoImpl extends SqlMapClientTemplate implements IndexPromItemDao {

	@Override
	public Integer insert(IndexPromItem indexPromItem) {
		return (Integer)insert("IndexPromItem.insert",indexPromItem);
	}

	@Override
	public void modify(IndexPromItem indexPromItem) {
		update("IndexPromItem.updateByPrimaryKey",indexPromItem);
	}

	@Override
	public int countByCondition(IndexPromItemQuery indexPromItemQuery) {
		return (Integer)queryForObject("IndexPromItem.countByCondition",indexPromItemQuery);
	}

	@Override
	public List<IndexPromItem> selectByCondition(IndexPromItemQuery indexPromItemQuery) {
		return (List<IndexPromItem>)queryForList("IndexPromItem.selectByCondition",indexPromItemQuery);
	}

	@Override
	public List<IndexPromItem> selectByConditionForPage(IndexPromItemQuery indexPromItemQuery) {
		return (List<IndexPromItem>)queryForList("IndexPromItem.selectByConditionForPage",indexPromItemQuery);
	}

}
