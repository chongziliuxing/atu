package com.atu.erp.dao;
import java.util.List;

import com.atu.erp.domain.BusinessUserExt;
import com.atu.erp.domain.query.BusinessUserExtQuery;

public interface BusinessUserExtDao{
	/**
	 * 添加商家扩展信息
	 * @param businessUserExt
	 * @return
	 */
	public Integer insert(BusinessUserExt businessUserExt);

	/**
	 * 依据用户ID修改商家扩展信息
	 * @param businessUserExt
	 */
	public void modifyByUserId(BusinessUserExt businessUserExt);

	/**
	 * 依据用户ID查询商家扩展信息
	 * @param userId
	 * @return
	 */
	public BusinessUserExt selectByUserId(int userId);
	
	/**
	 * 依据ID查询商家扩展信息
	 * @param id
	 * @return
	 */
	public BusinessUserExt selectById(int id);
	
	/**
	 * 根据相应的条件查询满足条件的商家扩展信息的总数
	 * @param businessUserExtQuery
	 * @return
	 */
	public int countByCondition(BusinessUserExtQuery businessUserExtQuery);
	
	/**
	 * 根据相应的条件查询商家扩展信息
	 * @param businessUserExtQuery
	 * @return
	 */
	public List<BusinessUserExt> selectByCondition(BusinessUserExtQuery businessUserExtQuery);
	
	/**
	 * 根据相应的条件查询商家扩展信息
	 * @param businessUserExtQuery
	 * @return
	 */
	public List<BusinessUserExt> selectByConditionWithPage(BusinessUserExtQuery businessUserExtQuery);
	
}