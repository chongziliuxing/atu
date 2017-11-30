package com.atu.erp.web.base;

import com.atu.erp.common.utils.HttpUtils;

public class BaseValues {
//	private static String domain = "http://localhost:80/qx-html-web";
	private static String domain = "http://www.atu360.com";
	private static String charSet = "utf-8";
	
	
	public static String httpGetData(String path, String data){
		return HttpUtils.httpPostData(domain+path, data, charSet);
	}
}
