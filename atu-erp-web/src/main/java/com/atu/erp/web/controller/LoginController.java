package com.atu.erp.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.atu.erp.common.utils.MD5Util;
import com.atu.erp.common.utils.RedisUtils;
import com.atu.erp.domain.BusinessUserExt;
import com.atu.erp.domain.Sms;
import com.atu.erp.domain.UserInfo;
import com.atu.erp.domain.query.SmsQuery;
import com.atu.erp.service.BusinessUserExtService;
import com.atu.erp.service.SmsService;
import com.atu.erp.service.UserService;
import com.atu.erp.service.VenderInfoService;


@Controller
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private VenderInfoService venderInfoService;
	@Autowired
	private SmsService smsService;
	@Autowired
	private BusinessUserExtService businessUserExtService;
	
//	private RedisUtils redisUtils;
//	private UserDao userDao;
	private final static Log LOG = LogFactory.getLog(LoginController.class);
	
	@RequestMapping(value="", method={ RequestMethod.GET, RequestMethod.POST })
	public String index(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		
		return "login/login";
	}
	
	/**
	 * 注销方法
	 * @return
	 */
	@RequestMapping(value="logout", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> logout(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		//删除cookie
		Cookie cookie = new Cookie("loginname", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		//response.sendRedirect("/");
		
		//返回给页面值
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("msg", "success");
		resultMap.put("url","/");//注销后，跳转到的页面		
		return resultMap;
	}
	
	
	/**
	 * 登录方法
	 * @param loginname
	 * @param loginpwd
	 * @return
	 */
	@RequestMapping(value="onLogin", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> onLogin(String loginname, String loginpwd,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){

		
		//判断用户是否合法
		UserInfo user = userService.queryUser(loginname, MD5Util.md5Hex(loginpwd));
		if(user==null){
			//返回给页面值
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("success",false);
			resultMap.put("message", "您输入的账号或密码错误！");
			resultMap.put("url","/");//登录失败后，留在登录页面
			return resultMap;
		}
		if(user.getYn()!=1){
			//返回给页面值
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("success",false);
			resultMap.put("message", "该用户无效！");
			resultMap.put("url","/");//登录失败后，留在登录页面
			return resultMap;
		}
		if(user.getUserType()!=1){
			//返回给页面值
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("success",false);
			resultMap.put("message", "该用户不是ERP用户！");
			resultMap.put("url","/");//登录失败后，留在登录页面
			return resultMap;
		}
		
		//登录成功，写cookie
		Cookie cookie = new Cookie("loginname","ok"+loginname+"^"+user.getUserId());
		//cookie.setMaxAge(60*60*12);//cookie过期时间,半个小时
		response.addCookie(cookie);
		
		//返回给页面值
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success",true);
		resultMap.put("message", "");
		resultMap.put("url","/index");//登录成功后，跳转到的页面
		//resultMap.put("data", data);	
		
		return resultMap;
	}

	
	@RequestMapping(value="sign", method={ RequestMethod.GET, RequestMethod.POST })
	public String sign(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		
		return "login/sign";
	}
	
	@RequestMapping(value="onSign", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> onSign(String mobile,String shopName, String loginpwdSign,String loginpwdSign2, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){

		String verificationCode=reuqest.getParameter("verificationCode");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 判断两次密码是否相同
		if(!loginpwdSign.equals(loginpwdSign2)){
			//返回给页面值

			resultMap.put("success",false);
			resultMap.put("message", "输入的密码不一致！");
			//resultMap.put("url","sign");//登录成功后，跳转到的页面
			return resultMap;
		}
		//判断手机验证码是否正确	
		if(!verificationCode.equals(RedisUtils.get("seller_"+mobile))){
			resultMap.put("success",false);
			resultMap.put("message", "输入的验证码错误！");
			//resultMap.put("url","sign");//登录成功后，跳转到的页面		
			return resultMap;
			
		}
		UserInfo user=new UserInfo();
		user.setMobile(mobile);
		user.setPassword(MD5Util.md5Hex(loginpwdSign));
		user.setUserType(1);//用户类型(1-erp 2-卖家  3-买家)
		user.setYn(1);
		user.setRegisterIp(reuqest.getRemoteAddr());
		Integer userid= userService.addUser(user);
		
		//插入商家扩展信息
		if(userid!=null){
			BusinessUserExt businessUserExt = new BusinessUserExt();
			businessUserExt.setUserId(userid);
			businessUserExt.setShopName(shopName);
			businessUserExtService.insert(businessUserExt);
		}
		
		//返回给页面值
		resultMap.put("success",true);
		resultMap.put("message", "注册成功！");
		resultMap.put("url","/");//注册后，跳转到登录页面	
		return resultMap;
	}
	
	@RequestMapping(value="resetPwd", method={ RequestMethod.GET, RequestMethod.POST })
	public String resetPwd(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		
		return "login/resetPwd";
	}
	
	@RequestMapping(value="onResetPwd", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> onResetPwd(String moblie, String verification, String newpassword,String newpassword2,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){

		if(!newpassword.equals(newpassword2)){
			//返回给页面值
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("success",false);
			resultMap.put("message", "输入的密码不一致！");
			//resultMap.put("url","sign");//登录成功后，跳转到的页面		
			return resultMap;
		}
		UserInfo user=new UserInfo();
		user.setMobile(moblie);
		user.setPassword(MD5Util.md5Hex(newpassword));
		userService.updateUser(user);
		
		//返回给页面值
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success",true);
		resultMap.put("message", "重置密码成功！");
		resultMap.put("url","/");//重置密码，跳转到登录页面	
		return resultMap;
	}

	//短信验证码
	@RequestMapping(value="getSignSMSCode", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> getSignSMSCode(String mobile,HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			if(mobile != null) {
				//查询最近60秒内是否已经生成
				SmsQuery smsQuery = new SmsQuery();
				smsQuery.setMobile(mobile);
				Sms lastSms = smsService.queryLastSms(smsQuery);
				boolean bSendSms = true;
				if(lastSms != null) {
					long lastTime = lastSms.getCreated().getTime();
					long nowTime = new Date().getTime();
					//60秒内不重复发送
					if(nowTime - lastTime < 60000) {
						resultMap.put("msg","error");
						resultMap.put("reason", "60秒内无需重新获取");
						bSendSms = false;
					}
				}

				if(bSendSms) {				
					//生成短信验证码
					Integer code = new Integer(0);
					while(code < 100000 || code > 999999) {
						double number = Math.random() * 1000000;
						code = new Integer((int) number);
					}
					
					String str = "注册验证码为: " + code;
					Sms sms = new Sms();
					sms.setMobile(mobile);
					sms.setContent(str);
					sms.setStatus(0);
					//写入数据库
					smsService.addSms(sms);
					
					//写缓存
					RedisUtils.set("seller_"+mobile, 60, code.toString());
					
					resultMap.put("msg","success");
				}
			}
			else {
				resultMap.put("msg", "error");
				resultMap.put("reason", "手机号不合法！");
			}

		} catch (Exception e) {
			LOG.error("LoginController.getSignSMSCode===", e);
		}
		return resultMap;
	}
	
	@RequestMapping(value="manager", method={ RequestMethod.GET, RequestMethod.POST })
	public String manager(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		
		return "manager";
	}
	
}
