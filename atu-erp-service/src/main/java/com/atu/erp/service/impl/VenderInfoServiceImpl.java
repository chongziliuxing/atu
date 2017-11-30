package com.atu.erp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.atu.erp.domain.BusinessUserExt;
import com.atu.erp.domain.UserInfo;
import com.atu.erp.manager.UserManager;
import com.atu.erp.manager.VenderInfoManager;
import com.atu.erp.service.UserService;
import com.atu.erp.service.VenderInfoService;

@Service(value = "venderInfoService")
public class VenderInfoServiceImpl implements VenderInfoService{
	@Autowired
	private VenderInfoManager venderInfoManager;

	@Override
	public void addVender(BusinessUserExt venderInfo) {
		// TODO Auto-generated method stub
		venderInfoManager.addVender(venderInfo);
		
	}



}
