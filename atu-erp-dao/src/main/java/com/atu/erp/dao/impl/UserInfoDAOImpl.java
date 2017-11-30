package com.atu.erp.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.atu.erp.dao.UserInfoDAO;
import com.atu.erp.domain.UserInfo;
import com.atu.erp.domain.query.UserInfoQuery;

public class UserInfoDAOImpl extends SqlMapClientTemplate implements UserInfoDAO {

	@Override
	public UserInfo query(Map<String, String> params) {
		UserInfo userInfo=null;
		userInfo = (UserInfo) super.queryForObject("user_info.query", params);
        return userInfo;		
	}
	
	@Override
	public UserInfo queryById(Integer userId) {
        return (UserInfo) queryForObject("user_info.queryById", userId);		
	}

	@Override
	public Integer addUser(UserInfo user) {
		return (Integer)super.insert("user_info.addUser", user);
	}

	@Override
	public void updateUser(UserInfo user) {
		super.update("user_info.updateUser", user);
	}

	@Override
	public UserInfo queryByMobile(String mobile) {
		return (UserInfo) super.queryForObject("user_info.queryByMobile", mobile);
	}

	@Override
	public void addSignSMSCode(Integer mobile) {
		
	}

	@Override
	public UserInfo queryUserInfo(UserInfoQuery userInfoQuery) {
		return (UserInfo) super.queryForObject("user_info.queryUserInfo", userInfoQuery);
	}

	@Override
	public int countByCondition(UserInfoQuery userInfoQuery) {
		return (Integer)queryForObject("user_info.countByCondition",userInfoQuery);
	}

	@Override
	public List<UserInfoQuery> selectByConditionWithPage(UserInfoQuery userInfoQuery) {
		return (List<UserInfoQuery>)queryForList("user_info.selectByConditionWithPage",userInfoQuery);
	}

	
}