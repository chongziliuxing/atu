package com.atu.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atu.erp.domain.PropertySub;
import com.atu.erp.domain.query.PropertySubQuery;
import com.atu.erp.manager.PropertySubManager;
import com.atu.erp.service.PropertySubService;

@Service(value = "propertySubService")
public class PropertySubServiceImpl implements PropertySubService {

	@Autowired
	private PropertySubManager propertySubManager;
	
	public Integer insert(PropertySub propertySub) {
		return propertySubManager.insert(propertySub);
	}

	public void modify(PropertySub propertySub) {
		propertySubManager.modify(propertySub);
	}
	
	public List<PropertySub> selectByCondition(PropertySubQuery propertySubQuery) {
		return propertySubManager.selectByCondition(propertySubQuery);
	}
}
