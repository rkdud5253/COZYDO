
package com.cozydo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozydo.model.place.Place;

public interface PlaceDao extends JpaRepository<Place, Long> {

	List<Place> findByPlaceLatAndPlaceLon(String lat, String lon);

	Place findByPlaceIdx(int placeIdx);
	
}
