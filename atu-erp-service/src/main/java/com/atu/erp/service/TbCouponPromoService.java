package com.atu.erp.service;

import java.util.List;
import java.util.Map;

import com.atu.erp.domain.TbCouponPromo;
import com.atu.erp.domain.query.CPSUserWithdrawCashQuery;
import com.atu.erp.domain.query.TbCouponPromoQuery;

public interface TbCouponPromoService {
	
	/**
	 * 添加促销优惠券
	 * @param tbCouponPromo
	 * @return
	 */
	public Integer insert(TbCouponPromo tbCouponPromo);

	/**
	 * 依据促销优惠券ID修改促销优惠券
	 * @param tbCouponPromo
	 */
	public void modify(TbCouponPromo tbCouponPromo);

	/**
	 * 依据促销优惠券ID查询促销优惠券
	 * @param consigneeId
	 * @return
	 */
	public TbCouponPromo selectByCouponPromoId(int couponPromoId);
	
	/**
	 * 根据相应的条件查询满足条件的促销优惠券的总数
	 * @param tbCouponPromoQuery
	 * @return
	 */
	public int countByCondition(TbCouponPromoQuery tbCouponPromoQuery);
	
	/**
	 * 根据相应的条件查询促销优惠券
	 * @param tbCouponPromoQuery
	 * @return
	 */
	public List<TbCouponPromo> selectByCondition(TbCouponPromoQuery tbCouponPromoQuery); 
	
	
	/**
	 * 分页查询
	 * @param tbCouponPromoQuery
	 * @return
	 */
	public Map<String, Object> getTbCouponPromoByPage(TbCouponPromoQuery tbCouponPromoQuery);
}
