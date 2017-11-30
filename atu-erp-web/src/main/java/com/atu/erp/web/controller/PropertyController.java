package com.atu.erp.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atu.erp.domain.Property;
import com.atu.erp.domain.PropertySub;
import com.atu.erp.domain.query.PropertyQuery;
import com.atu.erp.domain.query.PropertySubQuery;
import com.atu.erp.service.PropertyService;
import com.atu.erp.service.PropertySubService;

@Controller
@RequestMapping("/property")
public class PropertyController {

	@Autowired
	private PropertySubService propertySubService;
	
	@Autowired
	private PropertyService propertyService;
	
	@RequestMapping(value="/home", method={ RequestMethod.GET, RequestMethod.POST })
	public String proHome(HttpServletRequest reuqest,HttpServletResponse response, ModelMap modelMap){
		return "/property/propertyHome";
	}
	
	@RequestMapping(value="/subHome", method={ RequestMethod.GET, RequestMethod.POST })
	public String proSubHome(String propertyId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap modelMap){
		modelMap.addAttribute("propertyId", propertyId);
		return "/property/propertySubHome";
	}
	
	@RequestMapping(value="/listPro", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> listPro(PropertyQuery propertyQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap modelMap){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Property> propertyList = propertyService.selectByCondition(propertyQuery);
		resultMap.put("propertyList",propertyList);
		resultMap.put("msg","success");
		return resultMap;
	}
	
	@RequestMapping(value="/listProSub", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> listProSub(PropertySubQuery propertySubQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap modelMap){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<PropertySub> propertySubList = propertySubService.selectByCondition(propertySubQuery);
		Property property = propertyService.selectByPropertyId(propertySubQuery.getPropertyId());
		resultMap.put("propertySubList",propertySubList);
		resultMap.put("property",property);
		resultMap.put("msg","success");
		return resultMap;
	}
	
	@RequestMapping(value="/addPro", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addPro(Property property, HttpServletRequest reuqest,HttpServletResponse response, ModelMap modelMap){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		propertyService.insert(property);
		resultMap.put("msg","success");
		return resultMap;
	}
	
	@RequestMapping(value="/addProSub", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addProSub(PropertySub propertySub, HttpServletRequest reuqest,HttpServletResponse response, ModelMap modelMap){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		propertySubService.insert(propertySub);
		resultMap.put("msg","success");
		return resultMap;
	}
}
