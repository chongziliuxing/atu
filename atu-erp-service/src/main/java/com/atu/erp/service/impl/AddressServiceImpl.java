package com.atu.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atu.erp.domain.Address;
import com.atu.erp.domain.query.AddressQuery;
import com.atu.erp.manager.AddressManager;
import com.atu.erp.service.AddressService;


@Service(value = "addressService")
public class AddressServiceImpl implements AddressService{
	@Autowired
	private AddressManager addressManager;

	@Override
	public List<Address> selectByCondition(AddressQuery addressQuery) {
		return addressManager.selectByCondition(addressQuery);
	}

	@Override
	public String getDetailConsigneeAddress(int province, int city, int county,
			String address) {
		AddressQuery query1 = new AddressQuery();
		query1.setAddressLevel(1);
		query1.setAddressId(province);
		
		List<Address> list1 = addressManager.selectByCondition(query1);
		if(list1 == null || list1.size() == 0){
			return null;
		}
		
		AddressQuery query2 = new AddressQuery();
		query2.setAddressLevel(2);
		query2.setAddressId(city);
		
		List<Address> list2 = addressManager.selectByCondition(query2);
		if(list2 == null || list2.size() == 0){
			return null;
		}
		
		AddressQuery query3 = new AddressQuery();
		query3.setAddressLevel(3);
		query3.setAddressId(county);
		
		List<Address> list3 = addressManager.selectByCondition(query3);
		if(list3 == null || list3.size() == 0){
			return null;
		}
		
		return list1.get(0).getAddressName() + list2.get(0).getAddressName() + list3.get(0).getAddressName() + address;
	}


}
