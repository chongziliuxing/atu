package com.atu.erp.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.atu.erp.dao.OrderConsigneeDao;
import com.atu.erp.domain.OrderConsignee;
import com.atu.erp.domain.query.OrderConsigneeQuery;

public class OrderConsigneeDaoImpl extends SqlMapClientTemplate implements OrderConsigneeDao {
	
	@Override
	public List<OrderConsignee> selectByCondition(OrderConsigneeQuery orderConsigneeQuery) {
		return (List<OrderConsignee>)queryForList("OrderConsignee.selectByCondition", orderConsigneeQuery);
	}

	@Override
	public void modify(OrderConsignee orderConsignee) {
		update("OrderConsignee.updateByPrimaryKey", orderConsignee);
	}
}
