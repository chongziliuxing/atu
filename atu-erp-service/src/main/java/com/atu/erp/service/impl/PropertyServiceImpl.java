package com.atu.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atu.erp.domain.Property;
import com.atu.erp.domain.query.PropertyQuery;
import com.atu.erp.manager.PropertyManager;
import com.atu.erp.service.PropertyService;


@Service(value = "propertyService")
public class PropertyServiceImpl implements PropertyService{
	@Autowired
	private PropertyManager propertyManager;

	@Override
	public List<Property> selectByCondition(PropertyQuery propertyQuery) {
		return propertyManager.selectByCondition(propertyQuery);
	}

	@Override
	public Property selectByPropertyId(int propertyId) {
		
		return propertyManager.selectByPropertyId(propertyId);
	}

	public Integer insert(Property property) {
		return propertyManager.insert(property);
	}


}
