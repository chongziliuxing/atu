package com.atu.erp.dao;

import com.atu.erp.domain.User;

public interface UserDao {
	User getUserByUsername(String username);
	
	
}
