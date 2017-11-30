package com.atu.erp.service;

import java.util.Map;

import com.atu.erp.domain.UserInfo;
import com.atu.erp.domain.query.UserInfoQuery;

public interface UserService {

	UserInfo queryUser(String loginname, String loginpwd);

	Integer addUser(UserInfo user);

	void updateUser(UserInfo user);

	Map<String, Object> queryUserList(UserInfoQuery userInfoQuery);
	
	UserInfo queryById(Integer userId);

}
