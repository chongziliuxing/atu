package com.atu.erp.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.atu.erp.dao.VenderInfoDAO;
import com.atu.erp.domain.BusinessUserExt;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class VenderInfoDAOImpl extends SqlMapClientTemplate implements VenderInfoDAO {

	@Override
	public void addVender(BusinessUserExt venderInfo) {
		super.insert("vender_info.addVender", venderInfo);
		
	}

	
}