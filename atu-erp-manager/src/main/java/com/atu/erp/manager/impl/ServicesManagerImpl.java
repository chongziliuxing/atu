package com.atu.erp.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atu.erp.dao.ServicesDao;
import com.atu.erp.domain.ServiceCategory;
import com.atu.erp.domain.query.ServiceCategoryQuery;
import com.atu.erp.manager.ServicesManager;

@Repository
public class ServicesManagerImpl implements ServicesManager {
	
	@Autowired 
	private ServicesDao servicesDao;

	@Override
	public Integer insert(ServiceCategory category) {
		return servicesDao.insert(category);
		}

	@Override
	public List<ServiceCategory> selectByLikeCondition(
			ServiceCategoryQuery categoryQuery) {
		// TODO Auto-generated method stub
		return servicesDao.selectByLikeCondition(categoryQuery);
	}

	@Override
	public void deleteCategory2ByPar(Integer categoryId) {
		servicesDao.deleteCategory2ByPar(categoryId);
		
		}

	@Override
	public void deleteCategory1(Integer categoryId) {
		servicesDao.deleteCategory1(categoryId);
		
		}

	@Override
	public List<ServiceCategory> selectByCondition(
			ServiceCategoryQuery categoryQuery) {
		
		return servicesDao.selectByCondition(categoryQuery);
	}

}
