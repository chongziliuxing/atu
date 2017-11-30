package com.atu.erp.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atu.erp.dao.AddressDao;
import com.atu.erp.domain.Address;
import com.atu.erp.domain.query.AddressQuery;
import com.atu.erp.manager.AddressManager;


@Repository
public class AddressManagerImpl implements AddressManager{
	
	@Autowired
	private AddressDao addressDao;
	private final static Log LOG = LogFactory.getLog(AddressManagerImpl.class);
	@Override
	public List<Address> selectByCondition(AddressQuery addressQuery) {
		
		return addressDao.selectByCondition(addressQuery);
	}
	

}
