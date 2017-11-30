package com.atu.erp.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atu.erp.common.utils.CookieUtil;
import com.atu.erp.domain.TbCouponPromo;
import com.atu.erp.domain.UserInfo;
import com.atu.erp.domain.query.TbCouponPromoQuery;
import com.atu.erp.service.TbCouponPromoService;
import com.atu.erp.service.UserService;
import com.atu.erp.web.base.BaseController;

@Controller
@RequestMapping("coupon")
public class CouponController{

	private final static Log Log = LogFactory.getLog(CouponController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TbCouponPromoService tbCouponPromoService;
	
	@InitBinder
	public void initDateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value="index", method={ RequestMethod.GET, RequestMethod.POST })
	public  String index(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		return "/coupon/applyCoupon";
	}
	
	/**
	 * 申请优惠券
	 */
	@RequestMapping(value="apply", method={ RequestMethod.GET, RequestMethod.POST })
	public  @ResponseBody Map<String, Object> apply(TbCouponPromo tbCouponPromo,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Date date=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddhhmmss");
		String time=df.format(date);
		tbCouponPromo.setCouponPromoCode("bm"+time);//设置优惠活动编码
		
		if(tbCouponPromo.getCouponValidType() == 1){ //优惠券有效方式(0 相对时间;1时间区间(结束时间）
			tbCouponPromo.setPromoStarttime(beginTime(date)); //设置活动开始时间
			if(tbCouponPromo.getPromoEndtime().compareTo(date) == -1){
				resultMap.put("msg","endTimeError");
				return resultMap;
			}
			tbCouponPromo.setPromoEndtime(endTime(tbCouponPromo.getPromoEndtime())); //设置活动结束时间
		}
		if(tbCouponPromo.getCouponBusinessType() == 1){ //优惠券业务类型：（1直接发放到账号 2用户行为相关 3通过链接领取）
			tbCouponPromo.setIsSend(0);       //设置未发放
		}
		tbCouponPromo.setApplyTime(new Date());
		tbCouponPromo.setCouponPromoState(0);//设置优惠券活动状态(0待上线;1正在进行;2已结束)
		tbCouponPromo.setYn(1);
		
		try {
			tbCouponPromoService.insert(tbCouponPromo);
		} catch (Exception e) {
			resultMap.put("msg","error");
			Log.error("coupon.apply:", e);
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	/**
	 * 我的优惠券
	 */
	@RequestMapping(value="myCoupon", method={ RequestMethod.GET, RequestMethod.POST })
	public String myCoupon(TbCouponPromoQuery tbCouponPromoQuery,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(tbCouponPromoQuery.getPageNo()==null){
				tbCouponPromoQuery.setPageNo(1);
			}
			tbCouponPromoQuery.setYn(1);
			
			resultMap = tbCouponPromoService.getTbCouponPromoByPage(tbCouponPromoQuery);
			context.put("resultMap", resultMap);
		} catch (Exception e) {
			Log.error("coupon.myCoupon:", e);
		}
		return "/coupon/myCoupon";
	}
	
	/**
	 * 上线优惠券
	 */
	@RequestMapping(value="online", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> online(Integer couponPromoId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			TbCouponPromo tbCouponPromo = tbCouponPromoService.selectByCouponPromoId(couponPromoId);
			Date date = new Date();
			UserInfo userInfo = userService.queryById(CookieUtil.getUserId(reuqest));
			tbCouponPromo.setOpera(userInfo.getMobile()); //设置操作者
			tbCouponPromo.setOperaTime(date);//设置操作时间
			tbCouponPromo.setCouponPromoState(1);  //设置优惠券活动状态(0待上线;1正在进行;2已结束)
			tbCouponPromo.setCouponValidStarttime(beginTime(date));//设置有效开始时间
			if(tbCouponPromo.getCouponValidType() == 0 ){
				tbCouponPromo.setCouponValidEndtime(endTime(addDays(date,tbCouponPromo.getCouponValidDays())));//设置有效结束时间
			}else if(tbCouponPromo.getCouponValidType() == 1){
				tbCouponPromo.setPromoStarttime(beginTime(date));   //设置有效开始时间
				tbCouponPromo.setCouponValidEndtime(tbCouponPromo.getPromoEndtime());    //设置有效结束时间
			}
			tbCouponPromoService.modify(tbCouponPromo);
			context.put("resultMap", resultMap);
		} catch (Exception e) {
			resultMap.put("msg","error");
			Log.error("coupon.online:", e);
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	
	/**
	 * 下线优惠券
	 */
	@RequestMapping(value="offline", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> offline(Integer couponPromoId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			TbCouponPromo tbCouponPromo = tbCouponPromoService.selectByCouponPromoId(couponPromoId);
			Date date = new Date();
			
			UserInfo userInfo = userService.queryById(CookieUtil.getUserId(reuqest));
			tbCouponPromo.setOpera(userInfo.getMobile()); //设置操作者
			tbCouponPromo.setOperaTime(date);//设置操作时间
			tbCouponPromo.setCouponPromoState(2);
			
			tbCouponPromoService.modify(tbCouponPromo);
			context.put("resultMap", resultMap);
		} catch (Exception e) {
			resultMap.put("msg","error");
			Log.error("coupon.online:", e);
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	/**
	 * 优惠券详情
	 */
	@RequestMapping(value="detail", method={ RequestMethod.GET, RequestMethod.POST })
	public String detail(Integer couponPromoId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		try {
			TbCouponPromo tbCouponPromo = tbCouponPromoService.selectByCouponPromoId(couponPromoId);
			context.put("tbCouponPromo", tbCouponPromo);
		} catch (Exception e) {
			Log.error("coupon.detail:", e);
		}
		return "/coupon/detail";
	}
	
	
	@RequestMapping(value="toUpdate", method={ RequestMethod.GET, RequestMethod.POST })
	public String toUpdate(Integer couponPromoId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		try {
			TbCouponPromo tbCouponPromo = tbCouponPromoService.selectByCouponPromoId(couponPromoId);
			context.put("tbCouponPromo", tbCouponPromo);
		} catch (Exception e) {
			Log.error("coupon.detail:", e);
		}
		return "/coupon/editCoupon";
	}
	
	/*
	 * 修改优惠券
	 */
	@RequestMapping(value="editCoupon", method={ RequestMethod.GET, RequestMethod.POST })
	public  @ResponseBody Map<String, Object> editCoupon(TbCouponPromo tbCouponPromo,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			tbCouponPromo.setApplyDepartmentName("市场部");
			tbCouponPromoService.modify(tbCouponPromo);
		} catch (Exception e) {
			resultMap.put("msg","error");
			Log.error("coupon.editCoupon:", e);
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	/**
	 * 追加优惠券数量
	 */
	@RequestMapping(value="addNum", method={ RequestMethod.GET, RequestMethod.POST })
	public  @ResponseBody Map<String, Object> addNum(Integer addNum,Integer couponPromoId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			TbCouponPromo tbCouponPromo =	tbCouponPromoService.selectByCouponPromoId(couponPromoId);
			Integer couponApplyAmount = tbCouponPromo.getCouponApplyAmount() + addNum;
			if(tbCouponPromo.getCouponBusinessType() == 1){
				tbCouponPromo.setIsSend(0);
			}
			tbCouponPromo.setCouponApplyAmount(couponApplyAmount);
			tbCouponPromoService.modify(tbCouponPromo);
			resultMap.put("couponApplyAmount", couponApplyAmount);
		} catch (Exception e) {
			resultMap.put("msg","error");
			Log.error("coupon.editCoupon:", e);
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	/**
	 * 优惠券财务统计
	 */
	@RequestMapping(value="statistics", method={ RequestMethod.GET, RequestMethod.POST })
	public String statistics(TbCouponPromoQuery tbCouponPromoQuery,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(tbCouponPromoQuery.getPageNo()==null){
				tbCouponPromoQuery.setPageNo(1);
			}
			tbCouponPromoQuery.setCouponFlag(1);
			tbCouponPromoQuery.setYn(1);
			
			resultMap = tbCouponPromoService.getTbCouponPromoByPage(tbCouponPromoQuery);
			context.put("resultMap", resultMap);
		} catch (Exception e) {
			Log.error("coupon.statistics:", e);
		}
		return "/coupon/statistics";
	}
	
	/**
	 * 计算增加天数
	 */
	public Date addDays(Date beginDate,int day){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) + day);
		try {
			return dft.parse(dft.format(date.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return  null;
	}
	
	/**
	 * 设置开始时间
	 */
	public Date beginTime(Date beginDate){
		beginDate.setHours(0);
		beginDate.setMinutes(0);
		beginDate.setSeconds(0);
		return beginDate;
	}
	
	/**
	 * 设置结束时间
	 */
	public Date endTime(Date endDate){
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		return endDate;
	}
}


