package com.atu.erp.service;

import java.util.List;

import com.atu.erp.domain.IndexImage;
import com.atu.erp.domain.query.IndexImageQuery;


public interface IndexImageService {
	public Integer insert(IndexImage indexImage);
	
	public List<IndexImage> selectByCondition(IndexImageQuery indexImageQuery);
	
	public void modify(IndexImage indexImage);
}
