package com.atu.erp.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/finance")
public class FinanceController {
	private final static Log log = LogFactory.getLog(FinanceController.class);
	@RequestMapping(value="/supplyCash", method={ RequestMethod.GET, RequestMethod.POST })
	public String supplyCash(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){

		return "/finance/supplyCash";
	}
	@RequestMapping(value="/cpsUserCash", method={ RequestMethod.GET, RequestMethod.POST })
	public String record(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){

		return "/finance/cpsUserCash";
	}
}
