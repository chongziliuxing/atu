package com.atu.erp.service;

import java.util.List;

import com.atu.erp.common.utils.PaginatedList;
import com.atu.erp.domain.Comment;
import com.atu.erp.domain.ForbiddenUsers;
import com.atu.erp.domain.Hotwords;
import com.atu.erp.domain.query.CommentQuery;
import com.atu.erp.domain.query.ForbiddenUsersQuery;
import com.atu.erp.domain.query.HotwordsQuery;

public abstract interface OperationService
{
  public abstract List<Hotwords> selectByCondition(HotwordsQuery paramHotwordsQuery);

  public abstract List<Hotwords> selectByLikeCondition(HotwordsQuery paramHotwordsQuery);

  public abstract Integer insert(Hotwords paramHotwords);

  public abstract Hotwords selectByHotwordsId(int paramInt);

  public abstract void deleteHotwords(Integer paramInteger);

  public abstract void modify(Hotwords paramHotwords);

  public abstract void deleteById(Integer paramInteger);
  
  public abstract Integer insert(ForbiddenUsersQuery forbiddenUsersQuery);
  
  public abstract void deleteForbiddenUsersById(Integer forbiddenId);
  
  public abstract void modifyComment(CommentQuery commentQuery);
	/**
	 * 分页查询评价审核列表
	 * @param commentQuery
	 * @return
	 */
	public PaginatedList<Comment> getByPageComment(CommentQuery commentQuery);
	/**
	 * 分页禁言名单列表
	 * @param commentQuery
	 * @return
	 */
	public PaginatedList<ForbiddenUsers> getByPageForbiddenUsers(ForbiddenUsersQuery forbiddenUsersQuery);

}