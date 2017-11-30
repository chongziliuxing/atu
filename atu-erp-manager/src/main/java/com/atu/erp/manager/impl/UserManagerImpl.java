package com.atu.erp.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atu.erp.dao.UserInfoDAO;
import com.atu.erp.domain.UserInfo;
import com.atu.erp.domain.query.UserInfoQuery;
import com.atu.erp.manager.UserManager;

@Repository
public class UserManagerImpl implements UserManager{
	
	@Autowired
	private UserInfoDAO userInfoDAO;

	@Override
	public UserInfo query(Map<String, String> params) {
		// TODO Auto-generated method stub
		UserInfo userInfo=userInfoDAO.query(params);
		return userInfo;
	}
	
	@Override
	public UserInfo queryById(Integer userId) {
		return userInfoDAO.queryById(userId);
	}

	public UserInfoDAO getUserInfoDAO() {
		return userInfoDAO;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}

	@Override
	public Integer addUser(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDAO.addUser(user);
		
	}

	@Override
	public void updateUser(UserInfo user) {
		userInfoDAO.updateUser(user);
		
	}

	@Override
	public void addSignSMSCode(Integer mobile) {
		
		userInfoDAO.addSignSMSCode(mobile);
	}

	@Override
	public int countByCondition(UserInfoQuery userInfoQuery) {
		// TODO Auto-generated method stub
		return userInfoDAO.countByCondition(userInfoQuery);
	}

	@Override
	public List<UserInfoQuery> selectByConditionWithPage(
			UserInfoQuery userInfoQuery) {
		
		return userInfoDAO.selectByConditionWithPage(userInfoQuery);
	}

	
}
