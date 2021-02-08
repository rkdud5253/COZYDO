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
	public List<PlaceDto> findByLatAndLon(String keyword, String level, String lat, String lon) {
		return placeMapper.findByLatAndLon(keyword, level, lat, lon);
	}

	@Override
	public PlaceDto findByIdx(String level, int placeIdx, int userIdx) {
		PlaceDto placeDto = placeMapper.findByIdx(level, placeIdx);
		placeDto.setIsLike(placeMapper.getLikes(userIdx, placeIdx));
		placeDto.setReviewList(placeMapper.getReview(placeIdx));
		return placeDto;
	}

}
