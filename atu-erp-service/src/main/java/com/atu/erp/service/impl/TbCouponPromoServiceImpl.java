package com.atu.erp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atu.erp.common.utils.PaginatedList;
import com.atu.erp.common.utils.impl.PaginatedArrayList;
import com.atu.erp.dao.TbCouponPromoDao;
import com.atu.erp.domain.TbCouponPromo;
import com.atu.erp.domain.TbCouponPromo;
import com.atu.erp.domain.query.TbCouponPromoQuery;
import com.atu.erp.service.TbCouponPromoService;

@Service(value="tbCouponPromoService")
public class TbCouponPromoServiceImpl implements TbCouponPromoService{

	@Autowired
	private TbCouponPromoDao tbCouponPromoDao;
	
	@Override
	public Integer insert(TbCouponPromo tbCouponPromo) {
		return tbCouponPromoDao.insert(tbCouponPromo);
	}

	@Override
	public void modify(TbCouponPromo tbCouponPromo) {
		tbCouponPromoDao.modify(tbCouponPromo);
	}

	@Override
	public TbCouponPromo selectByCouponPromoId(int couponPromoId) {
		return tbCouponPromoDao.selectByCouponPromoId(couponPromoId);
	}

	@Override
	public int countByCondition(TbCouponPromoQuery tbCouponPromoQuery) {
		return 0;
	}

	@Override
	public List<TbCouponPromo> selectByCondition(TbCouponPromoQuery tbCouponPromoQuery) {
		return null;
	}


	@Override
	public Map<String, Object> getTbCouponPromoByPage(TbCouponPromoQuery tbCouponPromoQuery) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		PaginatedList<TbCouponPromo> tbCouponPromoList = null;
		//创建一个分页的优惠券管理对象
		tbCouponPromoList = new PaginatedArrayList<TbCouponPromo>(tbCouponPromoQuery.pageNo, tbCouponPromoQuery.pageSize);
		int count = tbCouponPromoDao.countByCondition(tbCouponPromoQuery);
		if (count > 0) {
			tbCouponPromoList.setTotalItem(count);//设总条目数
			int startRow=tbCouponPromoList.getStartRow(); 
			if (startRow == 0) {
				startRow = 1;
			}
			tbCouponPromoQuery.setStart(startRow - 1);        
			List<TbCouponPromo> list = tbCouponPromoDao.selectByConditionForPage(tbCouponPromoQuery);
			tbCouponPromoList.addAll(list);
		}
		resultMap.put("tbCouponPromoList", tbCouponPromoList);
		resultMap.put("tbCouponPromoQuery", tbCouponPromoQuery);
		return resultMap;
	}
}
