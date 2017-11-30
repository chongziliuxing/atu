package com.atu.erp.dao;

import com.atu.erp.domain.UserInfo;
import com.atu.erp.domain.query.UserInfoQuery;

import java.util.List;
import java.util.Map;

public interface UserInfoDAO {

	UserInfo query(Map<String, String> params);

	UserInfo queryById(Integer userId);

	Integer addUser(UserInfo user);

	void updateUser(UserInfo user);

	void addSignSMSCode(Integer mobile);
	
	UserInfo queryByMobile(String mobile);
	
	UserInfo queryUserInfo(UserInfoQuery userInfoQuery);

	int countByCondition(UserInfoQuery userInfoQuery);

	List<UserInfoQuery> selectByConditionWithPage(UserInfoQuery userInfoQuery);
}