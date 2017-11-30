package com.atu.erp.service;

import java.util.List;

import com.atu.erp.domain.Address;
import com.atu.erp.domain.query.AddressQuery;

public interface AddressService {
	public List<Address> selectByCondition(AddressQuery addressQuery);

	/**
	 * 根据一级、二级、三级地址id获得收货人地址信息
	 * @param province
	 * @param city
	 * @param county
	 * @param address
	 * @return
	 */
	public String getDetailConsigneeAddress(int province, int city, int county, String address);
}
