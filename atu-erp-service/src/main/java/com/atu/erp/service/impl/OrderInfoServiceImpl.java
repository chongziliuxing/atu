package com.atu.erp.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.atu.erp.domain.PaymentInfo;
import com.atu.erp.domain.query.PaymentInfoQuery;
import com.atu.erp.common.utils.PaginatedList;
import com.atu.erp.common.utils.impl.PaginatedArrayList;
import com.atu.erp.dao.AddressDao;
import com.atu.erp.dao.OrderConsigneeDao;
import com.atu.erp.dao.OrderDetailDao;
import com.atu.erp.dao.OrderInfoDao;
import com.atu.erp.dao.PaymentInfoDao;
import com.atu.erp.dao.SellerEntryDao;
import com.atu.erp.domain.Address;
import com.atu.erp.domain.OrderConsignee;
import com.atu.erp.domain.OrderDetail;
import com.atu.erp.domain.OrderInfo;
import com.atu.erp.domain.SellerEntry;
import com.atu.erp.domain.query.AddressQuery;
import com.atu.erp.domain.query.OrderConsigneeQuery;
import com.atu.erp.domain.query.OrderInfoQuery;
import com.atu.erp.service.OrderInfoService;
import com.atu.erp.service.result.Result;

@Service(value = "orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {
	private static Log log = LogFactory.getLog(OrderInfoServiceImpl.class);
	private OrderInfoDao orderInfoDao;
	private OrderDetailDao orderDetailDao;
	private SellerEntryDao sellerEntryDao;
	private OrderConsigneeDao orderConsigneeDao;
	private AddressDao addressDao;
	private PaymentInfoDao paymentInfoDao;
	private DataSourceTransactionManager transactionManager;

	@Override
	public PaginatedList<OrderInfo> getOrderInfosByPage(OrderInfoQuery orderInfoQuery) {
		PaginatedList<OrderInfo> orderInfoList = new PaginatedArrayList<OrderInfo>(orderInfoQuery.getPageNo(),orderInfoQuery.getPageSize());
		try{
			int count = orderInfoDao.countByCondition(orderInfoQuery);
			if(count > 0){
				orderInfoList.setTotalItem(count);
				orderInfoQuery.setStart(orderInfoList.getStartRow() - 1);
				List<OrderInfo> list= orderInfoDao.selectByConditionForPage(orderInfoQuery);
				for(OrderInfo orderInfo : list){
					//查询订单详情信息
					List<OrderDetail> orderDetailList = orderDetailDao.selectByOrderId(orderInfo.getOrderId());
					if(orderDetailList != null && orderDetailList.size() > 0){
						orderInfo.setOrderDetail(orderDetailList);
					}
					
					//查询订单收货信息
					OrderConsigneeQuery orderConsigneeQuery = new OrderConsigneeQuery();
					orderConsigneeQuery.setOrderId(orderInfo.getOrderId());
					List<OrderConsignee> orderConsigneeList = orderConsigneeDao.selectByCondition(orderConsigneeQuery);
					if(orderConsigneeList != null && orderConsigneeList.size() > 0){
						orderInfo.setOrderConsigneeList(orderConsigneeList);
					}
				}
				orderInfoList.addAll(list);
			}
		}catch (Exception e) {
			log.error("", e);
		}
		return orderInfoList;
	}

	@Override
	public OrderInfo getOrderInfoByOrderId(OrderInfoQuery orderInfoQuery) {
		OrderInfo orderInfo = null;
		try {
			List<OrderInfo> list = orderInfoDao
					.selectByCondition(orderInfoQuery);
			if (list != null && list.size() > 0) {
				orderInfo = list.get(0);
				List<OrderDetail> orderDetailList = orderDetailDao
						.selectByOrderId(orderInfo.getOrderId());
				orderInfo.setOrderDetail(orderDetailList);
			}
		} catch (Exception e) {
			log.error("", e);
		}
		return orderInfo;
	}

	@Override
	public Map<String, Object> lockOrder(Integer orderId, Integer venderUserId,
			String lockReason) {
		Map<String, Object> map = new HashMap<String, Object>();

		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderId(orderId);
		orderInfo.setVenderUserId(venderUserId);
		orderInfo.setLockReason(lockReason);
		orderInfo.setLockStatus(1);
		int result = orderInfoDao.modify(orderInfo);
		if (result == 0) {
			map.put("success", false);
			map.put("message", "修改失败");
		} else {
			map.put("success", true);
		}
		return map;
	}

	@Override
	public Map<String, Object> unLockOrder(Integer orderId, Integer venderUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderId(orderId);
		orderInfo.setVenderUserId(venderUserId);
		orderInfo.setLockStatus(0);
		int result = orderInfoDao.modify(orderInfo);
		if (result == 0) {
			map.put("success", false);
			map.put("message", "修改失败");
		} else {
			map.put("success", true);
		}
		return map;
	}

	@Override
	public Map<String, Object> confirmGetPrice(Integer orderId,
			Integer venderUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderId(orderId);
		orderInfo.setVenderUserId(venderUserId);
		orderInfo.setOrderStatus(2);

		int result = orderInfoDao.modify(orderInfo);
		if (result == 0) {
			map.put("success", false);
			map.put("message", "修改失败");
		} else {
			map.put("success", true);
		}
		return map;
	}

	@Override
	public Map<String, Object> confirmGetLastPrice(Integer orderId,
			Integer venderUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderId(orderId);
		orderInfo.setVenderUserId(venderUserId);
		orderInfo.setOrderStatus(4);

		int result = orderInfoDao.modify(orderInfo);
		if (result == 0) {
			map.put("success", false);
			map.put("message", "修改失败");
		} else {
			map.put("success", true);
		}
		return map;
	}

	@Override
	public Map<String, Object> addSellerEntry(final Integer orderId,
			final Integer orderPayType, final Integer paymentMode,
			final BigDecimal paymentMoney, final Integer venderUserId) {
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		try {
			new TransactionTemplate(transactionManager)
					.execute(new TransactionCallbackWithoutResult() {
						@Override
						protected void doInTransactionWithoutResult(
								TransactionStatus arg0) {
							SellerEntry se = new SellerEntry();
							se.setOrderId(orderId);
							se.setOrderPayType(orderPayType);
							se.setPaymentMode(paymentMode);
							se.setPaymentMoney(paymentMoney);

							if (sellerEntryDao.insert(se) > 0) {// 添加一条记录
								// 修改实际支付金额
								OrderInfo orderInfo = new OrderInfo();
								orderInfo.setPayMoney(paymentMoney);
								orderInfo.setVenderUserId(venderUserId);
								orderInfo.setOrderId(orderId);
								if (orderInfoDao.modifyPayMoney(orderInfo) > 0) {
									map.put("success", true);
									return;
								}
							}
						}
					});
			boolean success = (Boolean) map.get("success");
			if (success) {
				return map;
			}
		} catch (Exception e) {
			log.error("", e);
		}
		map.put("success", false);
		map.put("message", "添加失败");
		return map;
	}

	@Override
	public Map<String, Object> sendGoods(Integer orderId, Integer venderId) {
		Map<String, Object> map = new HashMap<String, Object>();

		OrderInfoQuery query = new OrderInfoQuery();
		query.setOrderId(orderId);
		query.setVenderUserId(venderId);
		List<OrderInfo> list = orderInfoDao.selectByCondition(query);

		if (list == null || list.size() == 0) {
			map.put("success", false);
			map.put("message", "订单不存在");
			return map;
		}

		OrderInfo orderInfo = list.get(0);
		if (orderInfo.getOrderStatus() == 51) {// 订单已取消
			map.put("success", false);
			map.put("message", "该订单已被取消");
			return map;
		}
		if (orderInfo.getOrderStatus() >= 5) {
			map.put("success", false);
			map.put("message", "该订单已发货");
			return map;
		}

		orderInfo.setOrderId(orderId);
		orderInfo.setVenderUserId(venderId);
		orderInfo.setOrderStatus(5);

		int result = orderInfoDao.modify(orderInfo);
		if (result == 0) {
			map.put("success", false);
			map.put("message", "修改失败");
		} else {
			map.put("success", true);
		}
		return map;
	}

	@Override
	public Map<String, Object> updateOrderInfoFinish(Integer orderId,
			Integer venderUserId) {
		Map<String, Object> map = new HashMap<String, Object>();

		OrderInfoQuery query = new OrderInfoQuery();
		query.setOrderId(orderId);
		query.setVenderUserId(venderUserId);
		List<OrderInfo> list = orderInfoDao.selectByCondition(query);

		if (list == null || list.size() == 0) {
			map.put("success", false);
			map.put("message", "订单不存在");
			return map;
		}

		OrderInfo orderInfo = list.get(0);
		if (orderInfo.getOrderStatus() == 51) {// 订单已取消
			map.put("success", false);
			map.put("message", "该订单已被取消");
			return map;
		}

		if (!((orderInfo.getOrderStatus() == 6 && orderInfo.getOrderType() == 1) || (orderInfo
				.getOrderStatus() == 4 && orderInfo.getOrderType() == 2))) {
			map.put("success", false);
			map.put("message", "此订单不能标记为完成订单");
			return map;
		}

		orderInfo.setOrderId(orderId);
		orderInfo.setVenderUserId(venderUserId);
		orderInfo.setOrderStatus(50);// 标记为订单完成

		int result = orderInfoDao.modify(orderInfo);
		if (result == 0) {
			map.put("success", false);
			map.put("message", "修改失败");
		} else {
			map.put("success", true);
		}
		return map;
	}

	@Override
	public List<OrderInfo> selectByCondition(OrderInfoQuery orderInfoQuery) {
		List<OrderInfo> list=orderInfoDao.selectByCondition(orderInfoQuery);
		for (OrderInfo orderInfo : list) {
			List<OrderDetail> orderDetailList = orderDetailDao
					.selectByOrderId(orderInfo.getOrderId());
			if (orderDetailList != null && orderDetailList.size() > 0) {
				orderInfo.setOrderDetail(orderDetailList);
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> selectOrderDetail(OrderInfoQuery orderInfoQuery) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			// 订单信息
			OrderInfo orderInfo = orderInfoDao.selectByOrderId(orderInfoQuery.getOrderId());
			
			// 订单商品信息
			List<OrderDetail> orderDetailList = orderDetailDao.selectByOrderId(orderInfo.getOrderId());
			orderInfo.setOrderDetail(orderDetailList);
			
			// 订单收货人信息
			String address = "";
			OrderConsigneeQuery orderConsigneeQuery = new OrderConsigneeQuery();
			orderConsigneeQuery.setOrderId(orderInfoQuery.getOrderId());
			orderConsigneeQuery.setYn(1);
			//orderConsigneeQuery.setVenderUserId(orderInfoQuery.getVenderUserId());
			
			List<OrderConsignee> consigneeList = orderConsigneeDao.selectByCondition(orderConsigneeQuery);
			if(consigneeList == null || consigneeList.isEmpty()){
				resultMap.put("msg","address's Null");
				return resultMap;
			}else{
				for (OrderConsignee orderConsignee : consigneeList) {
					address= getDetailConsigneeAddress(orderConsignee.getConsigneeProvince(), orderConsignee.getConsigneeCity(), orderConsignee.getConsigneeCounty(), orderConsignee.getConsigneeAddress());
					orderConsignee.setAddress(address);
				}
				resultMap.put("consigneeList", consigneeList);
			}
			resultMap.put("orderInfo", orderInfo);
			resultMap.put("orderDetailList", orderDetailList);
			
		}catch (Exception e) {
			log.error("", e);
		}
		return resultMap;
	}
	
	@Override
	public Map<String, Object> confirmMoney(Integer orderId,String paymentNumber,Date payTime) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		final OrderInfo orderInfo = orderInfoDao.selectByOrderId(orderId);
    	orderInfo.setOrderStatus(1); //修改订单状态为已支付
    	orderInfo.setPayMoney(orderInfo.getOughtPayMoney());//支付金额
    	orderInfo.setPayTime(payTime);//支付时间
    	
    	PaymentInfoQuery paymentInfoQuery = new PaymentInfoQuery();
		paymentInfoQuery.setOrderId(orderId);
		List<PaymentInfo> paymentInfoList = paymentInfoDao.selectByCondition(paymentInfoQuery);
		if(paymentInfoList == null || paymentInfoList.isEmpty()){ //判断支付是否成功worker 24小时扫微信支付接口
    	
			//组装支付信息
			final PaymentInfo paymentInfo = new PaymentInfo();
			paymentInfo.setOrderId(orderId);
			paymentInfo.setOrderPayType(1);//（1-定金OR全款支付  2-尾款支付）
			paymentInfo.setPaymentMode(3); //（支付方式（1、微信APP支付   2、微信H5支付  3、线下支付  4、物流代收） ）
			paymentInfo.setPaymentInfoType(2);//(1、支付信息   2、支付成功确认信息)
			paymentInfo.setPaymentNumber(paymentNumber);
			paymentInfo.setPaymentMoney(orderInfo.getPayMoney());
			paymentInfo.setBusiPartner("109001");
			String pTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(payTime);
			paymentInfo.setDtOrder(pTime);
			
			new TransactionTemplate(transactionManager).execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					orderInfoDao.modify(orderInfo);
					paymentInfoDao.insert(paymentInfo);
				}
			});
		}
		resultMap.put("success",true);
		return resultMap;
	}
	
	
	//地址详情
	public String getDetailConsigneeAddress(int province, int city, int county,
			String address) {
		AddressQuery query1 = new AddressQuery();
		query1.setAddressLevel(1);
		query1.setAddressId(province);
		
		List<Address> list1 = addressDao.selectByCondition(query1);
		if(list1 == null || list1.size() == 0){
			return null;
		}
		
		AddressQuery query2 = new AddressQuery();
		query2.setAddressLevel(2);
		query2.setAddressId(city);
		
		List<Address> list2 = addressDao.selectByCondition(query2);
		if(list2 == null || list2.size() == 0){
			return null;
		}
		
		AddressQuery query3 = new AddressQuery();
		query3.setAddressLevel(3);
		query3.setAddressId(county);
		
		List<Address> list3 = addressDao.selectByCondition(query3);
		if(list3 == null || list3.size() == 0){
			return null;
		}
		
		return list1.get(0).getAddressName() + list2.get(0).getAddressName() + list3.get(0).getAddressName() + address;
	}
	
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}

	public void setSellerEntryDao(SellerEntryDao sellerEntryDao) {
		this.sellerEntryDao = sellerEntryDao;
	}

	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setOrderConsigneeDao(OrderConsigneeDao orderConsigneeDao) {
		this.orderConsigneeDao = orderConsigneeDao;
	}

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	public void setPaymentInfoDao(PaymentInfoDao paymentInfoDao) {
		this.paymentInfoDao = paymentInfoDao;
	}

}
