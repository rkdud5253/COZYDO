
package com.cozydo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cozydo.model.place.Coronalevel;

@Repository
public interface CrawlingDao extends JpaRepository<Coronalevel, String> {
	Coronalevel getCoronalevelBycity(String city);
}
