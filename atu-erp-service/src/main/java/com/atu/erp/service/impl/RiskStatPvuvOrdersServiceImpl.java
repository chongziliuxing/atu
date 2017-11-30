package com.atu.erp.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atu.erp.common.utils.PaginatedList;
import com.atu.erp.common.utils.impl.PaginatedArrayList;
import com.atu.erp.dao.OrderInfoDao;
import com.atu.erp.dao.RiskStatMinutesDao;
import com.atu.erp.dao.RiskStatPvuvOrdersDao;
import com.atu.erp.domain.RiskStatPvuvOrders;
import com.atu.erp.domain.query.OrderInfoQuery;
import com.atu.erp.domain.query.RiskStatMinutesQuery;
import com.atu.erp.domain.query.RiskStatPvuvOrdersQuery;
import com.atu.erp.service.RiskStatPvuvOrdersService;

@Service(value = "riskStatPvuvOrdersService")
public class RiskStatPvuvOrdersServiceImpl implements RiskStatPvuvOrdersService {

	private static Log LOG = LogFactory.getLog(RiskStatPvuvOrdersServiceImpl.class);
	
	@Autowired
	private RiskStatPvuvOrdersDao riskStatPvuvOrdersDao;
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private RiskStatMinutesDao riskStatMinutesDao;
	
	@Override
	public PaginatedList<RiskStatPvuvOrders> getRiskStatPvuvOrdersForPage(RiskStatPvuvOrdersQuery riskStatPvuvOrdersQuery) {
		PaginatedList<RiskStatPvuvOrders> riskStatPvuvOrdersList = new PaginatedArrayList<RiskStatPvuvOrders>(
				riskStatPvuvOrdersQuery.getPageNo(),riskStatPvuvOrdersQuery.getPageSize());

		try {
			int count = riskStatPvuvOrdersDao.countByCondition(riskStatPvuvOrdersQuery);
			if (count > 0) {
				riskStatPvuvOrdersList.setTotalItem(count);
				int startRow=riskStatPvuvOrdersList.getStartRow();
				if (startRow == 0) {
					startRow = 1;
				}
				riskStatPvuvOrdersQuery.setStart(startRow-1);
				List<RiskStatPvuvOrders> list = riskStatPvuvOrdersDao.selectByConditionForPage(riskStatPvuvOrdersQuery);
				for (RiskStatPvuvOrders riskStatPvuvOrders : list) {
					//计算客单价
					BigDecimal guestUnitMoney = riskStatPvuvOrders.getGuestUnitMoney();
					if(riskStatPvuvOrders.getOrderConsumer() ==  0){
						guestUnitMoney = new BigDecimal(0).setScale(2,RoundingMode.HALF_UP);
						
					}else{
						guestUnitMoney = div(riskStatPvuvOrders.getOrderMoney(),riskStatPvuvOrders.getOrderConsumer(),2);
					}
					riskStatPvuvOrders.setGuestUnitMoney(guestUnitMoney);
				}
				riskStatPvuvOrdersList.addAll(list);
			}
		} catch (Exception e) {
			LOG.error("RiskStatPvuvOrdersServiceImpl.getRiskStatPvuvOrdersForPage", e);
		}
		return riskStatPvuvOrdersList;
	}
	
	@Override
	public RiskStatPvuvOrders getRiskStatPvuvOrdersSurvey(Integer projectId) {
		RiskStatPvuvOrders riskStatPvuvOrders = null;
		try {
			riskStatPvuvOrders = riskStatPvuvOrdersDao.riskStatPvuvOrdersSurvey(projectId);
			//计算 下订单总金额的总和 = 统计的数据 + 没有统计时的数据(74715.68)
			BigDecimal orderMoneyBefor = new BigDecimal(74715.68);//没有统计之前的订单金额
			BigDecimal orderMony = riskStatPvuvOrders.getOrderMoney();
			riskStatPvuvOrders.setOrderMoney(orderMony.add(orderMoneyBefor).setScale(2,RoundingMode.HALF_UP));
			
			//计算  商品数量  = 统计的数据 + 之前没有统计的(1824)
			riskStatPvuvOrders.setItemCount(riskStatPvuvOrders.getItemCount() + 1824); //设置下单商品件数
			
			//计算 客单数 = 统计的数据 + 之前没有统计的(106)
			riskStatPvuvOrders.setOrderConsumer(riskStatPvuvOrders.getOrderConsumer() + 106);
			
			//计算 订单量 = 统计的数据 + 之前没有统计的(225)
			riskStatPvuvOrders.setOrderNumber(riskStatPvuvOrders.getOrderNumber() + 225);
			
			BigDecimal orderConsumer = new BigDecimal(riskStatPvuvOrders.getOrderConsumer());
			BigDecimal money = new BigDecimal(0);
			//计算客单价
			BigDecimal guestUnitMoney = riskStatPvuvOrders.getGuestUnitMoney();
			if(riskStatPvuvOrders.getOrderConsumer() ==  0){
				guestUnitMoney = money.setScale(2,RoundingMode.HALF_UP);
			}else{
				guestUnitMoney = div(riskStatPvuvOrders.getOrderMoney(),riskStatPvuvOrders.getOrderConsumer(),2);
			}
			riskStatPvuvOrders.setGuestUnitMoney(guestUnitMoney);//设置客单价
			
			//设置UV UV=统计的 + 预估UV
			riskStatPvuvOrders.setUv(riskStatPvuvOrders.getUv() + 150);
			
			BigDecimal conversionRate = money.setScale(2,RoundingMode.HALF_UP);
			if(riskStatPvuvOrders.getUv() > 0){
				conversionRate = div(orderConsumer,riskStatPvuvOrders.getUv(),2).multiply(new BigDecimal(100));
			}
			riskStatPvuvOrders.setConversionRate(conversionRate);//设置转化率

		}catch (Exception e) {
			LOG.error("RiskStatPvuvOrdersServiceImpl.getRiskStatPvuvOrdersSurvey", e);
		}
		return riskStatPvuvOrders;
	} 

	@Override
	public Map<String, Object> getChartInfo(RiskStatPvuvOrdersQuery riskStatPvuvOrdersQuery) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		RiskStatPvuvOrders riskStatPvuvOrders = new RiskStatPvuvOrders();
		try {
			StringBuffer pvData = new StringBuffer("[");
			StringBuffer uvData = new StringBuffer("[");
			StringBuffer conversionRateData = new StringBuffer("[");
			StringBuffer categories = new StringBuffer("[");//分段展示的时间区域
			Date keyDate = null;
			Date startTime = riskStatPvuvOrdersQuery.getStartTime();
			Date endTime = riskStatPvuvOrdersQuery.getEndTime();
			Integer day = riskStatPvuvOrdersQuery.getDays();
			if(startTime != null && endTime != null && startTime.compareTo(endTime) == 0){  //代表查询的某一天
				if(startTime.compareTo(endTime) == 0){
					day = 1; 
				}
			}
			
			if(startTime != null && endTime != null && startTime.compareTo(endTime) < 0){ //结束时间大于开始时间
				int pvSum = 0;
				int uvSum = 0;
				int orderConsumerSum = 0;
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(startTime);
				int startDay = calendar.get(Calendar.DATE); //开始时间
				calendar.setTime(endTime);
				int endDay = calendar.get(Calendar.DATE);  //结束时间
				
				for (int i = startDay; i <= endDay; i++) {
					calendar.set(Calendar.DATE, i);
					calendar.set(Calendar.HOUR_OF_DAY, 0);
					calendar.set(Calendar.SECOND,0);
					calendar.set(Calendar.MINUTE,0);
					keyDate = calendar.getTime();
					riskStatPvuvOrdersQuery.setKeyDate(keyDate);
					List<RiskStatPvuvOrders> riskStatPvuvOrdersList = riskStatPvuvOrdersDao.selectByCondition(riskStatPvuvOrdersQuery);
					Integer pv = 0;
					Integer uv = 0;
					BigDecimal conversionRate = new BigDecimal(0);
					if(riskStatPvuvOrdersList == null || riskStatPvuvOrdersList.isEmpty()){
						if(i == endDay){
							categories.append("'" + i +"/天" + "']");
							pvData.append(pv + "]");
							uvData.append(uv + "]");
							conversionRateData.append(conversionRate + "]");
						}else{
							pvData.append(pv + ",");
							uvData.append(uv + ",");
							categories.append("'" + i + "',");
							conversionRateData.append(conversionRate + ",");
						}
					}else{
						for (RiskStatPvuvOrders riskPvuvOrders : riskStatPvuvOrdersList) {
							pv = riskPvuvOrders.getPv();
							uv = riskPvuvOrders.getUv();
							Integer orderConsumer = riskPvuvOrders.getOrderConsumer();
							conversionRate = calcConversionRate(orderConsumer,uv);
							
							if(i == endDay){
								categories.append("'" + i +"/天" + "']");
								pvData.append(pv + "]");
								uvData.append(uv + "]");
								conversionRateData.append(conversionRate + "]");
							}else{
								pvData.append(pv + ",");
								uvData.append(uv + ",");
								categories.append("'" + i + "',");
								conversionRateData.append(conversionRate + ",");
							}
							
							pvSum += pv;
							uvSum += uv;
							orderConsumerSum += orderConsumer;
						}
					}
				}
				riskStatPvuvOrders.setPv(pvSum);
				riskStatPvuvOrders.setUv(uvSum);
				riskStatPvuvOrders.setConversionRate(calcConversionRate(orderConsumerSum, uvSum));
			}
			else if(day ==  0){ //本月
				Calendar calendar = Calendar.getInstance();
				int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); //本月的最大天数
				
				int pvSum = 0;
				int uvSum = 0;
				int orderConsumerSum = 0;
				
				for(int i = 0; i < maxDay; i++){
					keyDate = getKeyDate(0,0,1,i);
					riskStatPvuvOrdersQuery.setKeyDate(keyDate);
					List<RiskStatPvuvOrders> riskStatPvuvOrdersList = riskStatPvuvOrdersDao.selectByCondition(riskStatPvuvOrdersQuery);
					Integer pv = 0;
					Integer uv = 0;
					BigDecimal conversionRate = new BigDecimal(0);
					if(riskStatPvuvOrdersList == null || riskStatPvuvOrdersList.isEmpty()){
						if(i == maxDay - 1){
							categories.append("'" + (i + 1) +"/天" + "']");
							pvData.append(pv + "]");
							uvData.append(uv + "]");
							conversionRateData.append(conversionRate + "]");
						}else{
							pvData.append(pv + ",");
							uvData.append(uv + ",");
							categories.append("'" + (i + 1) + "',");
							conversionRateData.append(conversionRate + ",");
						}
					}else{
						for (RiskStatPvuvOrders riskPvuvOrders : riskStatPvuvOrdersList) {
							pv = riskPvuvOrders.getPv();
							uv = riskPvuvOrders.getUv();
							Integer orderConsumer = riskPvuvOrders.getOrderConsumer();
							conversionRate = calcConversionRate(orderConsumer,uv);
							
							if(i == maxDay - 1){
								categories.append("'" + (i + 1) +"/天" + "']");
								pvData.append(pv + "]");
								uvData.append(uv + "]");
								conversionRateData.append(conversionRate + "]");
							}else{
								pvData.append(pv + ",");
								uvData.append(uv + ",");
								categories.append("'" + (i + 1) + "',");
								conversionRateData.append(conversionRate + ",");
							}
							
							pvSum += pv;
							uvSum += uv;
							orderConsumerSum += orderConsumer;
						}
					}
				}
				riskStatPvuvOrders.setPv(pvSum);
				riskStatPvuvOrders.setUv(uvSum);
				riskStatPvuvOrders.setConversionRate(calcConversionRate(orderConsumerSum, uvSum));
			}else{
				if(day == 1){    //代表查询的某一天
					keyDate = startTime;
				}else{
					keyDate = getDays(day);// 昨天、前天
				}
				//获取PV UV 转化率
				riskStatPvuvOrdersQuery.setKeyDate(keyDate);
				List<RiskStatPvuvOrders> riskStatPvuvOrdersList = riskStatPvuvOrdersDao.selectByCondition(riskStatPvuvOrdersQuery);
				if(riskStatPvuvOrdersList == null || riskStatPvuvOrdersList.isEmpty()){
					riskStatPvuvOrders.setPv(0);
					riskStatPvuvOrders.setUv(0);
					riskStatPvuvOrders.setConversionRate(calcConversionRate(riskStatPvuvOrders.getOrderConsumer(),riskStatPvuvOrders.getUv()));
				}else{
					riskStatPvuvOrders = riskStatPvuvOrdersList.get(0);
					riskStatPvuvOrders.setConversionRate(calcConversionRate(riskStatPvuvOrders.getOrderConsumer(),riskStatPvuvOrders.getUv()));
				}
				
				//数据曲线图信息
				RiskStatMinutesQuery riskStatMinutesQuery = new RiskStatMinutesQuery();
				riskStatMinutesQuery.setKeyDate(keyDate);//设置KeyDate
				
				Integer projectId = riskStatPvuvOrdersQuery.getProjectId();//系统来源
				Integer fromWhere = null; 
				if(projectId == 10){ //PC
					fromWhere = 3;
				}else if(projectId == 20){//H5
					fromWhere = 0;
				}else if(projectId == 30){//android
					fromWhere = 1;
				}else if(projectId == 40){//IOS
					fromWhere = 2;
				}else{
					projectId = null; //全部
				}
				
				OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
				orderInfoQuery.setFromWhere(fromWhere);//设置系统来源
				orderInfoQuery.setOrderFlag(1);//1表示所有支付的订单（status 1、2、3、4、5、6、50）
				
				for (int i = 0; i < 24; i++) {
					
					int minuteValueStart = i * 60;
					int minuteValueEnd = (i + 1) * 60;
					riskStatMinutesQuery.setTypeId(1);//PV
					riskStatMinutesQuery.setMinuteValueStart(minuteValueStart);
					riskStatMinutesQuery.setMinuteValueEnd(minuteValueEnd);
					riskStatMinutesQuery.setProjectId(projectId);
					//每小时的PV
				 	Integer pv = riskStatMinutesDao.selectPVUVCount(riskStatMinutesQuery);
				 	if(pv == null){
				 		pv = 0;
				 	}
				 	//每小时的UV
				 	riskStatMinutesQuery.setTypeId(2);//UV
				 	Integer uv = riskStatMinutesDao.selectPVUVCount(riskStatMinutesQuery);
				 	if(uv == null){
				 		uv = 0;
				 	}
				 	
				 	orderInfoQuery.setStartTime(getMinutes(day,minuteValueStart));
				 	orderInfoQuery.setEndTime(getMinutes(day,minuteValueEnd));
				 	
				 	//每小时下单客户数
				 	Integer orderConsumerHours =  orderInfoDao.selectOrderConsumer(orderInfoQuery);
					BigDecimal conversionRate = calcConversionRate(orderConsumerHours,uv);
					
					if(i == 23){
						categories.append("'" + i +"/点" + "']");
						pvData.append(pv + "]");
						uvData.append(uv + "]");
						conversionRateData.append(conversionRate + "]");
					}else{
						pvData.append(pv + ",");
						uvData.append(uv + ",");
						categories.append("'" + i + "',");
						conversionRateData.append(conversionRate + ",");
					}
					
				}
				
			}
			resultMap.put("pv", pvData.toString());
			resultMap.put("uv", uvData.toString());
			resultMap.put("conversionRate", conversionRateData.toString());
			resultMap.put("categories", categories.toString());
			resultMap.put("riskStatPvuvOrders", riskStatPvuvOrders);
		} catch (Exception e) {
			LOG.error("RiskStatPvuvOrdersServiceImpl.getChartInfo",e);
		}
		resultMap.put("success", true);
		return resultMap;
	}
	
	
	//除法保留两位小数
	public BigDecimal div(BigDecimal v1, int v2, int scale) {  
	   BigDecimal b2 = new BigDecimal(v2);  
	   return v1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);  
	}
	
	//分钟计算
	public Date getMinutes(int day, int minute){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MINUTE,minute);
		Date date = calendar.getTime();
		return date;
	}
	
	//天数计算
	public Date getDays(int day){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MINUTE,0);
		Date date = calendar.getTime();
		return date;
	}
	
	//计算KEYDATE
	public Date getKeyDate(int year, int month, int startDay,int addDay){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, startDay);
		calendar.add(Calendar.DATE, addDay);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MINUTE,0);
		Date date = calendar.getTime();
		return date;
	}
	
	//计算转化率
	public BigDecimal calcConversionRate(Integer orderConsumer, Integer uv){
		BigDecimal money = new BigDecimal(0); 
		BigDecimal conversionRate = money.setScale(2,RoundingMode.HALF_UP);//0.00
		if(uv > 0){
			BigDecimal counsumer = new BigDecimal(orderConsumer);
			conversionRate = div(counsumer,uv,2).multiply(new BigDecimal(100));
		}
		return conversionRate;
	}
	
	public static void main(String[] args) {
		Date date1 = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		int startDay = calendar.get(Calendar.DATE); //开始时间
		System.out.println(startDay);
	}
}
