package com.atu.erp.web.controller;

import java.util.ArrayList;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atu.erp.common.utils.CookieUtil;
import com.atu.erp.domain.BusinessUserExt;
import com.atu.erp.domain.Category;
import com.atu.erp.domain.Item;
import com.atu.erp.domain.ItemDescription;
import com.atu.erp.domain.ItemImage;
import com.atu.erp.domain.PromotionInfo;
import com.atu.erp.domain.Sku;
import com.atu.erp.domain.query.ItemQuery;
import com.atu.erp.domain.query.SkuQuery;
import com.atu.erp.service.BusinessUserExtService;
import com.atu.erp.service.CategoryService;
import com.atu.erp.service.ItemDescriptionService;
import com.atu.erp.service.ItemImageService;
import com.atu.erp.service.ItemService;
import com.atu.erp.service.PromotionInfoService;
import com.atu.erp.service.SkuService;
import com.atu.erp.web.base.BaseController;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {
	@Autowired
	private PromotionInfoService promotionInfoService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private SkuService skuService;
	@Autowired	
	private ItemImageService itemImageService;
	@Autowired
	private ItemDescriptionService itemDescriptionService;
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BusinessUserExtService businessUserExtService;
	
	private final static Log LOG = LogFactory.getLog(ProductController.class);
	
	@RequestMapping(value="/detail", method={ RequestMethod.GET, RequestMethod.POST })
	public String queryPromotion(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){

		return "product/product";
	}
	/**
	 * 编辑商品详情
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/edit", method={ RequestMethod.GET, RequestMethod.POST })
	public String edit(Integer itemId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		try {
			//查询商品信息
			Item item = itemService.selectByItemId(itemId);
			
			//查询分类ID对应的分类
			Category category1 = categoryService.selectByCategoryId(item.getCategoryId1());
			Category category2 = categoryService.selectByCategoryId(item.getCategoryId2());
			Category category3 = categoryService.selectByCategoryId(item.getCategoryId3());
			Category category4 = categoryService.selectByCategoryId(item.getCategoryId4());
			
			//查询sku信息
			SkuQuery skuQuery =new SkuQuery();
			skuQuery.setItemId(item.getItemId());
			skuQuery.setYn(1);
			List<Sku> skuList = skuService.selectByCondition(skuQuery);
			
			//查询商品图片信息
			List<ItemImage> itemImageList = itemImageService.selectByItemId(itemId);
			for(int i=0; i<itemImageList.size();i++){
				context.put("imageUrl"+(i+1), itemImageList.get(i).getImageUrl());
				context.put("imageUrlId"+(i+1), itemImageList.get(i).getId());
			}
			context.put("imageUrl0", item.getItemImage());
			
			//查询商品详情
			ItemDescription itemDescription =itemDescriptionService.selectByItemId(itemId);
			
			//商品无销售属性
			if(category4.getIfHaveSaleProperty()==0){
				context.put("tbPriceNoPro", skuList.get(0).getTbPrice());
				context.put("costPriceNoPro", skuList.get(0).getCostPrice());
				context.put("stockNoPro", skuList.get(0).getStock());
				context.put("minSaleNumNoPro", skuList.get(0).getLeastBuy());
				context.put("barCodeNoPro", skuList.get(0).getBarCode());
				
				context.put("proValIdList", "null");
				context.put("tbPriceList", "null");
				context.put("costPriceList", "null");
				context.put("stockList", "null");
				context.put("leastBuyList", "null");
				context.put("barCodeList", "null");
				
			}else if(category4.getIfHaveSaleProperty() == 1){//有销售属性
				List<Integer> proValIdList = new ArrayList();
				List<Double> costPriceList = new ArrayList();
				List<Double> tbPriceList = new ArrayList();
				List<Integer> stockList = new ArrayList();
				List<Integer> leastBuyList = new ArrayList();
				List<String> barCodeList = new ArrayList();
				for(int i=0; i<skuList.size(); i++){
					Sku sku = skuList.get(i);
					String propert = sku.getSalesProperty();
					String proValIDString = propert.substring(
							propert.indexOf(":")+1, propert.indexOf("^"));
					proValIdList.add(Integer.parseInt(proValIDString));
					costPriceList.add(sku.getCostPrice());
					tbPriceList.add(sku.getTbPrice());
					stockList.add(sku.getStock());
					leastBuyList.add(sku.getLeastBuy());
					barCodeList.add(sku.getBarCode());
				}
				context.put("proValIdList", proValIdList);
				context.put("costPriceList", costPriceList);
				context.put("tbPriceList", tbPriceList);
				context.put("stockList", stockList);
				context.put("leastBuyList", leastBuyList);
				context.put("barCodeList", barCodeList);
			}
			
			context.put("item", item);
			context.put("category1", category1);
			context.put("category2", category2);
			context.put("category3", category3);
			context.put("category4", category4);
			context.put("skuList", skuList);
			context.put("itemImageList", itemImageList);
			context.put("itemDescription", itemDescription);
		} catch (Exception e) {
			LOG.error("ProductController.edit:", e);
		}
		return "product/editProduct";
	}
	/**
	 * 上架
	 * @param itemId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/startSale", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> startSale(Integer itemId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Item item =new Item();
		item.setItemId(itemId);
		//item.setVenderUserId(CookieUtil.getUserId(reuqest));
		item.setItemStatus(1);
		item.setOnShelfTime(new Date());
		item.setYn(1);
		try {
			itemService.modify(item);
		} catch (Exception e) {
			LOG.error("Product.startSale:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		//context.put("resultMap", resultMap);
		resultMap.put("msg","success");
		return resultMap;
	}
	/**
	 * 下架
	 * @param itemId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/offSale", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> offSale(Integer itemId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Item item =new Item();
		item.setItemId(itemId);
		item.setOffShelfTime(new Date());
		//item.setVenderUserId(CookieUtil.getUserId(reuqest));
		item.setItemStatus(2);//2下架
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			itemService.modify(item);
		} catch (Exception e) {
			LOG.error("Product.offSale:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		//context.put("resultMap", resultMap);
		resultMap.put("msg","success");
		return resultMap;
	}

	@RequestMapping("/list")
	public @ResponseBody Map<String, Object> list(PromotionInfo promotionInfo, Integer page, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {	
			promotionInfo.setPageIndex(page);
			resultMap = promotionInfoService.queryPromotionList(promotionInfo);
		} catch (Exception e) {
		}
		return resultMap;
	}

	/**
	 * 上架商品列表
	 * @param page
	 * @param itemQuery
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/onSaleProduct", method={ RequestMethod.GET, RequestMethod.POST })
	public String onSaleProduct(ItemQuery itemQuery,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		
		itemQuery.setItemStatus(1);	//0：待售商品，1：上架 2：下架	
		//itemQuery.setVenderUserId(CookieUtil.getUserId(reuqest));//设置商家ID
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(itemQuery.getPageNo()==null){
				itemQuery.setPageNo(1);
			}
			resultMap = itemService.queryItemList(itemQuery);
		} catch (Exception e) {
			LOG.error("Product.onSaleProduct:", e);
		}
		context.put("resultMap", resultMap);
		return "product/onSaleProduct";
	}
	/**
	 * 待售商品列表
	 * @param page
	 * @param itemQuery
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/preSaleProduct", method={ RequestMethod.GET, RequestMethod.POST })
	public String preSaleProduct(Integer page, ItemQuery itemQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			if(page==null){
				itemQuery.setPageNo(1);
			}else{
				itemQuery.setPageNo(page);
			}
			
			itemQuery.setItemStatus(0);	//0：待售商品，1：上架 2：下架
		
			resultMap = itemService.queryItemList(itemQuery);
		} catch (Exception e) {
			LOG.error("Product.preSaleProduct:", e);
		}
		context.put("resultMap", resultMap);
		return "product/preSaleProduct";
	}
	/**
	 * 下架商品列表
	 * @param page
	 * @param itemQuery
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/stopSaleProduct", method={ RequestMethod.GET, RequestMethod.POST })
	public String stopSaleProduct(ItemQuery itemQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		
		itemQuery.setItemStatus(2);	//0：待售商品，1：上架 2：下架
		//itemQuery.setVenderUserId(CookieUtil.getUserId(reuqest));//设置商家ID
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(itemQuery.getPageNo()==null){
				itemQuery.setPageNo(1);
			}
			resultMap = itemService.queryItemList(itemQuery);
		} catch (Exception e) {
			LOG.error("Product.stopSaleProduct:", e);
		}
		context.put("resultMap", resultMap);
		return "product/stopSaleProduct";
	}
	
	
	
	/**
	 * 修改商品
	 */
	@RequestMapping(value="/modifyProduct", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> modifyProduct(Item item,Integer hasPropertyInput, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//修改商品
		try {
			String itemImage=reuqest.getParameter("imageMainUrl");//主图URL
			item.setItemType(2);// 第三方商品为2
			// TODO 通过取cooke获取商家ID
			item.setItemStatus(0);//新创建
			item.setYn(0);//待售商品为无效
			item.setItemImage(itemImage);
			Item item2 = itemService.selectByItemId(item.getItemId());
			item.setVenderUserId(item2.getVenderUserId());
			itemService.modify(item);//修改商品
			//itemId = itemService.insert(item);
			LOG.error("修改商品ID成功："+item.getItemId());
		} catch (Exception e) {
			LOG.error("product.modifyProduct 修改商品:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		
		//修改细节图片（根据细节图ID修改）
		ItemImage itemImage=new ItemImage();
		String[] imageUrlArray=reuqest.getParameterValues("imageUrl");
		String[] imageUrlIdArray=reuqest.getParameterValues("imageUrlId");
		try {
			for(int i=0; i<imageUrlArray.length;i++){
				itemImage.setId(Integer.parseInt(imageUrlIdArray[i]));
				itemImage.setImageUrl(imageUrlArray[i]);
				itemImage.setItemId(item.getItemId());
				itemImage.setSortNumber(i);
				itemImage.setYn(1);
				itemImageService.modify(itemImage);//修改细节图
				LOG.error("修改商品细节图成功ID为："+itemImage.getId());
			}
		} catch (Exception e) {
			LOG.error("product.modifyProduct 修改细节图:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		
		//修改商品描述(根据 itemId修改)
		String pcDescriptionInfo= reuqest.getParameter("pcDescriptionInfo");
		String appDescriptionInfo= reuqest.getParameter("appDescriptionInfo");
		ItemDescription itemDescription=new ItemDescription();
		itemDescription.setAppDescriptionInfo(appDescriptionInfo);
		itemDescription.setPcDescriptionInfo(pcDescriptionInfo);
		itemDescription.setItemId(item.getItemId());
		try {
			itemDescriptionService.modify(itemDescription);
		} catch (Exception e) {
			LOG.error("product.modifyProduct 修改商品描述:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		

		
		Sku sku=new Sku();
		//无销售属性(根据itemId修改SKU信息)
		if(hasPropertyInput.equals(0)){
			String costPriceNoPro= reuqest.getParameter("costPriceNoPro");
			String tbPriceNoPro= reuqest.getParameter("tbPriceNoPro");
			String stockNoPro= reuqest.getParameter("stockNoPro");
			String minSaleNumNoPro= reuqest.getParameter("minSaleNumNoPro");
			String barCodeNpPro= reuqest.getParameter("barCodeNpPro");
			sku.setItemId(item.getItemId());
			sku.setCostPrice(Double.parseDouble(costPriceNoPro));
			sku.setTbPrice(Double.parseDouble(tbPriceNoPro));
			sku.setStock(Integer.parseInt(stockNoPro));
			sku.setLeastBuy(Integer.parseInt(minSaleNumNoPro));
			sku.setBarCode(barCodeNpPro);
			sku.setYn(1);//插入即是有效
			Integer skuId = skuService.insert(sku);//插入sku	
			skuService.modifyByItemId(sku);
			LOG.error("修改SKU成功ItemId："+item.getItemId());
			resultMap.put("msg","success");
			return resultMap;
		}
		
		//如果有销售属性（根据ItemId和销售属性修改）
		
		//现将所有的sku设为无效，后续进行插入更新操作（选中的更新为有效，新选中的新建插入）
		Sku sku2= new Sku();
		sku2.setItemId(item.getItemId());
		sku2.setYn(0);
		skuService.modifyByItemId(sku2);
		
		
		String  salesPropertyItem="";//item中的销售属性
		String[] costPriceArray=reuqest.getParameterValues("costPrice");
		String[] tbPriceArray=reuqest.getParameterValues("tbPrice");
		String[] stockArray=reuqest.getParameterValues("stock");
		String[] leastBuyArray=reuqest.getParameterValues("leastBuy");
		String[] barCodeArray=reuqest.getParameterValues("barCode");
		
		String[] propertyIdArray=reuqest.getParameterValues("propertyId");
		String[] propertyValueIdArray=reuqest.getParameterValues("propertyValueId");
		String[] ifChooseArray=reuqest.getParameterValues("ifChoose");
		
		
		salesPropertyItem=propertyIdArray[0]+":";
		
		for(int i=0; i<ifChooseArray.length;i++){
			if(StringUtils.isBlank(ifChooseArray[i])||ifChooseArray[i].equals("0")){
				//不添加
			}else{//ifChoose为1时表示已选
				sku.setItemId(item.getItemId());
				sku.setCostPrice(Double.parseDouble(costPriceArray[i]));
				sku.setTbPrice(Double.parseDouble(tbPriceArray[i]));
				sku.setStock(Integer.parseInt(stockArray[i]));
				sku.setLeastBuy(Integer.parseInt(leastBuyArray[i]));
				sku.setBarCode(barCodeArray[i]);
				sku.setYn(1);//插入即是有效
				String  salesProperty=propertyIdArray[i]+":"+propertyValueIdArray[i]+"^";
				salesPropertyItem=salesPropertyItem+propertyValueIdArray[i]+",";//item中的属性值ID
				sku.setSalesProperty(salesProperty);
				skuService.insertOrUpdate(sku);
				//Integer skuId = skuService.insert(sku);//插入sku	
				//LOG.error("生成SKU成功："+skuId);
			}
		}
		Item item2=new Item();
		item2.setItemId(item.getItemId());
		item2.setSalesPropertySet(salesPropertyItem+"^");
		try {
			itemService.modify(item2);
		} catch (Exception e) {
			LOG.error("更新Item销售属性异常", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		context.put("item", item);
		resultMap.put("msg","success");
		return resultMap;
	}
	
	
	
	/**
	 * 添加商品，成功跳转到预售商品列表
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/addProduct", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addProduct(Item item,Integer hasPropertyInput,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Integer itemId=null;	
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			LOG.error("开始添加商品！");
			
			String itemImage=reuqest.getParameter("imageMainUrl");//主图URL
			item.setItemType(2);// 第三方商品为2
			// TODO通过取cooke获取商家ID
			item.setVenderUserId(item.getVenderUserId());
			item.setItemStatus(0);//新创建
			item.setYn(0);//待售商品为无效
			item.setItemImage(itemImage);

			BusinessUserExt userExt=businessUserExtService.selectByUserId(CookieUtil.getUserId(reuqest));
			item.setVenderUserId(userExt.getId());//设置商家ID
			itemId = itemService.insert(item);
			LOG.error("生成商品ID成功："+itemId);
		} catch (Exception e) {
			LOG.error("product.addProduct:", e);
			resultMap.put("msg","error");
		}
		
		//细节图片地址入库
		ItemImage itemImage=new ItemImage();
		String[] imageUrlArray=reuqest.getParameterValues("imageUrl");
		try {
			for(int i=0; i<imageUrlArray.length;i++){
				itemImage.setImageUrl(imageUrlArray[i]);
				itemImage.setItemId(itemId);
				itemImage.setSortNumber(i);
				itemImage.setYn(1);
				itemImageService.insert(itemImage);
				
			}
		} catch (Exception e) {
			LOG.error("product.addProduct:", e);
			resultMap.put("msg","error");
		}
		
		//商品描述入库
		String pcDescriptionInfo= reuqest.getParameter("pcDescriptionInfo");
		String appDescriptionInfo= reuqest.getParameter("appDescriptionInfo");
		ItemDescription itemDescription=new ItemDescription();
		itemDescription.setAppDescriptionInfo(appDescriptionInfo);
		itemDescription.setPcDescriptionInfo(pcDescriptionInfo);
		itemDescription.setItemId(itemId);
		try {
			itemDescriptionService.insert(itemDescription);
		} catch (Exception e) {
			LOG.error("product.addProduct:", e);
			resultMap.put("msg","error");
		}
		

		
		Sku sku=new Sku();
		//无销售属性
		if(hasPropertyInput.equals(0)){
			String costPriceNoPro= reuqest.getParameter("costPriceNoPro");
			String tbPriceNoPro= reuqest.getParameter("tbPriceNoPro");
			String stockNoPro= reuqest.getParameter("stockNoPro");
			String minSaleNumNoPro= reuqest.getParameter("minSaleNumNoPro");
			String barCodeNpPro= reuqest.getParameter("barCodeNpPro");
			sku.setItemId(itemId);
			sku.setCostPrice(Double.parseDouble(costPriceNoPro));
			sku.setTbPrice(Double.parseDouble(tbPriceNoPro));
			sku.setStock(Integer.parseInt(stockNoPro));
			sku.setLeastBuy(Integer.parseInt(minSaleNumNoPro));
			sku.setBarCode(barCodeNpPro);
			sku.setYn(1);//插入即是有效
			Integer skuId = skuService.insert(sku);//插入sku	
			LOG.error("生成SKU成功："+skuId);
			resultMap.put("msg","success");
			return resultMap;
		}
		
		//如果有销售属性
		
		String  salesPropertyItem="";//item中的销售属性
		String[] costPriceArray=reuqest.getParameterValues("costPrice");
		String[] tbPriceArray=reuqest.getParameterValues("tbPrice");
		String[] stockArray=reuqest.getParameterValues("stock");
		String[] leastBuyArray=reuqest.getParameterValues("leastBuy");
		String[] barCodeArray=reuqest.getParameterValues("barCode");
		
		String[] propertyIdArray=reuqest.getParameterValues("propertyId");
		String[] propertyValueIdArray=reuqest.getParameterValues("propertyValueId");
		String[] ifChooseArray=reuqest.getParameterValues("ifChoose");
		
		
		salesPropertyItem=propertyIdArray[1]+":";
		
		for(int i=0; i<ifChooseArray.length;i++){
			if(StringUtils.isBlank(ifChooseArray[i])||ifChooseArray[i].equals("0")){
				//不添加
			}else{
				sku.setItemId(itemId);
				sku.setCostPrice(Double.parseDouble(costPriceArray[i]));
				sku.setTbPrice(Double.parseDouble(tbPriceArray[i]));
				sku.setStock(Integer.parseInt(stockArray[i]));
				sku.setLeastBuy(Integer.parseInt(leastBuyArray[i]));
				sku.setBarCode(barCodeArray[i]);
				sku.setYn(1);//插入即是有效
				String  salesProperty=propertyIdArray[i]+":"+propertyValueIdArray[i]+"^";
				salesPropertyItem=salesPropertyItem+propertyValueIdArray[i]+",";//item中的属性值ID
				sku.setSalesProperty(salesProperty);
				Integer skuId = skuService.insert(sku);//插入sku	
				LOG.error("生成SKU成功："+skuId);
			}
		}
		Item item2=new Item();
		item2.setItemId(itemId);
		item2.setSalesPropertySet(salesPropertyItem+"^");
		try {
			itemService.modify(item2);
		} catch (Exception e) {
			LOG.error("更新Item销售属性异常", e);
			resultMap.put("msg","error");
		}
		context.put("item", item);
		resultMap.put("msg","success");
		//resultMap.put("propertyValue",propertyValue);
		
		return resultMap;
	}
	
}

