package com.atu.erp.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.atu.erp.dao.TbCouponDao;
import com.atu.erp.domain.TbCoupon;
import com.atu.erp.domain.query.TbCouponQuery;

public class TbCouponDaoImpl extends SqlMapClientTemplate implements TbCouponDao {

	@Override
	public Integer insert(TbCoupon tbCoupon) {
		return (Integer)insert("TbCoupon.insert",tbCoupon);
	}

	@Override
	public void modify(TbCoupon tbCoupon) {
		update("TbCoupon.updateByPrimaryKey",tbCoupon);
	}

	@Override
	public TbCoupon selectByTbCouponId(int couponId) {
		//return (TbCoupon)queryForObject("TbCoupon.selectById",couponId);
		return null;
	}

	@Override
	public int countByCondition(TbCouponQuery tbCouponQuery) {
		return (Integer)queryForObject("TbCoupon.countByTbCouponCondition",tbCouponQuery);
	}

	@Override
	public List<TbCoupon> selectByCondition(TbCouponQuery tbCouponQuery) {
		return (List<TbCoupon>)queryForList("TbCoupon.selectByTbCouponCondition",tbCouponQuery);
	}

	@Override
	public List<TbCoupon> selectByConditionForPage(TbCouponQuery tbCouponQuery) {
		return (List<TbCoupon>)queryForList("TbCoupon.selectByTbCouponConditionForPage",tbCouponQuery);
	}

}
