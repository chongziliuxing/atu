package com.atu.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atu.erp.domain.Sms;
import com.atu.erp.domain.query.SmsQuery;
import com.atu.erp.manager.SmsManager;
import com.atu.erp.service.SmsService;

@Service(value = "smsService")
public class SmsServiceImpl implements SmsService{
	@Autowired
	private SmsManager smsManager;

	@Override
	public List<Sms> querySms(SmsQuery smsQuery) {
		return smsManager.querySms(smsQuery);
	}

	@Override
	public Sms queryLastSms(SmsQuery smsQuery) {
		Sms sms = smsManager.queryLastSms(smsQuery);
		return sms;
	}
	
	@Override
	public Integer addSms(Sms user) {
		// TODO Auto-generated method stub
		return smsManager.addSms(user);
		
	}

	@Override
	public void updateSms(Sms user) {
		// TODO Auto-generated method stub
		smsManager.updateSms(user);
		
	}

	

}
