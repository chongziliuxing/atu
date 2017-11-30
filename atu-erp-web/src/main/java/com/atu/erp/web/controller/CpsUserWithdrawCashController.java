package com.atu.erp.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atu.erp.domain.query.CPSUserWithdrawCashQuery;
import com.atu.erp.service.CpsUserWithdrawCashService;
import com.atu.erp.service.result.Result;
import com.atu.erp.web.base.BaseController;

@Controller
@RequestMapping("withdrawals")
public class CpsUserWithdrawCashController extends BaseController{
	
	private final static Log log = LogFactory.getLog(CpsUserWithdrawCashController.class);
	
	@Autowired
	private CpsUserWithdrawCashService cpsUserWithdrawCashService;
	
	@RequestMapping(value="index", method={ RequestMethod.GET, RequestMethod.POST })
	public String index(CPSUserWithdrawCashQuery cpsUserWithdrawCashQuery,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(cpsUserWithdrawCashQuery.getPageNo()==null){
				cpsUserWithdrawCashQuery.setPageNo(1);
			}
			resultMap = cpsUserWithdrawCashService.getCPSUserWithdrawCashsByPage(cpsUserWithdrawCashQuery);
			context.put("resultMap", resultMap);
		} catch (Exception e) {
			log.error("withdrawals.index:", e);
		}
		return "/withdrawals/index";
	}
	
	@RequestMapping(value="editState", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Result editState(Integer id,String transferOrderNumber,Integer withdrawState, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		return httpGetDataResult("/withdraws/editWithdraws","id="+ id +"&transferOrderNumber="+ transferOrderNumber +"&withdrawState="+ withdrawState);
	}
	
}
