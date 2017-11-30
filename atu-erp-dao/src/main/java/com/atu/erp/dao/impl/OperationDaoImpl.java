package com.atu.erp.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.atu.erp.dao.OperationDao;
import com.atu.erp.domain.Comment;
import com.atu.erp.domain.ForbiddenUsers;
import com.atu.erp.domain.Hotwords;
import com.atu.erp.domain.query.CommentQuery;
import com.atu.erp.domain.query.ForbiddenUsersQuery;
import com.atu.erp.domain.query.HotwordsQuery;

public class OperationDaoImpl extends SqlMapClientTemplate
  implements OperationDao
{
  public Integer insert(Hotwords hotwords)
  {
    return (Integer)insert("Hotwords.insert", hotwords);
  }

  public void modify(Hotwords hotwords)
  {
    update("Hotwords.updateByPrimaryKey", hotwords);
  }

  public Hotwords selectByHotwordsId(int id)
  {
    return (Hotwords)queryForObject("Hotwords.selectByPrimaryKey", Integer.valueOf(id));
  }

  public int countByCondition(HotwordsQuery hotwordsQuery)
  {
    return ((Integer)queryForObject("Hotwords.countByCondition", hotwordsQuery)).intValue();
  }

  public List<Hotwords> selectByCondition(HotwordsQuery hotwordsQuery)
  {
    return queryForList("Hotwords.selectByCondition", hotwordsQuery);
  }

  public List<Hotwords> selectByConditionForPage(HotwordsQuery hotwordsQuery)
  {
    return queryForList("Hotwords.selectByConditionForPage", hotwordsQuery);
  }

  public List<Hotwords> selectByLikeCondition(HotwordsQuery hotwordsQuery)
  {
    return queryForList("Hotwords.selectByLikeCondition", hotwordsQuery);
  }

  public void deleteHotwords(Integer id)
  {
    delete("Hotwords.deleteHotwords", id);
  }

  public void deleteById(Integer proValId)
  {
    delete("Hotwords.deleteById", proValId);
  }

@Override
public int countByConditionComment(CommentQuery commentQuery) {
	return ((Integer)queryForObject("Comment.countByCondition", commentQuery)).intValue();
}

@Override
public List<Comment> selectByConditionForPageComment(CommentQuery commentQuery) {
	return queryForList("Comment.selectByConditionForPage", commentQuery);
}

@Override
public int countByConditionForbiddenUsers(ForbiddenUsersQuery forbiddenUsersQuery) {
	return ((Integer)queryForObject("ForbiddenUsers.countByCondition", forbiddenUsersQuery)).intValue();
}

@Override
public List<ForbiddenUsers> selectByConditionForPageForbiddenUsers(ForbiddenUsersQuery forbiddenUsersQuery) {
	return queryForList("ForbiddenUsers.selectByConditionForPage", forbiddenUsersQuery);
}

@Override
public Integer insert(ForbiddenUsersQuery forbiddenUsersQuery) {
	 return (Integer)insert("ForbiddenUsers.insert", forbiddenUsersQuery);
}

@Override
public void deleteForbiddenUsersById(Integer forbiddenId) {
	delete("ForbiddenUsers.deleteById", forbiddenId);
	
}

@Override
public void modifyComment(CommentQuery commentQuery) {
	update("Comment.updateByPrimaryKeySelective", commentQuery);
	
}
}