package com.atu.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atu.erp.domain.IndexImage;
import com.atu.erp.domain.query.IndexImageQuery;
import com.atu.erp.manager.IndexImageManager;
import com.atu.erp.service.IndexImageService;


@Service(value = "indexImageService")
public class IndexImageServiceImpl implements IndexImageService{
	@Autowired
	private IndexImageManager indexImageManager;

	@Override
	public Integer insert(IndexImage indexImage) {
		// TODO Auto-generated method stub
		return indexImageManager.insert(indexImage);
	}

	@Override
	public List<IndexImage> selectByCondition(IndexImageQuery indexImageQuery) {
		// TODO Auto-generated method stub
		return indexImageManager.selectByCondition(indexImageQuery);
	}

	@Override
	public void modify(IndexImage indexImage) {
		indexImageManager.modify(indexImage);
		
	}




}
