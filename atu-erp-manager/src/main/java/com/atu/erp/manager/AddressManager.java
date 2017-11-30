package com.atu.erp.manager;

import java.util.List;

import com.atu.erp.domain.Address;
import com.atu.erp.domain.query.AddressQuery;

public interface AddressManager {
	public List<Address> selectByCondition(AddressQuery addressQuery);

}
