package com.atu.erp.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atu.erp.dao.SmsDao;
import com.atu.erp.domain.Sms;
import com.atu.erp.domain.query.SmsQuery;
import com.atu.erp.manager.SmsManager;


@Repository
public class SmsManagerImpl implements SmsManager{
	
	@Autowired
	private SmsDao smsDao;

	@Override
	public List<Sms> querySms(SmsQuery smsQuery) {
		// TODO Auto-generated method stub
		return smsDao.selectByCondition(smsQuery);
	}
	
	@Override
	public Sms queryLastSms(SmsQuery smsQuery) {
		// TODO Auto-generated method stub
		return smsDao.selectLastRecordByCondition(smsQuery);
	}

	public SmsDao getSmsDAO() {
		return smsDao;
	}

	public void setSmsDao(SmsDao smsDao) {
		this.smsDao = smsDao;
	}

	@Override
	public Integer addSms(Sms sms) {
		// TODO Auto-generated method stub
		return smsDao.addSms(sms);
		
	}

	@Override
	public void updateSms(Sms sms) {
		smsDao.updateSms(sms);
		
	}

	
}
