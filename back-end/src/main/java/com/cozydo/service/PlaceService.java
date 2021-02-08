package com.cozydo.service;

import java.util.List;

import com.cozydo.model.place.PlaceDto;

public interface PlaceService {

	List<PlaceDto> findByLatAndLon(String keyword, String level, String lat, String lon);

	PlaceDto findByIdx(String level, int placeIdx, int userIdx);

}
