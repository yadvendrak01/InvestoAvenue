package com.infy.dao;

import com.infy.model.Assets;

public interface Algorithm2DAO {
	
public Assets individualAssets(String userId) throws Exception;

public Integer individualIncome(String userId) throws Exception;
}
