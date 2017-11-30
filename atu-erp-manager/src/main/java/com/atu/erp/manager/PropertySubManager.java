package com.atu.erp.manager;

import java.util.List;

import com.atu.erp.domain.PropertySub;
import com.atu.erp.domain.query.PropertySubQuery;

public interface PropertySubManager {

	public Integer insert(PropertySub propertySub);

	public void modify(PropertySub propertySub);
	
	public List<PropertySub> selectByCondition(PropertySubQuery propertySubQuery);
}
