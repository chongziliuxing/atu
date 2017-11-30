package com.atu.erp.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import com.atu.erp.domain.IndexRecommendItem;
import com.atu.erp.domain.query.IndexRecommendItemQuery;
import com.atu.erp.service.IndexRecommendItemService;

@Controller
@RequestMapping("/appimagerecommend")
public class AppImageRecommendController {

	private final static Log LOG = LogFactory.getLog(AppImageRecommendController.class);
	@Autowired
	private IndexRecommendItemService indexRecommendItemService;
	
	@RequestMapping(value="/addImg", method={ RequestMethod.GET, RequestMethod.POST })
	public String add(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		return "appimage/recommendItem";
	}
	
	@RequestMapping(value="/edit", method={ RequestMethod.GET, RequestMethod.POST })
	public String edit(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		return "appimage/recommendItem";
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
		resultMap.put("recommendImgUrl", savefilePath);	
		
		String jsonStr = "{\"msg\":\"success\",\"recommendImgUrl\":\""+savefilePath+"\"}";
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonStr);
		writer.print(jsonStr);
		return null;
	}
	
	
	@RequestMapping(value="/addImage", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addImage(IndexRecommendItem indexRecommendItem, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			indexRecommendItem.setYn(1);
			indexRecommendItem.setRecommendType(1);
			indexRecommendItemService.insert(indexRecommendItem);
		} catch (Exception e) {
			LOG.error("appimagerecommend.addImage:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	
	
	@RequestMapping(value="/getIndexImage", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> getIndexImage(HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		IndexRecommendItemQuery indexRecommendItemQuery =new IndexRecommendItemQuery();
		List<IndexRecommendItem> indexRecommendItemList = new ArrayList<IndexRecommendItem>();
		indexRecommendItemQuery.setYn(1);
		try {	
			indexRecommendItemList = indexRecommendItemService.selectByCondition(indexRecommendItemQuery);
			
		} catch (Exception e) {
			LOG.error("CategoryController.getIndexImage:", e);
		}
		resultMap.put("success","success");
		resultMap.put("indexRecommendItemList",indexRecommendItemList);
		return resultMap;
	}
	
	
	@RequestMapping(value="/deletIndexImage", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> deletIndexImage(Integer id, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		IndexRecommendItem indexRecommendItem = new IndexRecommendItem();
		try {	
			if(id!=null){
				indexRecommendItem.setId(id);
				indexRecommendItem.setYn(0);
				indexRecommendItemService.modify(indexRecommendItem);
			}
			resultMap.put("msg","success");
		} catch (Exception e) {
			LOG.error("CategoryController.deletProVal===", e);
		}
		return resultMap;
	}
	
	@RequestMapping(value="/modifyIndexImage", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> modifyIndexImage(Integer id,Integer sortNum, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		IndexRecommendItem indexRecommendItem = new IndexRecommendItem();
		try {	
			if(id!=null){
				indexRecommendItem.setId(id);
			}
			if(sortNum!=null){
				indexRecommendItem.setSortNum(sortNum);	
			}
			indexRecommendItemService.modify(indexRecommendItem);
			resultMap.put("msg","success");
		} catch (Exception e) {
			LOG.error("CategoryController.deletProVal===", e);
		}
		return resultMap;
	}

	
}