package com.atu.erp.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.atu.erp.dao.BrandDao;
import com.atu.erp.domain.Brand;
import com.atu.erp.domain.query.BrandQuery;

public class BrandDaoImpl extends SqlMapClientTemplate implements BrandDao {

	@Override
	public Integer insert(Brand brand) {
		return (Integer)insert("Brand.insert",brand);
	}

	@Override
	public void modify(Brand brand) {
		update("Brand.updateByPrimaryKey",brand);
	}
	
	@Override
	public List<Brand> selectByCondition(BrandQuery brandQuery) {
		return (List<Brand>)queryForList("Brand.selectByCondition", brandQuery);
	}
	
	@Override
	public List<Brand> selectByConditionForPage(BrandQuery brandQuery) {
		return (List<Brand>)queryForList("Brand.selectByConditionForPage", brandQuery);
	}

	@Override
	public void delete(Integer brandId) {
		delete("Brand.delete",brandId); 
	}
}
