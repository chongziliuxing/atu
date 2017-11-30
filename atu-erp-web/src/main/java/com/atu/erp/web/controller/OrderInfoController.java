package com.atu.erp.web.controller;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atu.erp.common.utils.PaginatedList;
import com.atu.erp.common.utils.StatusUtil;
import com.atu.erp.domain.OrderDetail;
import com.atu.erp.domain.OrderInfo;
import com.atu.erp.domain.PaymentInfo;
import com.atu.erp.domain.query.OrderInfoQuery;
import com.atu.erp.service.AddressService;
import com.atu.erp.service.OrderInfoService;
import com.atu.erp.service.PaymentInfoService;
import com.atu.erp.service.SellerEntryService;
import com.atu.erp.service.result.Result;
import com.atu.erp.web.base.BaseController;

@Controller
@RequestMapping("/orderInfo")
public class OrderInfoController extends BaseController{

	private final static Log log = LogFactory.getLog(OrderInfoController.class);
	
	private OrderInfoService orderInfoService;
	private SellerEntryService sellerEntryService;
	private PaymentInfoService paymentInfoService;
	private AddressService addressService;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	
	@InitBinder
	public void initDateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	/**
	 * 订单列表页面
	 * @param orderInfoQuery
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/index", method={ RequestMethod.GET, RequestMethod.POST })
	public String index(OrderInfoQuery orderInfoQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		orderInfoQuery.setVenderFlag(1);//所有拆分过的订单
		PaginatedList<OrderInfo> list = orderInfoService.getOrderInfosByPage(orderInfoQuery);
		context.put("list", list);
		context.put("query", orderInfoQuery);
		return "/orderInfo/index";
	}
	
	/**
	 * 订单详情
	 * @param orderId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/detail", method={ RequestMethod.GET, RequestMethod.POST })
	public String detail(Integer orderId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
		orderInfoQuery.setOrderId(orderId);
		
		resultMap = orderInfoService.selectOrderDetail(orderInfoQuery);
		
		context.put("resultMap", resultMap);
		return "/orderInfo/detail";
	}
	
	
	/**
	 * 线下确认收款
	 * @param orderId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/confirmMoney", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> confirmMoney(Integer orderId,String paymentNumber,Date payTime, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		return orderInfoService.confirmMoney(orderId, paymentNumber,payTime);
	}
	
	
	/**
	 * 线下支付
	 * @param orderId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/linePayment", method={ RequestMethod.GET, RequestMethod.POST })
	public String linePayment(Integer orderId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		context.put("orderId", orderId);
		return "/orderInfo/payment";
	}
	
	/**
	 * 结算页面用到的订单导出excel
	 * @param balanceId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/excelOrderListForBalance", method={ RequestMethod.GET, RequestMethod.POST })
	public void excelOrderListForBalance(OrderInfoQuery orderInfoQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		try{
			List<OrderInfo> list = this.orderInfoService.selectByCondition(orderInfoQuery);
			if(list == null){
				list = new ArrayList<OrderInfo>();
			}
			
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet("结算订单明细");
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow((int) 0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
			
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellValue("订单编号");
			cell.setCellStyle(style);
			cell = row.createCell((short) 1);
			cell.setCellValue("下单时间");
			cell.setCellStyle(style);
			cell = row.createCell((short) 2);
			cell.setCellValue("计费时间");
			cell.setCellStyle(style);
			cell = row.createCell((short) 3);
			cell.setCellValue("总货款");
			cell.setCellStyle(style);
			cell = row.createCell((short) 4);
			cell.setCellValue("佣金");
			cell.setCellStyle(style);

			cell.setCellStyle(style);
			for(int i=0;i<list.size();i++){
				row = sheet.createRow((int) i + 1);
				OrderInfo orderInfo = (OrderInfo) list.get(i);
				// 第四步，创建单元格，并设置值
				row.createCell((short) 0).setCellValue(orderInfo.getOrderId());
				row.createCell((short) 1).setCellValue(sdf.format(orderInfo.getOrderTime()));
				row.createCell((short) 2).setCellValue(sdf.format(orderInfo.getPayTime()));
				row.createCell((short) 3).setCellValue(""+orderInfo.getBigDecimalOrderPrice());
				
				row.createCell((short) 4).setCellValue(orderInfo.getCommission() != null ? orderInfo.getCommission().doubleValue() : 0);
			}
			
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("Content-disposition", "attachment;filename="+sdf2.format(new Date())+".xls");
			wb.write(response.getOutputStream());
		}catch (Exception e) {
			log.error("", e);
		}
	}
	

	/**
	 * 订单列表导出excel
	 * @param balanceId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/excel", method={ RequestMethod.GET, RequestMethod.POST })
	public void excel(OrderInfoQuery orderInfoQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		try{
			List<OrderInfo> list = this.orderInfoService.selectByCondition(orderInfoQuery);
			if(list == null){
				list = new ArrayList<OrderInfo>();
			}
			
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet("订单列表");
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow((int) 0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
			
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellValue("订单编号");
			cell.setCellStyle(style);
			
			cell = row.createCell((short) 1);
			cell.setCellValue("订单状态");
			cell.setCellStyle(style);
			
			cell = row.createCell((short) 2);
			cell.setCellValue("商品名称");
			cell.setCellStyle(style);
			
			cell = row.createCell((short) 3);
			cell.setCellValue("商品属性");
			cell.setCellStyle(style);
			
			cell = row.createCell((short) 4);
			cell.setCellValue("商品数量");
			cell.setCellStyle(style);
			
			cell = row.createCell((short) 5);
			cell.setCellValue("订单金额");
			cell.setCellStyle(style);
			
			cell = row.createCell((short) 6);
			cell.setCellValue("优惠金额");
			cell.setCellStyle(style);
			
			cell = row.createCell((short) 7);
			cell.setCellValue("实付金额");
			cell.setCellStyle(style);
			
			cell = row.createCell((short) 8);
			cell.setCellValue("买家ID");
			cell.setCellStyle(style);
			
			cell = row.createCell((short) 9);
			cell.setCellValue("商家ID");
			cell.setCellStyle(style);
			
			cell = row.createCell((short) 10);
			cell.setCellValue("收货人");
			cell.setCellStyle(style);
			
			cell = row.createCell((short) 11);
			cell.setCellValue("收货人手机号");
			cell.setCellStyle(style);
			
			cell = row.createCell((short) 12);
			cell.setCellValue("收货人地址");
			
			cell = row.createCell((short) 13);
			cell.setCellValue("下单时间");
			cell.setCellStyle(style);
			
			cell = row.createCell((short) 14);
			cell.setCellValue("付款时间");
			cell.setCellStyle(style);
			int rowNum=0;
			for(int i=0;i<list.size();i++){

				OrderInfo orderInfo = (OrderInfo) list.get(i);

				//商品信息
				if(orderInfo.getOrderDetail()!=null){
				for(OrderDetail orderDetail:orderInfo.getOrderDetail()){
					rowNum++;
					row = sheet.createRow(rowNum);
					// 第四步，创建单元格，并设置值
					row.createCell((short) 0).setCellValue(orderInfo.getOrderId());
					row.createCell((short) 1).setCellValue(StatusUtil.getOrderStatusName(orderInfo.getOrderStatus()));
					String itemName=orderDetail.getItemName();
					row.createCell((short) 2).setCellValue(itemName);
					String propertyName="";
					if(orderDetail.getSalesPropertyNameList()!=null){
					for(String salesPropertyName :orderDetail.getSalesPropertyNameList()){
						propertyName+=salesPropertyName;
					}
					}
					row.createCell((short) 3).setCellValue(propertyName);
					Integer num=orderDetail.getNum();
					row.createCell((short) 4).setCellValue(num);

				row.createCell((short) 5).setCellValue(""+orderDetail.getPrice());
				row.createCell((short) 6).setCellValue(""+orderInfo.getDiscountMoney());
				row.createCell((short) 7).setCellValue(""+orderInfo.getPayMoney());
				row.createCell((short) 8).setCellValue(orderInfo.getUserId());
				if(orderInfo.getVenderUserId()!=null){
					row.createCell((short) 9).setCellValue(orderInfo.getVenderUserId());
				}else {
					row.createCell((short) 9).setCellValue("");	
				}
				row.createCell((short) 10).setCellValue(orderInfo.getConsigneeName());
				row.createCell((short) 11).setCellValue(orderInfo.getConsigneeMobile());
				row.createCell((short) 12).setCellValue(orderInfo.getConsigneeAddress());
				
				row.createCell((short) 13).setCellValue(sdf.format(orderInfo.getOrderTime()));
				if(orderInfo.getPayTime()!=null){
					row.createCell((short) 14).setCellValue(sdf.format(orderInfo.getPayTime()));
				}else {
					row.createCell((short) 14).setCellValue("");	
				}
				}
				}
			}
			
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("Content-disposition", "attachment;filename="+sdf2.format(new Date())+".xls");
			wb.write(response.getOutputStream());
		}catch (Exception e) {
			log.error("", e);
		}
	}
	
	
	/**
	 * 锁定订单弹层
	 * @param orderId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/lockOrder", method={ RequestMethod.GET, RequestMethod.POST })
	public String lockOrder(Integer orderId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		context.put("orderId", orderId);
		return "/orderInfo/lockOrder";
	}
	
	/**
	 * 补录金额弹层
	 * @param orderId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/entry", method={ RequestMethod.GET, RequestMethod.POST })
	public String entry(Integer orderId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		context.put("orderId", orderId);
		return "/orderInfo/entry";
	}
	
	/**
	 * 收款确认弹层
	 * @param orderId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	/*@RequestMapping(value="/detail", method={ RequestMethod.GET, RequestMethod.POST })
	public String detail(Integer orderId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
		orderInfoQuery.setOrderId(orderId);
		OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderId(orderInfoQuery);
		String address = addressService.getDetailConsigneeAddress(orderInfo.getConsigneeProvince(), orderInfo.getConsigneeCity(), orderInfo.getConsigneeCounty(), orderInfo.getConsigneeAddress());
		
		context.put("orderInfo", orderInfo);
		context.put("address", address);
		PaymentInfo paymentInfo1 = paymentInfoService.getPaymentInfoByOrderId(orderId, 1);//获取全款或者订金支付金额
		context.put("paymentInfo1", paymentInfo1);//全款或者定金支付金额
		PaymentInfo paymentInfo2 = paymentInfoService.getPaymentInfoByOrderId(orderId, 2);//获取全款或者订金支付金额
		context.put("paymentInfo2", paymentInfo2);//尾款支付金额
		context.put("zaixianSellEntryPrice", sellerEntryService.selectSumPayMoneyByCondition(orderId, 1));//补录金额
		context.put("weikuanSellEntryPrice", sellerEntryService.selectSumPayMoneyByCondition(orderId, 2));//尾款补录金额
		return "/orderInfo/detail";
	}*/
	
	/**
	 * 收款确认弹层
	 * @param orderId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	/*@RequestMapping(value="/confirmGetPrice", method={ RequestMethod.GET, RequestMethod.POST })
	public String confirmGetPrice(Integer orderId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
		orderInfoQuery.setOrderId(orderId);
		OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderId(orderInfoQuery);
		PaymentInfo paymentInfo = paymentInfoService.getPaymentInfoByOrderId(orderId, 1);//获取全款或者订金支付金额
		
		context.put("paymentInfo", paymentInfo);
		context.put("orderInfo", orderInfo);
		context.put("sellerEntryMoney", sellerEntryService.selectSumPayMoneyByCondition(orderId, 1));
		return "/orderInfo/confirmGetPrice";
	}*/
	
	/**
	 * 尾款确认弹层
	 * @param orderId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	/*@RequestMapping(value="/confirmGetLastPrice", method={ RequestMethod.GET, RequestMethod.POST })
	public String confirmGetLastPrice(Integer orderId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
		orderInfoQuery.setOrderId(orderId);
		OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderId(orderInfoQuery);
		
		context.put("orderInfo", orderInfo);
		PaymentInfo paymentInfo1 = paymentInfoService.getPaymentInfoByOrderId(orderId, 1);//获取全款或者订金支付金额
		context.put("paymentInfo1", paymentInfo1);//全款或者定金支付金额
		PaymentInfo paymentInfo2 = paymentInfoService.getPaymentInfoByOrderId(orderId, 2);//获取全款或者订金支付金额
		context.put("paymentInfo2", paymentInfo2);//尾款支付金额
		context.put("zaixianSellEntryPrice", sellerEntryService.selectSumPayMoneyByCondition(orderId, 1));//补录金额
		context.put("weikuanSellEntryPrice", sellerEntryService.selectSumPayMoneyByCondition(orderId, 2));//尾款补录金额
		return "/orderInfo/confirmGetLastPrice";
	}*/
	
	/**
	 * 收款确认
	 * @param orderId
	 * @param remark
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/doConfirmGetPrice", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> doConfirmGetPrice(Integer orderId, Integer venderUserId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> map = new HashMap<String, Object>();
		if(orderId == null){
			map.put("success", false);
			map.put("message", "订单号不能为空");
			return map;
		}
		
		//TODO 需要改为从cookie中获取 venderUserId
		return orderInfoService.confirmGetPrice(orderId, venderUserId);
	}
	
	/**
	 * 收款确认
	 * @param orderId
	 * @param remark
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/doFinish", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> doFinish(Integer orderId, Integer venderUserId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> map = new HashMap<String, Object>();
		if(orderId == null){
			map.put("success", false);
			map.put("message", "订单号不能为空");
			return map;
		}
		
		//TODO 需要改为从cookie中获取 venderUserId
		return orderInfoService.updateOrderInfoFinish(orderId, venderUserId);
	}
	
	/**
	 * 尾款收款确认
	 * @param orderId
	 * @param remark
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/doConfirmGetLastPrice", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> doConfirmGetLastPrice(Integer orderId, Integer venderUserId, String remark, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> map = new HashMap<String, Object>();
		if(orderId == null){
			map.put("success", false);
			map.put("message", "订单号不能为空");
			return map;
		}
		
		return orderInfoService.confirmGetLastPrice(orderId, venderUserId);
	}
	
	/**
	 * 锁定订单
	 * @param orderId
	 * @param lockReason
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/doLock", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> doLock(Integer orderId, Integer venderUserId, String lockReason, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> map = new HashMap<String, Object>();
		if(orderId == null){
			map.put("success", false);
			map.put("message", "订单号不能为空");
			return map;
		}
		if(StringUtils.isBlank(lockReason)){
			map.put("success", false);
			map.put("message", "锁定原因不能为空");
			return map;
		}
		return orderInfoService.lockOrder(orderId, venderUserId, lockReason);
	}
	
	/**
	 * 确认发货
	 * @param orderId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/sendGoods", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> sendGoods(Integer orderId, Integer venderUserId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> map = new HashMap<String, Object>();
		if(orderId == null){
			map.put("success", false);
			map.put("message", "订单号不能为空");
			return map;
		}
		return orderInfoService.sendGoods(orderId, venderUserId);
	}

	/**
	 * 解锁订单
	 * @param orderId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/doUnLock", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> doUnLock(Integer orderId, Integer venderUserId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> map = new HashMap<String, Object>();
		if(orderId == null){
			map.put("success", false);
			map.put("message", "订单号不能为空");
			return map;
		}
		//TODO 需要改为从cookie中获取 venderUserId
		return orderInfoService.unLockOrder(orderId, venderUserId);
	}
	
	/**
	 * 订单金额补录
	 * @param orderId
	 * @param orderPayType
	 * @param paymentMode
	 * @param paymentMoney
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/doEntry", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> doEntry(Integer orderId,
			Integer orderPayType, Integer paymentMode, BigDecimal paymentMoney, Integer venderUserId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> map = new HashMap<String, Object>();
		if(orderId == null){
			map.put("success", false);
			map.put("message", "订单号不能为空");
			return map;
		}
		if(orderPayType == null){
			map.put("success", false);
			map.put("message", "订单款项类型不能为空");
			return map;
		}
		if(paymentMode == null){
			map.put("success", false);
			map.put("message", "支付方式不能为空");
			return map;
		}
		if(paymentMoney == null){
			map.put("success", false);
			map.put("message", "金额不能为空");
			return map;
		}
		//TODO 需要改为从cookie中获取 venderUserId
		return orderInfoService.addSellerEntry(orderId, orderPayType, paymentMode, paymentMoney, venderUserId);
	}
	
	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

	public void setSellerEntryService(SellerEntryService sellerEntryService) {
		this.sellerEntryService = sellerEntryService;
	}

	public void setPaymentInfoService(PaymentInfoService paymentInfoService) {
		this.paymentInfoService = paymentInfoService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	
}

