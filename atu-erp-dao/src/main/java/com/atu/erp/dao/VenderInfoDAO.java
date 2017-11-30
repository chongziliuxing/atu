package com.atu.erp.dao;

import com.atu.erp.domain.BusinessUserExt;

import java.sql.SQLException;
import java.util.List;

public interface VenderInfoDAO {

	void addVender(BusinessUserExt venderInfo);
}