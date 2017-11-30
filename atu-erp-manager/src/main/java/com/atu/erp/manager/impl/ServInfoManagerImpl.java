package com.atu.erp.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atu.erp.dao.ServInfoDao;
import com.atu.erp.domain.ServiceInfo;
import com.atu.erp.domain.query.ServiceInfoQuery;
import com.atu.erp.manager.ServInfoManager;

@Repository
public class ServInfoManagerImpl implements ServInfoManager {
	
	@Autowired
	private ServInfoDao servInfoDao;

	@Override
	public List<ServiceInfo> selectByCondition(ServiceInfoQuery serviceinfoquery) {
	    return this.servInfoDao.selectByCondition(serviceinfoquery);
	    }

	@Override
	public Integer insert(ServiceInfo serviceinfo) {
	    return this.servInfoDao.insert(serviceinfo);
	    }

	@Override
	public void deleteById(Integer id) {
	    this.servInfoDao.deleteById(id);
	    }

}
