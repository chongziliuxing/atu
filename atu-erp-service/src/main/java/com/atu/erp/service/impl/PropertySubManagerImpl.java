package com.atu.erp.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atu.erp.dao.PropertySubDao;
import com.atu.erp.domain.PropertySub;
import com.atu.erp.domain.query.PropertySubQuery;
import com.atu.erp.manager.PropertySubManager;
import com.atu.erp.manager.impl.BrandManagerImpl;

@Repository
public class PropertySubManagerImpl implements PropertySubManager {

	@Autowired
	private PropertySubDao propertySubDao;
	
	private final static Log LOG = LogFactory.getLog(BrandManagerImpl.class);
	
	public Integer insert(PropertySub propertySub) {
		return propertySubDao.insert(propertySub);
	}

	public void modify(PropertySub propertySub) {
		propertySubDao.modify(propertySub);
	}
	
	public List<PropertySub> selectByCondition(PropertySubQuery propertySubQuery) {
		return propertySubDao.selectByCondition(propertySubQuery);
	}
}
