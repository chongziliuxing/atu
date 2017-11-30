package com.atu.erp.web.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.atu.erp.common.utils.StatusUtil;
import com.atu.erp.domain.BalanceInfo;
import com.atu.erp.domain.BusinessUserExt;
import com.atu.erp.domain.query.BalanceInfoQuery;
import com.atu.erp.service.AddressService;
import com.atu.erp.service.BalanceInfoService;
import com.atu.erp.service.BusinessUserExtService;
import com.atu.erp.service.OrderInfoService;
import com.atu.erp.service.PaymentInfoService;
import com.atu.erp.service.SellerEntryService;
import com.atu.erp.service.result.Result;

@Controller
@RequestMapping("/balanceInfo")
public class BalanceInfoController {

	private final static Log log = LogFactory.getLog(BalanceInfoController.class);
	
	@Autowired
	private BusinessUserExtService businessUserExtService;
	@Autowired
	private BalanceInfoService balanceInfoService;
	@Autowired
	private OrderInfoService orderInfoService;
	
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	
	@InitBinder
	public void initDateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	/**
	 * 添加结算信息
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/addBalanceInfo", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addBalanceInfo(BalanceInfo balanceInfo,Integer orderId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		return balanceInfoService.addBalanceInfo(balanceInfo, orderId);
	}
	
	/**
	 * 结算列表页面
	 * @param balanceInfoQuery
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/index", method={ RequestMethod.GET, RequestMethod.POST })
	public String index(BalanceInfoQuery balanceInfoQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		PaginatedList<BalanceInfo> list = balanceInfoService.getBalanceInfosByPage(balanceInfoQuery);
		context.put("list", list);
		context.put("query", balanceInfoQuery);
		return "/balanceInfo/index";
	}
	
	/***
	 * 查看结算单明细
	 * @param balanceId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/detail", method={ RequestMethod.GET, RequestMethod.POST })
	public String detail(Integer balanceId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		
		BalanceInfoQuery balanceInfoQuery = new BalanceInfoQuery();
		balanceInfoQuery.setBalanceId(balanceId);
		BalanceInfo balanceInfo = balanceInfoService.getBalanceInfoByBalanceId(balanceInfoQuery);
		context.put("balanceInfo", balanceInfo);
		return "/balanceInfo/detail";
	}
	
	/***
	 * 查看结算单
	 * @param balanceId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/seeBalance", method={ RequestMethod.GET, RequestMethod.POST })
	public String seeBalance(Integer balanceId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		
		BalanceInfoQuery balanceInfoQuery = new BalanceInfoQuery();
		balanceInfoQuery.setBalanceId(balanceId);
		BalanceInfo balanceInfo = balanceInfoService.getBalanceInfoByBalanceId(balanceInfoQuery);
		BusinessUserExt businessUserExt = businessUserExtService.selectByUserId(balanceInfo.getVenderUserId());
		context.put("balanceInfo", balanceInfo);
		context.put("businessUserExt", businessUserExt);
		return "/balanceInfo/seeBalance";
	}
	
	/**
	 * 确认结算
	 * @param balanceInfo
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/doAgree", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> doAgree(BalanceInfo balanceInfo, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> map = new HashMap<String, Object>();
		if(balanceInfo.getBalanceId() == null){
			map.put("success", false);
			map.put("message", "结算单号不能为空");
			return map;
		}
		if(balanceInfo.getBalanceStatus() == null || !balanceInfo.getBalanceStatus().toString().trim().matches("^[12345]{1}$")){
			map.put("success", false);
			map.put("message", "审核状态不正确");
			return map;
		}
		
		return balanceInfoService.doAgree(balanceInfo);
	}
	
	/**
	 * 导出excel
	 * @param balanceInfoQuery
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/excel", method={ RequestMethod.GET, RequestMethod.POST })
	public void excel(BalanceInfoQuery balanceInfoQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		try{
			List<BalanceInfo> list = this.balanceInfoService.selectByCondition(balanceInfoQuery);
			if(list == null){
				list = new ArrayList<BalanceInfo>();
			}
			
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet("结算管理");
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow((int) 0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
			
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellValue("结算单号");
			cell.setCellStyle(style);
			cell = row.createCell((short) 1);
			cell.setCellValue("商家编号");
			cell.setCellStyle(style);
			cell = row.createCell((short) 2);
			cell.setCellValue("起止时间");
			cell.setCellStyle(style);
			cell = row.createCell((short) 3);
			cell.setCellValue("本期应收");
			cell.setCellStyle(style);
			cell = row.createCell((short) 4);
			cell.setCellValue("本期应付");
			cell.setCellStyle(style);
			cell = row.createCell((short) 5);
			cell.setCellValue("应结金额");
			cell.setCellStyle(style);
			cell = row.createCell((short) 6);
			cell.setCellValue("实结金额");
			cell.setCellStyle(style);
			cell = row.createCell((short) 7);
			cell.setCellValue("结算状态");
			cell.setCellStyle(style);
			cell = row.createCell((short) 8);
			cell.setCellValue("付款日期");
			cell.setCellStyle(style);
			for(int i=0;i<list.size();i++){
				row = sheet.createRow((int) i + 1);
				BalanceInfo balanceInfo = (BalanceInfo) list.get(i);
				// 第四步，创建单元格，并设置值
				row.createCell((short) 0).setCellValue(balanceInfo.getBalanceId());
				row.createCell((short) 1).setCellValue(balanceInfo.getVenderUserId());
				String balanceDateStr=sdf2.format(balanceInfo.getBalanceDateStart())+"至"+sdf2.format(balanceInfo.getBalanceDateEnd());
				row.createCell((short) 2).setCellValue(balanceDateStr);
				row.createCell((short) 3).setCellValue(balanceInfo.getPaymentGoods() != null ? balanceInfo.getPaymentGoods().doubleValue() : 0);
				
				row.createCell((short) 4).setCellValue(balanceInfo.getOughtPayMoney() != null ? balanceInfo.getOughtPayMoney().doubleValue() : 0);
				row.createCell((short) 5).setCellValue(balanceInfo.getbalanceMoneyFact() != null ? balanceInfo.getbalanceMoneyFact().doubleValue() : 0);
				row.createCell((short) 6).setCellValue(balanceInfo.getBalanceMoney() != null ? balanceInfo.getBalanceMoney().doubleValue() : 0);
				String status =StatusUtil.getBlanceStatusName(balanceInfo.getBalanceStatus());
				row.createCell((short) 7).setCellValue(status);
				if(balanceInfo.getPayDate()!=null){
				row.createCell((short) 8).setCellValue(sdf2.format(balanceInfo.getPayDate()));
				} else {
					row.createCell((short) 8).setCellValue("");
				}
			}
			
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("Content-disposition", "attachment;filename="+sdf2.format(new Date())+".xls");
			wb.write(response.getOutputStream());
		}catch (Exception e) {
			log.error("", e);
		}
	}
}

