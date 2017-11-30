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

import com.atu.erp.domain.Brand;
import com.atu.erp.domain.query.BrandQuery;
import com.atu.erp.service.BrandService;

@Controller
@RequestMapping("/brand")
public class BrandController {

	@Autowired
	private BrandService brandService;
	
	@RequestMapping(value="/home", method={ RequestMethod.GET, RequestMethod.POST })
	public String brandHome(BrandQuery brandQuery, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		return "/brand/listBrand";
	}
	
	@RequestMapping(value="/listBrand", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> listBrand(String brandName, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		BrandQuery brandQuery = new BrandQuery();
		brandQuery.setBrandName(brandName);
		List<Brand> brandList = brandService.selectByCondition(brandQuery);
		resultMap.put("msg","success");
		resultMap.put("brandList",brandList);
		return resultMap;
	}
	
	@RequestMapping(value="/addBrand", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addBrand(Brand brand, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
//		Brand brand = new Brand();
//		brand.setBrandName(brandName);
		brandService.insert(brand);
		resultMap.put("msg","success");
		return resultMap;
	}
	
	@RequestMapping(value="/delBrand", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> delBrand(Integer brandId, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		brandService.delete(brandId);
		resultMap.put("msg","success");
		return resultMap;
	}
	
}
