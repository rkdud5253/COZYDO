package com.cozydo.service;

import java.util.List;

import com.cozydo.model.place.PlaceDto;
import com.cozydo.model.place.PlaceResponseDto;

public interface PlaceService {

	List<PlaceResponseDto> findByLatAndLon(String keyword, String level, String lat, String lon);

	PlaceDto findByIdx(String level, int placeIdx, int userIdx);

}
