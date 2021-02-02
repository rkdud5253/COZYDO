package com.cozydo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cozydo.mapper.PlaceMapper;
import com.cozydo.model.place.PlaceDto;

@Service
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	PlaceMapper placeMapper;
	@Override
	public List<PlaceDto> findByLatAndLon(String keyword, String lat, String lon) {
		return placeMapper.findByLatAndLon(keyword, lat, lon);
	}

	@Override
	public PlaceDto findByIdx(int level, int placeIdx) {
		return placeMapper.findByIdx(level, placeIdx);
	}

}
