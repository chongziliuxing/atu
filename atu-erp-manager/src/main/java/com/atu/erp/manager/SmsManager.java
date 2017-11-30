package com.atu.erp.manager;

import java.util.Map;
import java.util.List;

import com.atu.erp.domain.Sms;
import com.atu.erp.domain.query.SmsQuery;

public interface SmsManager {

	List<Sms> querySms(SmsQuery smsQuery);
	
	Sms queryLastSms(SmsQuery smsQuery);

	Integer addSms(Sms user);

	void updateSms(Sms user);

}
