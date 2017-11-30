package com.atu.erp.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.atu.erp.dao.ServInfoDao;
import com.atu.erp.dao.ServicesDao;
import com.atu.erp.domain.ServiceInfo;
import com.atu.erp.domain.query.ServiceInfoQuery;
public class ServInfoDaoImpl extends SqlMapClientTemplate implements ServInfoDao {


	@Override
	public List<ServiceInfo> selectByCondition(ServiceInfoQuery serviceinfoquery) {
	    return queryForList("ServiceInfo.selectByCondition", serviceinfoquery);
	    }

	@Override
	public Integer insert(ServiceInfo serviceinfo) {
	    return (Integer)insert("ServiceInfo.insert", serviceinfo);
	    }

	@Override
	public void deleteById(Integer serviceId) {
	    delete("ServiceInfo.deleteById", serviceId);
	    }

}
