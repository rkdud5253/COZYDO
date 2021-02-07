package com.cozydo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozydo.model.crawling.Coronalevel;

public interface CoronaLevelDao extends JpaRepository<Coronalevel, String> {
	Coronalevel getCoronalevelByCity(String city);
}
