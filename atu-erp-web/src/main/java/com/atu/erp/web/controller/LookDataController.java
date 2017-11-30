package com.atu.erp.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atu.erp.common.utils.PaginatedList;
import com.atu.erp.domain.RiskStatPvuvOrders;
import com.atu.erp.domain.query.RiskStatPvuvOrdersQuery;
import com.atu.erp.service.RiskStatPvuvOrdersService;

@Controller
@RequestMapping("/lookData")
public class LookDataController{
	
	@Autowired
	private RiskStatPvuvOrdersService riskStatPvuvOrdersService;
	
	@InitBinder
	public void initDateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value="/survey",method={RequestMethod.GET, RequestMethod.POST})
	public String survey(HttpServletResponse response, HttpServletRequest request, ModelMap context){
		RiskStatPvuvOrders riskStatPvuvOrders = riskStatPvuvOrdersService.getRiskStatPvuvOrdersSurvey(0);
		context.put("riskStatPvuvOrders", riskStatPvuvOrders);
		return "lookData/survey";
	}
	
	@RequestMapping(value="/initChart",method={RequestMethod.GET, RequestMethod.POST})
	public String chart(HttpServletResponse response, HttpServletRequest request, ModelMap context){
		return "lookData/chart";
	}
	
	@RequestMapping(value="/chart",method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Map<String, Object> chart(RiskStatPvuvOrdersQuery riskStatPvuvOrdersQuery, HttpServletResponse response, HttpServletRequest request, ModelMap context){
		return riskStatPvuvOrdersService.getChartInfo(riskStatPvuvOrdersQuery);
	}
	
	@RequestMapping(value="/statistics",method={RequestMethod.GET, RequestMethod.POST})
	public String statistics(RiskStatPvuvOrdersQuery riskStatPvuvOrdersQuery, HttpServletResponse response, HttpServletRequest request,ModelMap context){
 		PaginatedList<RiskStatPvuvOrders> list = riskStatPvuvOrdersService.getRiskStatPvuvOrdersForPage(riskStatPvuvOrdersQuery);
		context.put("list", list);
		context.put("query", riskStatPvuvOrdersQuery);
		return "lookData/statistics";
	}
}
