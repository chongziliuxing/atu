package com.atu.erp.dao.impl;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.atu.erp.dao.UserDao;
import com.atu.erp.domain.User;

public class UserDaoImpl extends SqlMapClientTemplate implements UserDao {

	@Override
	public User getUserByUsername(String username) {
		return (User)super.queryForObject("User.getUserByUsername", username);
	}
}
