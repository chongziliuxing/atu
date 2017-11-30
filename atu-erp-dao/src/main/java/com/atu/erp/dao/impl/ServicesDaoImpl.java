package com.atu.erp.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.atu.erp.dao.ServicesDao;
import com.atu.erp.domain.Category;
import com.atu.erp.domain.ServiceCategory;
import com.atu.erp.domain.query.ServiceCategoryQuery;

public class ServicesDaoImpl extends SqlMapClientTemplate implements
		ServicesDao {

	@Override
	public Integer insert(ServiceCategory category) {
		return (Integer)insert("ServiceCategory.insert",category);
		}

	@Override
	public List<ServiceCategory> selectByLikeCondition(
			ServiceCategoryQuery categoryQuery) {
		
		return (List<ServiceCategory>)queryForList("ServiceCategory.selectByLikeCondition", categoryQuery);
	}

	@Override
	public void deleteCategory2ByPar(Integer categoryId) {
		delete("ServiceCategory.deleteCategory2ByPar",categoryId);
		
	}

	@Override
	public void deleteCategory1(Integer categoryId) {
		delete("ServiceCategory.deleteCategory1",categoryId);
		
		}

	@Override
	public List<ServiceCategory> selectByCondition(
			ServiceCategoryQuery categoryQuery) {
		return (List<ServiceCategory>)queryForList("ServiceCategory.selectByCondition", categoryQuery);
		}

}
