package com.atu.erp.service;

import java.util.List;

import com.atu.erp.domain.PropertyValue;
import com.atu.erp.domain.query.PropertyValueQuery;

public interface PropertyValueService {
	public List<PropertyValue> selectByCondition(PropertyValueQuery propertyValueQuery);
	
	public Integer insert(PropertyValue propertyValue);
	
	public void deleteById(Integer propertyValueId);

}
