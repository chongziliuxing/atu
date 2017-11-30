package com.atu.erp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atu.erp.common.utils.PaginatedList;
import com.atu.erp.common.utils.impl.PaginatedArrayList;
import com.atu.erp.dao.CpsUserWithdrawCashDao;
import com.atu.erp.domain.CPSUserWithdrawCash;
import com.atu.erp.domain.OrderDetail;
import com.atu.erp.domain.OrderInfo;
import com.atu.erp.domain.query.CPSUserWithdrawCashQuery;
import com.atu.erp.service.CpsUserWithdrawCashService;

@Service(value="CpsUserWithdrawCashService")
public class CpsUserWithdrawCashServiceImpl implements
		CpsUserWithdrawCashService {

	private static Log log = LogFactory.getLog(CpsUserWithdrawCashServiceImpl.class);
	
	
	@Autowired
	private CpsUserWithdrawCashDao cpsUserWithdrawCashDao;

	@Override
	public Map<String, Object> getCPSUserWithdrawCashsByPage(CPSUserWithdrawCashQuery cpsUserWithdrawCashQuery) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		PaginatedList<CPSUserWithdrawCash> cpsUserWithdrawCashList = null;
		
		//创建一个分页的分销用户提现对象
		cpsUserWithdrawCashList = new PaginatedArrayList<CPSUserWithdrawCash>(cpsUserWithdrawCashQuery.pageNo, cpsUserWithdrawCashQuery.pageSize);
		int count = cpsUserWithdrawCashDao.countByCondition(cpsUserWithdrawCashQuery);
		if (count > 0) {
			cpsUserWithdrawCashList.setTotalItem(count);//设总条目数
			int startRow=cpsUserWithdrawCashList.getStartRow(); 
			if (startRow == 0) {
				startRow = 1;
			}
			cpsUserWithdrawCashQuery.setStart(startRow - 1);
			List<CPSUserWithdrawCash> list = cpsUserWithdrawCashDao.selectByConditionForPage(cpsUserWithdrawCashQuery);
			cpsUserWithdrawCashList.addAll(list);
		}
		resultMap.put("cpsUserWithdrawCashList", cpsUserWithdrawCashList);
		resultMap.put("cpsUserWithdrawCashQuery", cpsUserWithdrawCashQuery);
		return resultMap;
	}

}
