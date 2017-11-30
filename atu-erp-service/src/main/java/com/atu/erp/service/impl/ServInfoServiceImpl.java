package com.atu.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atu.erp.domain.ServiceInfo;
import com.atu.erp.domain.query.ServiceInfoQuery;
import com.atu.erp.manager.ServInfoManager;
import com.atu.erp.service.ServInfoService;

@Service(value="servInfoService")
public class ServInfoServiceImpl implements ServInfoService {
		@Autowired
		private ServInfoManager servInfoManager;

		@Override
		public List<ServiceInfo> selectByCondition(
				ServiceInfoQuery serviceinfoquery) {
		    return this.servInfoManager.selectByCondition(serviceinfoquery);
		    }

		@Override
		public Integer insert(ServiceInfo serviceinfo) {
		    return this.servInfoManager.insert(serviceinfo);
		    }

		@Override
		public void deleteById(Integer id) {
		    this.servInfoManager.deleteById(id);
		    }
}
