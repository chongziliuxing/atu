package com.atu.erp.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atu.erp.common.utils.PaginatedList;
import com.atu.erp.common.utils.impl.PaginatedArrayList;
import com.atu.erp.domain.Comment;
import com.atu.erp.domain.ForbiddenUsers;
import com.atu.erp.domain.Hotwords;
import com.atu.erp.domain.query.CommentQuery;
import com.atu.erp.domain.query.ForbiddenUsersQuery;
import com.atu.erp.domain.query.HotwordsQuery;
import com.atu.erp.manager.OperationManager;
import com.atu.erp.service.OperationService;

@Service("operationService")
public class OperationServiceImpl implements OperationService {
	private static Log log = LogFactory.getLog(OperationServiceImpl.class);
	@Autowired
	private OperationManager operationManager;

	public List<Hotwords> selectByCondition(HotwordsQuery hotwordsQuery) {
		return this.operationManager.selectByCondition(hotwordsQuery);
	}

	public List<Hotwords> selectByLikeCondition(HotwordsQuery hotwordsQuery) {
		return this.operationManager.selectByLikeCondition(hotwordsQuery);
	}

	public Integer insert(Hotwords hotwords) {
		return this.operationManager.insert(hotwords);
	}

	public Hotwords selectByHotwordsId(int id) {
		return this.operationManager.selectByHotwordsId(id);
	}

	public void deleteHotwords(Integer id) {
		this.operationManager.deleteHotwords(id);
	}

	public void modify(Hotwords hotwords) {
		this.operationManager.modify(hotwords);
	}
	public void modifyComment(CommentQuery commentQuery) {
		this.operationManager.modifyComment(commentQuery);
	}
	public void deleteById(Integer proValId) {
		this.operationManager.deleteById(proValId);
	}
	public Integer insert(ForbiddenUsersQuery forbiddenUsersQuery) {
		return this.operationManager.insert(forbiddenUsersQuery);
	}
	public void deleteForbiddenUsersById(Integer forbiddenId) {
		this.operationManager.deleteForbiddenUsersById(forbiddenId);
	}
	@Override
	public PaginatedList<Comment> getByPageComment(CommentQuery commentQuery) {
		PaginatedList<Comment> commentList = new PaginatedArrayList<Comment>(commentQuery.getPageNo(), commentQuery.getPageSize());
		try {
			int count = operationManager.countByConditionComment(commentQuery);
			if (count > 0) {
				commentList.setTotalItem(count);
				commentQuery.setStart(commentList.getStartRow() - 1);
				List<Comment> list = operationManager.selectByConditionForPageComment(commentQuery);
				commentList.addAll(list);
				return commentList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("", e);
		}

		return commentList;
	}
	
	@Override
	public PaginatedList<ForbiddenUsers> getByPageForbiddenUsers(ForbiddenUsersQuery forbiddenUsersQuery) {
		PaginatedList<ForbiddenUsers> forbiddenUsersList = new PaginatedArrayList<ForbiddenUsers>(forbiddenUsersQuery.getPageNo(), forbiddenUsersQuery.getPageSize());
		try {
			int count = operationManager.countByConditionForbiddenUsers(forbiddenUsersQuery);
			if (count > 0) {
				forbiddenUsersList.setTotalItem(count);
				forbiddenUsersQuery.setStart(forbiddenUsersList.getStartRow() - 1);
				List<ForbiddenUsers> list = operationManager.selectByConditionForPageForbiddenUsers(forbiddenUsersQuery);
				forbiddenUsersList.addAll(list);
				return forbiddenUsersList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("", e);
		}

		return forbiddenUsersList;
	}
	
}