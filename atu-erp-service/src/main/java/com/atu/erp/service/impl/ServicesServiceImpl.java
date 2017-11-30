package com.atu.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atu.erp.domain.ServiceCategory;
import com.atu.erp.domain.query.ServiceCategoryQuery;
import com.atu.erp.manager.ServicesManager;
import com.atu.erp.service.ServicesService;

@Service(value="servicesService")
public class ServicesServiceImpl implements ServicesService {
	@Autowired
	private ServicesManager servicesManager;

	@Override
	public Integer insert(ServiceCategory category) {		
		return servicesManager.insert(category);
		}

	@Override
	public List<ServiceCategory> selectByCondition(
			ServiceCategoryQuery categoryQuery) {
		return servicesManager.selectByCondition(categoryQuery);
	}

	@Override
	public void deleteCategory1(Integer categoryId) {
		servicesManager.deleteCategory1(categoryId);
		
		}

	@Override
	public void deleteCategory2ByPar(Integer categoryId) {
		servicesManager.deleteCategory2ByPar(categoryId);
	}

	@Override
	public void modify(ServiceCategory category) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ServiceCategory> selectByLikeCondition(
			ServiceCategoryQuery categoryQuery) {
		return servicesManager.selectByLikeCondition(categoryQuery);
	}
}
