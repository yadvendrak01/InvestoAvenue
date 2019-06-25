package com.infy.dao;

import com.infy.model.Clients;


public interface AlgorithmDAO {
public Integer getNetAssets(Clients client) throws Exception;
public Integer getAnnualSavings(Clients client) throws Exception;
public Integer[] getRetireSavings(Clients clients) throws Exception;
public Integer[] getNetProfit(String userId) throws Exception;
}
