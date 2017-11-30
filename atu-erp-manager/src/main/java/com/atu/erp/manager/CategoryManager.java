package com.atu.erp.manager;

import java.util.List;

import com.atu.erp.domain.Category;
import com.atu.erp.domain.query.CategoryQuery;

public interface CategoryManager {
	public List<Category> selectByCondition(CategoryQuery categoryQuery);
	
	public List<Category> selectByLikeCondition(CategoryQuery categoryQuery);
	
	public Category selectByCategoryId(int categoryId);

	public Integer insert(Category category);

	public void deleteCategory1(Integer categoryId);

	public void deleteCategory2ByPar(Integer parentCategoryId);
	
	public void modify(Category category);
	

}
