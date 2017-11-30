package com.atu.erp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atu.erp.common.utils.PaginatedList;
import com.atu.erp.common.utils.impl.PaginatedArrayList;
import com.atu.erp.domain.UserInfo;
import com.atu.erp.domain.query.UserInfoQuery;
import com.atu.erp.manager.BusinessUserExtManager;
import com.atu.erp.manager.UserManager;
import com.atu.erp.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserManager userManager;
    @Autowired
    private BusinessUserExtManager businessUserExtManager;

	@Override
	public UserInfo queryUser(String loginname, String loginpwd) {
		Map<String,String> params=new HashMap<String,String>();
		params.put("mobile", loginname);
		params.put("password", loginpwd);
		UserInfo userInfo = userManager.query(params);
		return userInfo;
	}

	@Override
	public Integer addUser(UserInfo user) {
		// TODO Auto-generated method stub
		return userManager.addUser(user);
		
	}

	@Override
	public void updateUser(UserInfo user) {
		
		userManager.updateUser(user);
		
	}

	@Override
	public Map<String, Object> queryUserList(UserInfoQuery userInfoQuery) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		PaginatedList<UserInfoQuery> userList =null;
		//创建一个分页的促销对象
		userList = new PaginatedArrayList<UserInfoQuery>(userInfoQuery.getPageNo(),userInfoQuery.getPageSize());
		int count = userManager.countByCondition(userInfoQuery);
		userList.setTotalItem(count);
		int startRow=userList.getStartRow();
		if (startRow == 0) {
			startRow = 1;
		}
		userInfoQuery.setStart(startRow-1);
		List<UserInfoQuery> list= userManager.selectByConditionWithPage(userInfoQuery);
		if( null != list ){
            for( UserInfoQuery uiq : list ){
                uiq.setBusinessUserExt(businessUserExtManager.selectByUserId(uiq.getUserId()));
            }
        }
        userList.addAll(list);
		resultMap.put("userList", userList);
		resultMap.put("userInfoQuery", userInfoQuery);	
		return resultMap;
	}
	
	@Override
	public UserInfo queryById(Integer userId) {
		return userManager.queryById(userId);
	}
	
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public void setBusinessUserExtManager(BusinessUserExtManager businessUserExtManager) {
        this.businessUserExtManager = businessUserExtManager;
    }
}
