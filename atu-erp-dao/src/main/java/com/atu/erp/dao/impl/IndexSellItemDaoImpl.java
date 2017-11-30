package com.atu.erp.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.atu.erp.dao.IndexSellItemDao;
import com.atu.erp.domain.IndexSellItem;
import com.atu.erp.domain.query.IndexSellItemQuery;

public class IndexSellItemDaoImpl extends SqlMapClientTemplate implements IndexSellItemDao {

	@Override
	public Integer insert(IndexSellItem indexSellItem) {
		return (Integer)insert("IndexSellItem.insert",indexSellItem);
	}

	@Override
	public void modify(IndexSellItem indexSellItem) {
		update("IndexSellItem.updateByPrimaryKey",indexSellItem);
	}

	@Override
	public int countByCondition(IndexSellItemQuery indexSellItemQuery) {
		return (Integer)queryForObject("IndexSellItem.countByCondition",indexSellItemQuery);
	}

	@Override
	public List<IndexSellItem> selectByCondition(IndexSellItemQuery indexSellItemQuery) {
		return (List<IndexSellItem>)queryForList("IndexSellItem.selectByCondition",indexSellItemQuery);
	}

	@Override
	public List<IndexSellItem> selectByConditionForPage(IndexSellItemQuery indexSellItemQuery) {
		return (List<IndexSellItem>)queryForList("IndexSellItem.selectByConditionForPage",indexSellItemQuery);
	}

}
