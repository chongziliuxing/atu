package com.atu.erp.dao;

import java.util.List;

import com.atu.erp.domain.ServiceInfo;
import com.atu.erp.domain.query.ServiceInfoQuery;

public interface ServInfoDao {

	List<ServiceInfo> selectByCondition(ServiceInfoQuery serviceinfoquery);

	Integer insert(ServiceInfo serviceinfo);

	void deleteById(Integer id);

}
