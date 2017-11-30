package com.atu.erp.manager.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atu.erp.dao.VenderInfoDAO;
import com.atu.erp.domain.BusinessUserExt;
import com.atu.erp.manager.VenderInfoManager;

@Repository
public class VenderInfoManagerImpl implements VenderInfoManager{
	
	@Autowired
	private VenderInfoDAO venderInfoDAO;

	@Override
	public void addVender(BusinessUserExt venderInfo) {
		venderInfoDAO.addVender(venderInfo);
		
	}
	
	


}
