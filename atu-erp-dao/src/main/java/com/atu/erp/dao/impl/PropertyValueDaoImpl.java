package com.atu.erp.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.atu.erp.dao.PropertyValueDao;
import com.atu.erp.domain.PropertyValue;
import com.atu.erp.domain.query.PropertyValueQuery;

public class PropertyValueDaoImpl extends SqlMapClientTemplate implements PropertyValueDao {

	@Override
	public Integer insert(PropertyValue propertyValue) {
		return (Integer)insert("PropertyValue.insert",propertyValue);
	}

	@Override
	public void modify(PropertyValue propertyValue) {
		update("PropertyValue.updateByPrimaryKey",propertyValue);
	}

	@Override
	public PropertyValue selectByPropertyValueId(int propertyValueId) {
		return (PropertyValue)queryForObject("PropertyValue.selectByPrimaryKey",propertyValueId);
	}

	@Override
	public int countByCondition(PropertyValueQuery propertyValueQuery) {
		return (Integer)queryForObject("PropertyValue.countByCondition",propertyValueQuery);
	}

	@Override
	public List<PropertyValue> selectByCondition(
			PropertyValueQuery propertyValueQuery) {
		return (List<PropertyValue>)queryForList("PropertyValue.selectByCondition",propertyValueQuery);
	}

	@Override
	public List<PropertyValue> selectByConditionForPage(
			PropertyValueQuery propertyValueQuery) {
		return (List<PropertyValue>)queryForList("PropertyValue.selectByConditionForPage",propertyValueQuery);
	}

	@Override
	public void deleteById(Integer propertyValueId) {
		delete("PropertyValue.deleteById", propertyValueId);
	}

}
