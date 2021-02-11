package com.cozydo.dao;

import java.util.ArrayList;
import java.util.List;

import com.cozydo.model.crawling.CoronaInfomation;

public interface CoronaApiDao {
	List<ArrayList<String[]>> Classification();
}
