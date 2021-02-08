package com.cozydo.dao;

import java.util.List;

import com.cozydo.model.crawling.CoronaInfomation;

public interface CoronaApiDao {
	List<String[]> Classification(String Classification);
}
