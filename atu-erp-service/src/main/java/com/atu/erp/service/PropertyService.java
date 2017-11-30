package com.atu.erp.service;

import java.util.List;

import com.atu.erp.domain.Property;
import com.atu.erp.domain.query.PropertyQuery;

public interface PropertyService {
	public List<Property> selectByCondition(PropertyQuery propertyQuery);
	
	/**
	 * 依据类目属性ID查询类目属性信息
	 * @param propertyId
	 * @return
	 */
	public Property selectByPropertyId(int propertyId);
	
	public Integer insert(Property property);

}
