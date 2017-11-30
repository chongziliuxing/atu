package com.atu.erp.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atu.erp.dao.OperationDao;
import com.atu.erp.domain.Comment;
import com.atu.erp.domain.ForbiddenUsers;
import com.atu.erp.domain.Hotwords;
import com.atu.erp.domain.query.CommentQuery;
import com.atu.erp.domain.query.ForbiddenUsersQuery;
import com.atu.erp.domain.query.HotwordsQuery;
import com.atu.erp.manager.OperationManager;

@Repository
public class OperationManagerImpl
  implements OperationManager
{

  @Autowired
  private OperationDao operationDao;
  private static final Log LOG = LogFactory.getLog(OperationManagerImpl.class);

  public List<Hotwords> selectByCondition(HotwordsQuery hotwordsQuery) {
    return this.operationDao.selectByCondition(hotwordsQuery);
  }

  public List<Hotwords> selectByLikeCondition(HotwordsQuery hotwordsQuery) {
    return this.operationDao.selectByLikeCondition(hotwordsQuery);
  }

  public Integer insert(Hotwords hotwords) {
    return this.operationDao.insert(hotwords);
  }

  public Hotwords selectByHotwordsId(int id) {
    return this.operationDao.selectByHotwordsId(id);
  }

  public void deleteHotwords(Integer id) {
    this.operationDao.deleteHotwords(id);
  }

  public void modify(Hotwords hotwords) {
    this.operationDao.modify(hotwords);
  }
  public void modifyComment(CommentQuery commentQuery) {
	    this.operationDao.modifyComment(commentQuery);
	  }
  public void deleteById(Integer proValId) {
    this.operationDao.deleteById(proValId);
  }

@Override
public int countByConditionComment(CommentQuery commentQuery) {
	return this.operationDao.countByConditionComment(commentQuery);
}

@Override
public List<Comment> selectByConditionForPageComment(CommentQuery commentQuery) {
	return this.operationDao.selectByConditionForPageComment(commentQuery);
}
@Override
public int countByConditionForbiddenUsers(ForbiddenUsersQuery forbiddenUsersQuery) {
	return this.operationDao.countByConditionForbiddenUsers(forbiddenUsersQuery);
}

@Override
public List<ForbiddenUsers> selectByConditionForPageForbiddenUsers(ForbiddenUsersQuery forbiddenUsersQuery) {
	return this.operationDao.selectByConditionForPageForbiddenUsers(forbiddenUsersQuery);
}

@Override
public Integer insert(ForbiddenUsersQuery forbiddenUsersQuery) {
	return this.operationDao.insert(forbiddenUsersQuery);
}

@Override
public void deleteForbiddenUsersById(Integer forbiddenId) {
	this.operationDao.deleteForbiddenUsersById(forbiddenId);
	
}
}