package com.atu.erp.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.atu.erp.dao.PropertySubDao;
import com.atu.erp.domain.PropertySub;
import com.atu.erp.domain.query.PropertySubQuery;

public class PropertySubDaoImpl extends SqlMapClientTemplate implements PropertySubDao {

	@Override
	public Integer insert(PropertySub propertySub) {
		return (Integer)insert("PropertySub.insert",propertySub);
	}

	@Override
	public void modify(PropertySub propertySub) {
		update("PropertySub.updateByPrimaryKey",propertySub);
	}
	
	@Override
	public List<PropertySub> selectByCondition(PropertySubQuery propertySubQuery) {
		return (List<PropertySub>)queryForList("PropertySub.selectByConditionPropertyId", propertySubQuery);
	}
}
