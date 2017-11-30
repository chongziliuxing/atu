package com.atu.erp.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.atu.erp.dao.OrderInfoDao;
import com.atu.erp.domain.OrderInfo;
import com.atu.erp.domain.query.OrderInfoQuery;

public class OrderInfoDaoImpl extends SqlMapClientTemplate implements OrderInfoDao {

	@Override
	public Integer insert(OrderInfo orderInfo) {
		return (Integer)insert("OrderInfo.insert", orderInfo);
	}

	@Override
	public int modify(OrderInfo orderInfo) {
		return update("OrderInfo.updateByPrimaryKey", orderInfo);
	}

	@Override
	public OrderInfo selectByOrderId(int orderId) {
		return (OrderInfo)queryForObject("OrderInfo.selectByPrimaryKey",orderId);
	}

	@Override
	public int countByCondition(OrderInfoQuery orderInfoQuery) {
		return (Integer)queryForObject("OrderInfo.countByCondition",orderInfoQuery);
	}

	@Override
	public List<OrderInfo> selectByCondition(OrderInfoQuery orderInfoQuery) {
		return (List<OrderInfo>)queryForList("OrderInfo.selectByCondition",orderInfoQuery);
	}

	@Override
	public List<OrderInfo> selectByConditionForPage(
			OrderInfoQuery orderInfoQuery) {
		return (List<OrderInfo>)queryForList("OrderInfo.selectByConditionForPage",orderInfoQuery);
	}

	@Override
	public int modifyPayMoney(OrderInfo orderInfo) {
		return update("OrderInfo.modifyPayMoney", orderInfo);
	}

	@Override
	public Integer selectOrderConsumer(OrderInfoQuery orderInfoQuery) {
		return (Integer)queryForObject("OrderInfo.selectOrderConsumer",orderInfoQuery);
	}
}
