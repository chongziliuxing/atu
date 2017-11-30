package com.atu.erp.dao;

import java.util.List;

import com.atu.erp.domain.ServiceCategory;
import com.atu.erp.domain.query.ServiceCategoryQuery;

public interface ServicesDao {

	Integer insert(ServiceCategory category);

	List<ServiceCategory> selectByLikeCondition(
			ServiceCategoryQuery categoryQuery);

	void deleteCategory2ByPar(Integer categoryId);

	void deleteCategory1(Integer categoryId);

	List<ServiceCategory> selectByCondition(ServiceCategoryQuery categoryQuery);

}
