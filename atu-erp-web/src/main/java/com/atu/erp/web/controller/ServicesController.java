package com.atu.erp.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.atu.erp.common.utils.CookieUtil;
import com.atu.erp.domain.BusinessUserExt;
import com.atu.erp.domain.Hotwords;
import com.atu.erp.domain.Property;
import com.atu.erp.domain.PropertyValue;
import com.atu.erp.domain.ServiceCategory;
import com.atu.erp.domain.ServiceInfo;
import com.atu.erp.domain.query.HotwordsQuery;
import com.atu.erp.domain.query.PropertyQuery;
import com.atu.erp.domain.query.PropertyValueQuery;
import com.atu.erp.domain.query.ServiceCategoryQuery;
import com.atu.erp.domain.query.ServiceInfoQuery;
import com.atu.erp.service.BusinessUserExtService;
import com.atu.erp.service.CategoryService;
import com.atu.erp.service.PropertyService;
import com.atu.erp.service.PropertyValueService;
import com.atu.erp.service.ServInfoService;
import com.atu.erp.service.ServicesService;

@Controller
@RequestMapping("/services")
public class ServicesController {
	@Autowired
	private PropertyService propertyService;
	@Autowired
	PropertyValueService propertyValueService;
	@Autowired
	ServicesService servicesService;  //对应 ServiceCategory 
	@Autowired
	ServInfoService servInfoService;  //对应 ServInfo
	
	@Autowired
	private BusinessUserExtService businessUserExtService;
	
	private final static Log LOG = LogFactory.getLog(ServicesController.class);

	
//	@RequestMapping(value="/getLevelOne", method={ RequestMethod.GET, RequestMethod.POST })
//	public String queryPromotion(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
//
//		return "product/product";
//	}
	@RequestMapping(value="/deletService", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> deletService(Integer id, HttpServletResponse response, HttpServletRequest request, ModelMap map) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {	
			if(id!=null){
				servInfoService.deleteById(id);
			}
			resultMap.put("msg","success");
		} catch (Exception e) {
			LOG.error("deletService===", e);
		}
		return resultMap;
	}
	@RequestMapping(value="/getLevel", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> getLevel(Integer level,Integer pId,HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ServiceCategory category=new ServiceCategory();
		try {	
			ServiceCategoryQuery categoryQuery=new ServiceCategoryQuery();
			//设置查询几级分类
			if(level!=null){
				categoryQuery.setCategoryLevel(level);
			}
			if(pId!=null){
				categoryQuery.setParentCategoryId(pId);
			}
			List<ServiceCategory> categoryList = servicesService.selectByCondition(categoryQuery);
			resultMap.put("msg","success");
			resultMap.put("categoryList",categoryList);//登录成功后，跳转到的页面
			resultMap.put("category",category);//登录成功后，跳转到的页面
		} catch (Exception e) {
			LOG.error("ServicesController.getLevel===", e);
		}
		return resultMap;
	}
	
	@RequestMapping(value="/addLevel", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addLevel(ServiceCategory category,HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {	
			ServiceCategoryQuery categoryQuery=new ServiceCategoryQuery();
			//设置查询几级分类
			if(category.getCategoryLevel() != null){
				//categoryQuery.setCategoryLevel(level);
			}

			List<ServiceCategory> categoryList = servicesService.selectByCondition(categoryQuery);
			resultMap.put("msg","success");
			resultMap.put("categoryList",categoryList);//登录成功后，跳转到的页面
			resultMap.put("category",category);//登录成功后，跳转到的页面
		} catch (Exception e) {
			LOG.error("ServicesController.getLevel===", e);
		}
		return resultMap;
	}

	@RequestMapping(value="/addProVal", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addProVal(PropertyValue propertyValue, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			BusinessUserExt userExt=businessUserExtService.selectByUserId(CookieUtil.getUserId(reuqest));
			
			propertyValue.setVenderUserId(userExt.getId());
			propertyValueService.insert(propertyValue);
//			context.put("propertyValue", propertyValue);
			resultMap.put("msg","success");
			resultMap.put("propertyValue",propertyValue);
			//resultMap.put("category",category);
		} catch (Exception e) {
			LOG.error("ServicesController.addProVal===", e);
			resultMap.put("msg","error");
		}
		//return "services/addProperty";
		return resultMap;
	}

	
	@RequestMapping(value="/deletProVal", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> deletProVal(Integer proValId, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {	
			if(proValId!=null){
				propertyValueService.deleteById(proValId);
			}
			resultMap.put("msg","success");
		} catch (Exception e) {
			LOG.error("ServicesController.deletProVal===", e);
		}
		return resultMap;
	}
	
	
	@RequestMapping(value="/deleteCategory1", method={ RequestMethod.GET, RequestMethod.POST })
	public  @ResponseBody Map<String, Object> deleteCategory1(Integer categoryId, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {	
			if(categoryId!=null){
				servicesService.deleteCategory1(categoryId);
				servicesService.deleteCategory2ByPar(categoryId);
			}
		} catch (Exception e) {
			LOG.error("ServicesController.deleteCategory1：", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	@RequestMapping(value="/deleteCategory2", method={ RequestMethod.GET, RequestMethod.POST })
	public  @ResponseBody Map<String, Object> deleteCategory2(Integer categoryId, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {	
			if(categoryId!=null){
				servicesService.deleteCategory1(categoryId);				
			}
		} catch (Exception e) {
			LOG.error("ServicesController.deleteCategory2：", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	
	
	
	@RequestMapping(value="/changeToHavePro", method={ RequestMethod.GET, RequestMethod.POST })
	public  @ResponseBody Map<String, Object> changeToHavePro(Integer categoryId, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ServiceCategory category = new ServiceCategory();
		try {	
			if(categoryId!=null){
				category.setCategoryId(categoryId);
//				category.setIfHaveSaleProperty(1);
				servicesService.modify(category);	
				// TODO 有销售属性
				Property property =new Property();
				property.setCategoryId(categoryId);
				property.setSortNumber(1);
				property.setPropertyName("规格");
				property.setYn(1);
				property.setPropertyType(3);
				propertyService.insert(property);
			}
		} catch (Exception e) {
			LOG.error("ServicesController.changeToHavePro：", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	@RequestMapping(value="/getProperty", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> getProperty(Integer categoryId,HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ServiceCategory category=new ServiceCategory();
		try {	
			ServiceCategoryQuery categoryQuery=new ServiceCategoryQuery();
			//设置查询几级分类
			if(categoryId!=null){
				categoryQuery.setCategoryId(categoryId);
			}
			List<ServiceCategory> categoryList = servicesService.selectByCondition(categoryQuery);
			//没有销售属性
//			if(categoryList!=null&&categoryList.get(0).getIfHaveSaleProperty()!=1){
//				resultMap.put("success","success");
//				resultMap.put("hasProperty","no");
//				return resultMap;
//			}else{
//				resultMap.put("hasProperty","yes");
//				
//				//查询所有的销售属性和对应的值
//				PropertyQuery propertyQuery=new PropertyQuery();
//				propertyQuery.setCategoryId(categoryId);
//				List<Property> propertyList = propertyService.selectByCondition(propertyQuery);
//				if(propertyList.isEmpty()){
//					//没有销售属性，提示用户添加销售属性
//					resultMap.put("success","success");
//					resultMap.put("haveProperyValue","no");
////					resultMap.put(key, value);
//					resultMap.put("msg","该商品需要添加销售属性，请添加！");
//					return resultMap;	
//				}
//				Property property = propertyList.get(0);
//				resultMap.put("propertyId",property.getPropertyId());
//				resultMap.put("propertyName",property.getPropertyName());
				PropertyValueQuery propertyValueQuery =new PropertyValueQuery();
//				propertyValueQuery.setPropertyId(property.getPropertyId());
//				propertyValueQuery.setVenderUserId(CookieUtil.getUserId(request));//设置商家ID
				List<PropertyValue> propertyValueList = propertyValueService.selectByCondition(propertyValueQuery);
				if(propertyValueList.isEmpty()){
					//商家没有销售属性，提示添加
					resultMap.put("success","success");
					resultMap.put("havePropery","no");
					resultMap.put("msg","该商品需要添加销售属性，请添加！");
					return resultMap;
				}
				resultMap.put("success","success");
				resultMap.put("propertyValueList",propertyValueList);
//			}
			resultMap.put("msg","success");
			resultMap.put("category",category);//登录成功后，跳转到的页面
		} catch (Exception e) {
			LOG.error("ServicesController.getLevel:", e);
		}
		return resultMap;
	}
	
	
	@RequestMapping(value="/uploaImage", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> uploaImage(Integer imageId,HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String path = null;
		String savefilePath=null;
        //创建一个通用的多部分解析器.     
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext()); 
        //判断 request 是否有文件上传,即多部分请求...    
        if(multipartResolver.isMultipart(request))  
        {  
             //判断 request 是否有文件上传,即多部分请求...    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                MultipartFile file = multiRequest.getFile(iter.next());
                if(StringUtils.isBlank(file.getOriginalFilename())){//文件为空
        			resultMap.put("msg","null");
        			return resultMap;
                }

                //String rootPath = request.getSession().getServletContext().getRealPath("/");
                String fileName = file.getOriginalFilename();
                String fileType=fileName.substring(fileName.lastIndexOf("."));
                int choice=97;
                Random random=new Random();
                char var = (char) (choice + random.nextInt(26)); 
                fileName= "p"+ var +(int)(Math.random() * 1000000)+fileType;
                
                
				Calendar cal = Calendar.getInstance();
				int year=cal.get(Calendar.YEAR);//得到年
				int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
				int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
				
				path = "/img" + "/" + year +"/" + month+"/"+day+"/";
				savefilePath = "/www.tbny.net" + path;
				File foler = new File(savefilePath);
				if(!foler.exists()){
					foler.mkdirs();
				}
				
				File savedFile = new File(savefilePath, fileName);
				path +=  fileName;
				savefilePath ="http:/" + savefilePath + fileName;
                try {
					file.transferTo(savedFile);
				} catch (IllegalStateException e) {
					LOG.error("ServicesController.uploaImage:", e);
				} catch (IOException e) {
					LOG.error("ServicesController.uploaImage:", e);
				}  
            }  
        }  
		resultMap.put("msg","success");
		resultMap.put("imageUrl", savefilePath);	
		String jsonStr = "{\"msg\":\"success\",\"imageUrl\":\""+savefilePath+"\"}";
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.print(jsonStr);
//		map.put("msg", "success");
//		map.put("imageUrl",savefilePath);
	
		return null;
	}
	
	@RequestMapping(value="/getServicesList",method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> getServicesList(HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {	
			ServiceInfoQuery serviceinfoquery=new ServiceInfoQuery();
			List<ServiceInfo> serviceinfoList = servInfoService.selectByCondition(serviceinfoquery);
			resultMap.put("msg","success");
			resultMap.put("serviceinfoList",serviceinfoList);//登录成功后，跳转到的页面
		} catch (Exception e) {
			LOG.error("getServicesList===", e);
		}
		return resultMap;
	}
	@RequestMapping(value="/addService", method={ RequestMethod.GET, RequestMethod.POST })
	public String addService(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){

		return "services/addServices";
	}
	@RequestMapping(value="/addServs", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addServices(ServiceInfo serviceinfo, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			servInfoService.insert(serviceinfo);
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
	
	@RequestMapping(value="/category1st", method={ RequestMethod.GET, RequestMethod.POST })
	public String category1(Integer page, ServiceCategoryQuery categoryQuery,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		if(page==null){
			categoryQuery.setPageNo(1);
		}else{
			categoryQuery.setPageNo(page);
		}
		//设置查询几级分类
		categoryQuery.setCategoryLevel(1);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ServiceCategory> categoryList = servicesService.selectByLikeCondition(categoryQuery);
		
		resultMap.put("itemList", categoryList);
		resultMap.put("services", categoryQuery);
		
		context.put("resultMap", resultMap);

		return "services/category1st";
	}

	

	@RequestMapping(value="/category2nd", method={ RequestMethod.GET, RequestMethod.POST })
	public String category2(Integer page, ServiceCategoryQuery categoryQuery,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		if(page==null){
			categoryQuery.setPageNo(1);
		}else{
			categoryQuery.setPageNo(page);
		}
		
		//设置查询几级分类
		categoryQuery.setCategoryLevel(2);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ServiceCategory> categoryList = servicesService.selectByLikeCondition(categoryQuery);

		resultMap.put("itemList", categoryList);
		resultMap.put("services", categoryQuery);
		
		context.put("resultMap", resultMap);

		return "services/category2nd";
	}
	
	

	@RequestMapping(value="/addCategory1", method={ RequestMethod.GET, RequestMethod.POST })
	public String addCategory1(ServiceCategory category, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		//category.setParentCategoryId(null);
		//category.setCategoryLevel(1);
		category.setYn(1);
		if(category.getSortNumber()!=null){
			category.setSortNumber(category.getSortNumber());
		}	
		try {
			servicesService.insert(category);
		} catch (Exception e) {
			LOG.error("ServicesController.addProVal===", e);
		}
		
		try {
			//跳转到显示界面
			response.sendRedirect("/services/category"+category.getCategoryLevel()+"st");
		} catch (IOException e) {
			
			LOG.error("ServicesController.addCategory1:", e);
			//e.printStackTrace();
		}

		return "services/category"+category.getCategoryLevel()+"st";
	}
	
	
	@RequestMapping(value="/addCategory2", method={ RequestMethod.GET, RequestMethod.POST })
	public String addCategory2(ServiceCategory category, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		//category.setParentCategoryId(null);
		//category.setCategoryLevel(1);
		category.setYn(1);
		if(category.getSortNumber()!=null){
			category.setSortNumber(category.getSortNumber());
		}	
		try {
			servicesService.insert(category);
		} catch (Exception e) {
			LOG.error("ServicesController.addProVal===", e);
		}
		
		try {
			//跳转到显示界面
			response.sendRedirect("/services/category"+category.getCategoryLevel()+"nd");
		} catch (IOException e) {
			
			LOG.error("ServicesController.addCategory2:", e);
			//e.printStackTrace();
		}

		return "services/category"+category.getCategoryLevel()+"nd";
	}
	
	@RequestMapping(value="/upDescripImg", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> upDescripImg(HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String path = null;
		String savefilePath=null;
        //创建一个通用的多部分解析器.     
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext()); 
        //判断 request 是否有文件上传,即多部分请求...    
        if(multipartResolver.isMultipart(request))  
        {  
             //判断 request 是否有文件上传,即多部分请求...    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                MultipartFile file = multiRequest.getFile(iter.next());
                if(StringUtils.isBlank(file.getOriginalFilename())){//文件为空
        			resultMap.put("err","文件为空！");
        			resultMap.put("msg","");
        			return resultMap;
                }

                //String rootPath = request.getSession().getServletContext().getRealPath("/");
                String fileName = file.getOriginalFilename();
                String fileType=fileName.substring(fileName.lastIndexOf("."));
                int choice=97;
                Random random=new Random();
                char var = (char) (choice + random.nextInt(26)); 
                fileName= "p"+ var +(int)(Math.random() * 1000000)+fileType;
                
                
				Calendar cal = Calendar.getInstance();
				int year=cal.get(Calendar.YEAR);//得到年
				int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
				int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
				
				path = "/img" + "/" + year +"/" + month+"/"+day+"/";
				savefilePath = "/www.tbny.net" + path;
				File foler = new File(savefilePath);
				if(!foler.exists()){
					foler.mkdirs();
				}
				
				File savedFile = new File(savefilePath, fileName);
				path +=  fileName;
				savefilePath ="http:/" + savefilePath + fileName;
                try {
					file.transferTo(savedFile);
				} catch (IllegalStateException e) {
					LOG.error("ServicesController.uploaImage:", e);
				} catch (IOException e) {
					LOG.error("ServicesController.uploaImage:", e);
				}  
            }  
        }         
        resultMap.put("err","");
		resultMap.put("msg",savefilePath);
		//resultMap.put("imageUrl", savefilePath);	
		
		String jsonStr = "{\"err\":\"\",\"msg\":\""+savefilePath+"\"}";
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.print(jsonStr);
//		map.put("err", "");
//		map.put("msg",savefilePath);
		
	
		return null;
	}
	
}

