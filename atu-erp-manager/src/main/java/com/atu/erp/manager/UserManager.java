package com.atu.erp.manager;

import java.util.List;
import java.util.Map;

import com.atu.erp.domain.UserInfo;
import com.atu.erp.domain.query.UserInfoQuery;

public interface UserManager {

	UserInfo query(Map<String, String> params);
	
	UserInfo queryById(Integer userId);

	Integer addUser(UserInfo user);

	void updateUser(UserInfo user);

	void addSignSMSCode(Integer mobile);

	int countByCondition(UserInfoQuery userInfoQuery);

	List<UserInfoQuery> selectByConditionWithPage(UserInfoQuery userInfoQuery);

}
