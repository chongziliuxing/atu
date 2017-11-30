package com.atu.erp.dao.impl;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.atu.erp.dao.ItemPropertyDao;
import com.atu.erp.domain.ItemProperty;

public class ItemPropertyDaoImpl extends SqlMapClientTemplate implements ItemPropertyDao {

	@Override
	public Integer insert(ItemProperty itemProperty) {
		return (Integer)insert("ItemProperty.insert",itemProperty);
	}

	@Override
	public void modify(ItemProperty itemProperty) {
		update("ItemProperty.updateByItemId",itemProperty);
	}

	@Override
	public ItemProperty selectByItemId(int itemId) {
		return (ItemProperty)queryForObject("ItemProperty.selectByItemId",itemId);
	}

}
