package com.atu.erp.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.atu.erp.common.utils.PaginatedList;
import com.atu.erp.common.utils.impl.PaginatedArrayList;
import com.atu.erp.dao.BalanceInfoDao;
import com.atu.erp.dao.BusinessUserExtDao;
import com.atu.erp.dao.OrderDetailDao;
import com.atu.erp.dao.OrderInfoDao;
import com.atu.erp.dao.PaymentInfoDao;
import com.atu.erp.domain.BalanceInfo;
import com.atu.erp.domain.BusinessUserExt;
import com.atu.erp.domain.OrderDetail;
import com.atu.erp.domain.OrderInfo;
import com.atu.erp.domain.PaymentInfo;
import com.atu.erp.domain.query.BalanceInfoQuery;
import com.atu.erp.domain.query.BusinessUserExtQuery;
import com.atu.erp.domain.query.OrderInfoQuery;
import com.atu.erp.domain.query.PaymentInfoQuery;
import com.atu.erp.service.BalanceInfoService;
import com.atu.erp.service.result.Result;

@Service(value="balanceInfoService")
public class BalanceInfoServiceImpl implements BalanceInfoService {
	private static Log log = LogFactory.getLog(BalanceInfoServiceImpl.class);

	private BalanceInfoDao balanceInfoDao;
	private OrderInfoDao orderInfoDao;
	private OrderDetailDao OrderDetailDao;
	private PaymentInfoDao paymentInfoDao;
	private BusinessUserExtDao businessUserExtDao;
	private DataSourceTransactionManager transactionManager;
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public PaginatedList<BalanceInfo> getBalanceInfosByPage(BalanceInfoQuery balanceInfoQuery) {
		PaginatedList<BalanceInfo> balanceInfoList = new PaginatedArrayList<BalanceInfo>(balanceInfoQuery.getPageNo(),balanceInfoQuery.getPageSize());
		try{
			int count = balanceInfoDao.countByCondition(balanceInfoQuery);
			if(count > 0){
				balanceInfoList.setTotalItem(count);
				balanceInfoQuery.setStart(balanceInfoList.getStartRow() - 1);
				List<BalanceInfo> list = balanceInfoDao.selectByCondition(balanceInfoQuery);
				if(list == null || list.isEmpty()){
				}else{
					balanceInfoList.addAll(list);
				}
			}
		}catch (Exception e) {
			log.error("", e);
		}
		return balanceInfoList;
	}

	@Override
	public BalanceInfo getBalanceInfoByBalanceId(BalanceInfoQuery balanceInfoQuery) {
		try {
			List<BalanceInfo> balanceInfoList = balanceInfoDao.selectByCondition(balanceInfoQuery);
			if(balanceInfoList == null || balanceInfoList.isEmpty()){
				return null;
			}
			BalanceInfo balanceInfo = balanceInfoList.get(0);
			
			/*OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
			orderInfoQuery.setVenderUserId(balanceInfo.getVenderUserId());
			orderInfoQuery.setOrderStatus(50);
			
			Date queryDate = balanceInfo.getBalanceDateEnd();
			orderInfoQuery.setStartFinishTime(sdf.parse(sdf.format(queryDate)));
			orderInfoQuery.setEndFinishTime(sdf.parse(sdf.format(new Date(orderInfoQuery.getStartFinishTime().getTime() + 60*60*24*1000))));
			List<OrderInfo> orderInfoList = orderInfoDao.selectByCondition(orderInfoQuery);
			
			for(int i=0;i<orderInfoList.size();i++){
				OrderInfo orderInfo = orderInfoList.get(i);
				List<OrderDetail> orderDetailList = OrderDetailDao.selectByOrderId(orderInfo.getOrderId());
				if(orderDetailList!=null && orderDetailList.size()>0){
				orderInfo.setOrderDetail(orderDetailList);
				}
				PaymentInfoQuery paymentInfoQuery = new PaymentInfoQuery();
				paymentInfoQuery.setOrderId(orderInfo.getOrderId());
				paymentInfoQuery.setPaymentInfoType(2);
				List<PaymentInfo> paymentInfolist = paymentInfoDao.selectByCondition(paymentInfoQuery);
				if(paymentInfolist != null && paymentInfolist.size() > 0){
					BigDecimal paymentGoodsPrice = new BigDecimal(0);
					for(int j=0;j<paymentInfolist.size();j++){
						paymentGoodsPrice = paymentGoodsPrice.add(paymentInfolist.get(j).getPaymentMoney());
					}
					orderInfo.setPaymentGoodsPrice(paymentGoodsPrice);
				}
			}
			
			BusinessUserExt businessUserExt = businessUserExtDao.selectByUserId(balanceInfo.getVenderUserId());
			balanceInfo.setVenderUserShopName(businessUserExt.getShopName());
			
			balanceInfo.setOrderInfoList(orderInfoList);*/
			return balanceInfo;
		} catch (Exception e) {
			log.error("", e);
		}
		return null;
	}

	@Override
	public Map<String, Object> doAgree(BalanceInfo balanceInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int result = balanceInfoDao.modify(balanceInfo);
		if(result == 0){
			map.put("success", false);
			map.put("message", "修改失败");
		}else{
			map.put("success", true);
		}
		return map;
	}
	
	@Override
	public List<BalanceInfo> selectByCondition(BalanceInfoQuery balanceInfoQuery) {
		List<BalanceInfo> list = null;
		try{
			if(balanceInfoQuery.getVenderUserId() == null && StringUtils.isNotBlank(balanceInfoQuery.getVenderUserShopName())){
				BusinessUserExtQuery businessUserExtQuery = new BusinessUserExtQuery();
				businessUserExtQuery.setShopName(balanceInfoQuery.getVenderUserShopName());
				List<BusinessUserExt> userList = businessUserExtDao.selectByCondition(businessUserExtQuery);
				if(userList != null && userList.size() > 0){
					balanceInfoQuery.setVenderUserId(userList.get(0).getUserId());
				}
			}
			
			list = balanceInfoDao.selectByCondition(balanceInfoQuery);
			if(list != null && list.size() > 0){
				for(int i=0;i<list.size();i++){
					BalanceInfo balanceInfo = list.get(i);
					BusinessUserExt businessUserExt = businessUserExtDao.selectByUserId(balanceInfo.getVenderUserId());
					balanceInfo.setVenderUserShopName(businessUserExt != null ? businessUserExt.getShopName() : null);
				}
			}
		}catch (Exception e) {
			log.error("", e);
		}
		return list;
	}
	
	@Override
	public Map<String, Object> addBalanceInfo(final BalanceInfo balanceInfo, Integer orderId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			final OrderInfo orderInfo = orderInfoDao.selectByOrderId(orderId);
			if(orderInfo == null){
				resultMap.put("message", "订单编号不存在,请核实订单编号！");
				return resultMap;
			}
			if(orderInfo.getBalanceId() !=null || orderInfo.getBalanceDate() !=null){
				resultMap.put("message", "订单已结算，请核实订单编号！");
				return resultMap;
			}
			OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
			orderInfoQuery.setOrderId(orderId);
			orderInfoQuery.setVenderUserId(balanceInfo.getVenderUserId());
			List<OrderInfo> orderInfoList = orderInfoDao.selectByCondition(orderInfoQuery);
			if(orderInfoList == null || orderInfoList.isEmpty()){
				resultMap.put("message", "该商家订单不存在,请核实订单编号");
				return resultMap;
			}
			final BigDecimal oughtPayMoney =  orderInfo.getOughtPayMoney();//订单应付金额
			//组装结算信息
			balanceInfo.setBalanceStatus(4); //结算状态(1:新建/待审核  2:审核通过 3:审核驳回 4:结算完成 5:冻结 6:商家已确认)
			balanceInfo.setOrderMoneyAll(oughtPayMoney); //订单金额（总）
			//BigDecimal rebate = oughtPayMoney.multiply(new BigDecimal(0.002)).setScale(2,BigDecimal.ROUND_HALF_UP); //返点 千分之二
			balanceInfo.setBalanceMoney(oughtPayMoney);//应结金额
			balanceInfo.setOughtPayMoney(oughtPayMoney);//本期应付
			new TransactionTemplate(transactionManager).execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					Integer balanceId = balanceInfoDao.insert(balanceInfo);
					if(balanceId != null){
						orderInfo.setBalanceDate(new Date());
						orderInfo.setBalanceId(balanceId);
						orderInfo.setOrderStatus(1);
						orderInfo.setPayTime(new Date());
						orderInfo.setPayMoney(oughtPayMoney);
						orderInfoDao.modify(orderInfo);
					}
				}
			});
		} catch (Exception e) {
			log.error(e);
		}
		resultMap.put("success", true);
		return resultMap;
	}
	
	public void setBalanceInfoDao(BalanceInfoDao balanceInfoDao) {
		this.balanceInfoDao = balanceInfoDao;
	}

	public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		OrderDetailDao = orderDetailDao;
	}

	public void setPaymentInfoDao(PaymentInfoDao paymentInfoDao) {
		this.paymentInfoDao = paymentInfoDao;
	}

	public void setBusinessUserExtDao(BusinessUserExtDao businessUserExtDao) {
		this.businessUserExtDao = businessUserExtDao;
	}

	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
}
