package com.atu.erp.service;

import java.util.List;

import com.atu.erp.domain.ServiceCategory;
import com.atu.erp.domain.query.ServiceCategoryQuery;

public interface ServicesService {

	Integer insert(ServiceCategory category);

	List<ServiceCategory> selectByCondition(ServiceCategoryQuery categoryQuery);

	void deleteCategory1(Integer categoryId);

	void deleteCategory2ByPar(Integer categoryId);

	void modify(ServiceCategory category);

	List<ServiceCategory> selectByLikeCondition(
			ServiceCategoryQuery categoryQuery);

}
