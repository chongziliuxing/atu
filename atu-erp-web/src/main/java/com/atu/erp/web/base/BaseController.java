package com.atu.erp.web.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import com.atu.erp.common.utils.JsonUtils;
import com.atu.erp.service.result.Result;

public class BaseController {
	
	@InitBinder
	public void initDateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	protected Result httpGetDataResult(String path, String data){
		Result Result = new Result();
		String datas = BaseValues.httpGetData(path, data);
		Result = JsonUtils.readValue(datas, Result.class);
		return Result;
	}
	protected void setExceptionResult(Result Result){
		Result.setSuccess(false);
		Result.setResultCode("500");
		Result.setResultMessage("服务异常，请稍后重试");
	}
	
	protected void setSuccessResult(Result Result){
		Result.setSuccess(true);
		Result.setResultCode("200");
		Result.setResultMessage("");
	}
	

    @ExceptionHandler({Exception.class}) 
    public String exception(HttpServletRequest reuqest, Exception e){
    	System.out.println(reuqest.getRequestURI()+"出现异常====>>"+e.getMessage());
        e.printStackTrace();
        return "exception";
    }
	
}