
package com.cozydo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozydo.model.place.Place;

public interface PlaceDao extends JpaRepository<Place, Long> {
	
}
