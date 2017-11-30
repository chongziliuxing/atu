package com.atu.erp.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atu.erp.common.utils.CookieUtil;
import com.atu.erp.common.utils.PaginatedList;
import com.atu.erp.domain.BalanceInfo;
import com.atu.erp.domain.Comment;
import com.atu.erp.domain.ForbiddenUsers;
import com.atu.erp.domain.Hotwords;
import com.atu.erp.domain.UserInfo;
import com.atu.erp.domain.query.CommentQuery;
import com.atu.erp.domain.query.ForbiddenUsersQuery;
import com.atu.erp.domain.query.HotwordsQuery;
import com.atu.erp.domain.query.UserInfoQuery;
import com.atu.erp.service.OperationService;
import com.atu.erp.service.UserService;

@Controller
@RequestMapping("/operation")
public class OperationController {

	@Autowired
	private OperationService operationService;
	@Autowired
	private UserService userService;
	private final static Log LOG = LogFactory.getLog(OperationController.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	@InitBinder
	public void initDateBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf2, true));
	}
	
	@RequestMapping(value="/addHotword", method={ RequestMethod.GET, RequestMethod.POST })
	public String addHotword(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){

		return "operation/addHotwords";
	}
	@RequestMapping(value="/addHotwords", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addHotwords(Hotwords hotwords, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			operationService.insert(hotwords);
//			context.put("propertyValue", propertyValue);
			resultMap.put("msg","success");
//			resultMap.put("propertyValue",hotwords);
			//resultMap.put("category",category);
		} catch (Exception e) {
			LOG.error("OperationController.addHotwords===", e);
			resultMap.put("msg","error");
		}
		//return "item/addProperty";
		return resultMap;
	}
	
	/**
	 * 评价审核
	 * @param balanceInfoQuery
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/commentList", method={ RequestMethod.GET, RequestMethod.POST })
	public String index(CommentQuery commentQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		PaginatedList<Comment> list = operationService.getByPageComment(commentQuery);
		context.put("list", list);
		context.put("query", commentQuery);
		return "/operation/commentList";
	}
	
	/**
	 * 禁言管理
	 * @param forbiddenUsersQuery
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/blackList", method={ RequestMethod.GET, RequestMethod.POST })
	public String blackList(ForbiddenUsersQuery forbiddenUsersQuery, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		PaginatedList<ForbiddenUsers> list = operationService.getByPageForbiddenUsers(forbiddenUsersQuery);
		context.put("list", list);
		context.put("query", forbiddenUsersQuery);
		return "/operation/blackList";
	}
	
	/**
	 * 添加禁言
	 * @param forbiddenUsersQuery
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/addForbiddenUsers", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addForbiddenUsers(ForbiddenUsersQuery forbiddenUsersQuery, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Map<String,Object> map=new HashMap<String,Object>();
		if(forbiddenUsersQuery==null || forbiddenUsersQuery.getUserId()==null){
			map.put("success", false);
			map.put("message", "解除禁言用户ID不能为空！");
			return map;
		}
		try{
			UserInfo userInfo=userService.queryById(forbiddenUsersQuery.getUserId());
			if(userInfo==null){
				map.put("success", false);
				map.put("message", "输入的禁言用户ID，不存此用户，请检查输入是否有误！");
				return map;
			}
			
			forbiddenUsersQuery.setMobile(userInfo.getMobile());
			forbiddenUsersQuery.setCreateUserId(CookieUtil.getUserId(request));
		    operationService.insert(forbiddenUsersQuery);
		    map.put("success", true);
		}catch(Exception e){
			map.put("success", false);
			map.put("message", "添加禁言用户出错："+e.getMessage());
		}
		return map;
	}
	
	/**
	 * 解除禁言
	 * @param forbiddenUsersQuery
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/unForbiddenUsers", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> unForbiddenUsers(Integer forbiddenId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String,Object> map=new HashMap<String,Object>();
		if(forbiddenId==null || StringUtils.isBlank(forbiddenId.toString())){
			map.put("success", false);
			map.put("message", "解除禁言ID不能为空！");
			return map;
		}
		try{
		    operationService.deleteForbiddenUsersById(forbiddenId);
		    map.put("success", true);
		}catch(Exception e){
			map.put("success", false);
			map.put("message", "解除禁言失败："+e.getMessage());
		}
		return map;
	}
	/**
	 * 评价审核
	 * @param balanceInfo
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/doAgree", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> doAgree(CommentQuery commentQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> map = new HashMap<String, Object>();
		if(commentQuery.getId() == null){
			map.put("success", false);
			map.put("message", "评价ID不能为空");
			return map;
		}
		if(commentQuery.getStatus() == null || !commentQuery.getStatus().toString().trim().matches("^[12]{1}$")){
			map.put("success", false);
			map.put("message", "审核状态不正确");
			return map;
		}
		try{
			if(commentQuery.getStatus()==1){
				commentQuery.setDeleted((byte)1);
			}
			operationService.modifyComment(commentQuery);
		    map.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", "审核失败："+e.getMessage());
		}
		
		return map;
	}
	
	/**
	 * 评价审核
	 * @param balanceInfo
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/doAgreeAll", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> doAgreeAll(String commentIdStr,String status, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> map = new HashMap<String, Object>();
		if(commentIdStr==null){
			map.put("success", false);
			map.put("message", "批量审核，至少传递一个评价ID，多个评价ID用\",\"隔开!");
		}
		if(status== null || !status.trim().matches("^[12]{1}$")){
			map.put("success", false);
			map.put("message", "审核状态不正确");
			return map;
		}
		String [] ids=commentIdStr.split(",");
		try{
		for(String id:ids){
			CommentQuery commentQuery=new CommentQuery();
			commentQuery.setId(Integer.parseInt(id));
			commentQuery.setStatus(Byte.parseByte(status));
			if(commentQuery.getStatus()==1){
				commentQuery.setDeleted((byte)1);
			}
			operationService.modifyComment(commentQuery);
		}
		    map.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", "批量审核失败："+e.getMessage());
		}
		return map;
	}
	
	/**
	 * 评价审核
	 * @param balanceInfo
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/doChangeCommentContent", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> doChangeCommentContent(CommentQuery commentQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> map = new HashMap<String, Object>();
		if(commentQuery.getId() == null){
			map.put("success", false);
			map.put("message", "评价ID不能为空");
			return map;
		}

		try{
			operationService.modifyComment(commentQuery);
		    map.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("message", "修改评论失败："+e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping(value="/getHotwords", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> getHotwords(HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {	
			HotwordsQuery hotwordsQuery=new HotwordsQuery();
			List<Hotwords> hotwordsList = operationService.selectByCondition(hotwordsQuery);
			resultMap.put("msg","success");
			resultMap.put("hotwordsList",hotwordsList);//登录成功后，跳转到的页面
		} catch (Exception e) {
			LOG.error("OperationController.getHotwords===", e);
		}
		return resultMap;
	}
	
	
	@RequestMapping(value="/deletHotword", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> deletHotword(Integer id, HttpServletResponse response, HttpServletRequest request, ModelMap map) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {	
			if(id!=null){
				operationService.deleteById(id);
			}
			resultMap.put("msg","success");
		} catch (Exception e) {
			LOG.error("CategoryController.deletProVal===", e);
		}
		return resultMap;
	}
}

