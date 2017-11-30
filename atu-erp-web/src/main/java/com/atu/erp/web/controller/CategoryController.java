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
import com.atu.erp.domain.Category;
import com.atu.erp.domain.Property;
import com.atu.erp.domain.PropertyValue;
import com.atu.erp.domain.query.CategoryQuery;
import com.atu.erp.domain.query.PropertyQuery;
import com.atu.erp.domain.query.PropertyValueQuery;
import com.atu.erp.service.BusinessUserExtService;
import com.atu.erp.service.CategoryService;
import com.atu.erp.service.PropertyService;
import com.atu.erp.service.PropertyValueService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PropertyService propertyService;
	@Autowired
	PropertyValueService propertyValueService;
	
	@Autowired
	private BusinessUserExtService businessUserExtService;
	
	private final static Log LOG = LogFactory.getLog(CategoryController.class);

	
//	@RequestMapping(value="/getLevelOne", method={ RequestMethod.GET, RequestMethod.POST })
//	public String queryPromotion(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
//
//		return "product/product";
//	}

	@RequestMapping(value="/getLevel", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> getLevel(Integer level,Integer pId,HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Category category=new Category();
		try {	
			CategoryQuery categoryQuery=new CategoryQuery();
			//设置查询几级分类
			if(level!=null){
				categoryQuery.setCategoryLevel(level);
			}
			if(pId!=null){
				categoryQuery.setParentCategoryId(pId);
			}
			List<Category> categoryList = categoryService.selectByCondition(categoryQuery);
			resultMap.put("msg","success");
			resultMap.put("categoryList",categoryList);//登录成功后，跳转到的页面
			resultMap.put("category",category);//登录成功后，跳转到的页面
		} catch (Exception e) {
			LOG.error("CategoryController.getLevel===", e);
		}
		return resultMap;
	}
	
	@RequestMapping(value="/addLevel", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addLevel(Category category,HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {	
			CategoryQuery categoryQuery=new CategoryQuery();
			//设置查询几级分类
			if(category.getCategoryLevel() != null){
				//categoryQuery.setCategoryLevel(level);
			}

			List<Category> categoryList = categoryService.selectByCondition(categoryQuery);
			resultMap.put("msg","success");
			resultMap.put("categoryList",categoryList);//登录成功后，跳转到的页面
			resultMap.put("category",category);//登录成功后，跳转到的页面
		} catch (Exception e) {
			LOG.error("CategoryController.getLevel===", e);
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
			LOG.error("CategoryController.addProVal===", e);
			resultMap.put("msg","error");
		}
		//return "item/addProperty";
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
			LOG.error("CategoryController.deletProVal===", e);
		}
		return resultMap;
	}
	
	
	@RequestMapping(value="/deleteCategory1", method={ RequestMethod.GET, RequestMethod.POST })
	public  @ResponseBody Map<String, Object> deleteCategory1(Integer categoryId, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {	
			if(categoryId!=null){
				categoryService.deleteCategory1(categoryId);
				categoryService.deleteCategory2ByPar(categoryId);
			}
		} catch (Exception e) {
			LOG.error("CategoryController.deleteCategory1：", e);
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
				categoryService.deleteCategory1(categoryId);				
			}
		} catch (Exception e) {
			LOG.error("CategoryController.deleteCategory2：", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	@RequestMapping(value="/deleteCategory3", method={ RequestMethod.GET, RequestMethod.POST })
	public  @ResponseBody Map<String, Object> deleteCategory3(Integer categoryId, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {	
			if(categoryId!=null){
				categoryService.deleteCategory1(categoryId);				
			}
		} catch (Exception e) {
			LOG.error("CategoryController.deleteCategory3：", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	@RequestMapping(value="/deleteCategory4", method={ RequestMethod.GET, RequestMethod.POST })
	public  @ResponseBody Map<String, Object> deleteCategory4(Integer categoryId, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {	
			if(categoryId!=null){
				categoryService.deleteCategory1(categoryId);				
			}
		} catch (Exception e) {
			LOG.error("CategoryController.deleteCategory4：", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	
	@RequestMapping(value="/changeToHavePro", method={ RequestMethod.GET, RequestMethod.POST })
	public  @ResponseBody Map<String, Object> changeToHavePro(Integer categoryId, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Category category = new Category();
		try {	
			if(categoryId!=null){
				category.setCategoryId(categoryId);
				category.setIfHaveSaleProperty(1);
				categoryService.modify(category);	
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
			LOG.error("CategoryController.changeToHavePro：", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	@RequestMapping(value="/getProperty", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> getProperty(Integer categoryId,HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Category category=new Category();
		try {	
			CategoryQuery categoryQuery=new CategoryQuery();
			//设置查询几级分类
			if(categoryId!=null){
				categoryQuery.setCategoryId(categoryId);
			}
			List<Category> categoryList = categoryService.selectByCondition(categoryQuery);
			//没有销售属性
			if(categoryList!=null&&categoryList.get(0).getIfHaveSaleProperty()!=1){
				resultMap.put("success","success");
				resultMap.put("hasProperty","no");
				return resultMap;
			}else{
				resultMap.put("hasProperty","yes");
				
				//查询所有的销售属性和对应的值
				PropertyQuery propertyQuery=new PropertyQuery();
				propertyQuery.setCategoryId(categoryId);
				List<Property> propertyList = propertyService.selectByCondition(propertyQuery);
				if(propertyList.isEmpty()){
					//没有销售属性，提示用户添加销售属性
					resultMap.put("success","success");
					resultMap.put("haveProperyValue","no");
//					resultMap.put(key, value);
					resultMap.put("msg","该商品需要添加销售属性，请添加！");
					return resultMap;	
				}
				Property property = propertyList.get(0);
				resultMap.put("propertyId",property.getPropertyId());
				resultMap.put("propertyName",property.getPropertyName());
				PropertyValueQuery propertyValueQuery =new PropertyValueQuery();
				propertyValueQuery.setPropertyId(property.getPropertyId());

//				BusinessUserExt userExt=businessUserExtService.selectByUserId(CookieUtil.getUserId(request));
//				propertyValueQuery.setVenderUserId(userExt.getId());
				propertyValueQuery.setVenderUserId(null);//商家ID null:系统管理员
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
			}
			resultMap.put("msg","success");
			resultMap.put("category",category);//登录成功后，跳转到的页面
		} catch (Exception e) {
			LOG.error("CategoryController.getLevel:", e);
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
					LOG.error("CategoryController.uploaImage:", e);
				} catch (IOException e) {
					LOG.error("CategoryController.uploaImage:", e);
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
	
	
	
	
	@RequestMapping(value="/category1", method={ RequestMethod.GET, RequestMethod.POST })
	public String category1(Integer page, CategoryQuery categoryQuery,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		if(page==null){
			categoryQuery.setPageNo(1);
		}else{
			categoryQuery.setPageNo(page);
		}
		//设置查询几级分类
		categoryQuery.setCategoryLevel(1);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Category> categoryList = categoryService.selectByLikeCondition(categoryQuery);
		
		resultMap.put("itemList", categoryList);
		resultMap.put("item", categoryQuery);
		
		context.put("resultMap", resultMap);

		return "item/category1";
	}

	

	@RequestMapping(value="/category2", method={ RequestMethod.GET, RequestMethod.POST })
	public String category2(Integer page, CategoryQuery categoryQuery,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		if(page==null){
			categoryQuery.setPageNo(1);
		}else{
			categoryQuery.setPageNo(page);
		}
		
		//设置查询几级分类
		categoryQuery.setCategoryLevel(2);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Category> categoryList = categoryService.selectByLikeCondition(categoryQuery);

		resultMap.put("itemList", categoryList);
		resultMap.put("item", categoryQuery);
		
		context.put("resultMap", resultMap);

		return "item/category2";
	}
	
	@RequestMapping(value="/category3", method={ RequestMethod.GET, RequestMethod.POST })
	public String category3(Integer page, CategoryQuery categoryQuery,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		if(page==null){
			categoryQuery.setPageNo(1);
		}else{
			categoryQuery.setPageNo(page);
		}
		
		//设置查询几级分类
		categoryQuery.setCategoryLevel(3);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Category> categoryList = categoryService.selectByLikeCondition(categoryQuery);

		resultMap.put("itemList", categoryList);
		resultMap.put("item", categoryQuery);
		
		context.put("resultMap", resultMap);

		return "item/category3";
	}
	
	@RequestMapping(value="/category4", method={ RequestMethod.GET, RequestMethod.POST })
	public String category4(Integer page, CategoryQuery categoryQuery,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		if(page==null){
			categoryQuery.setPageNo(1);
		}else{
			categoryQuery.setPageNo(page);
		}
		
		//设置查询几级分类
		categoryQuery.setCategoryLevel(4);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Category> categoryList = categoryService.selectByLikeCondition(categoryQuery);

		resultMap.put("itemList", categoryList);
		resultMap.put("item", categoryQuery);
		
		context.put("resultMap", resultMap);

		return "item/category4";
	}

	@RequestMapping(value="/addCategory1", method={ RequestMethod.GET, RequestMethod.POST })
	public String addCategory1(Category category, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		//category.setParentCategoryId(null);
		//category.setCategoryLevel(1);
		category.setYn(1);
		if(category.getSortNumber()!=null){
			category.setSortNumber(category.getSortNumber());
		}	
		try {
			categoryService.insert(category);
		} catch (Exception e) {
			LOG.error("CategoryController.addProVal===", e);
		}
		
		try {
			//跳转到显示界面
			response.sendRedirect("/category/category"+category.getCategoryLevel());
		} catch (IOException e) {
			
			LOG.error("CategoryController.addCategory1:", e);
			//e.printStackTrace();
		}

		return "item/category"+category.getCategoryLevel();
	}
	
	@RequestMapping(value="/addCategory4", method={ RequestMethod.GET, RequestMethod.POST })
	public String addCategory4(Category category, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		category.setCategoryLevel(4);
		category.setYn(1);
		if(category.getSortNumber()!=null){
			category.setSortNumber(category.getSortNumber());
		}
		
		//传来的catalogID
		try {
			Integer categoryId = categoryService.insert(category);
			if(category.getIfHaveSaleProperty()==1){//如果有销售属性
				Property property =new Property();
				property.setCategoryId(categoryId);
				property.setSortNumber(1);
				property.setPropertyName("规格");
				property.setYn(1);
				property.setPropertyType(3);
				propertyService.insert(property);	
			}
		} catch (Exception e) {
			LOG.error("CategoryController.addCategory4:", e);
		}

		try {
			//跳转到显示界面
			response.sendRedirect("/category/category4");
		} catch (IOException e) {

		}
		
		return "item/category4";
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
					LOG.error("CategoryController.uploaImage:", e);
				} catch (IOException e) {
					LOG.error("CategoryController.uploaImage:", e);
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

