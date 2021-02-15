package com.cozydo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cozydo.mapper.PlaceMapper;
import com.cozydo.model.place.PlaceDto;
import com.cozydo.model.place.PlaceResponseDto;

@Service
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	PlaceMapper placeMapper;

	@Override
	public List<PlaceResponseDto> findByLatAndLon(String keyword, String level, String lat, String lon) {
		List<PlaceResponseDto> response = new ArrayList<PlaceResponseDto>();
		if(keyword.equals("체육시설"))
			keyword = "스포츠";
		List<PlaceDto> placeList = placeMapper.findByLatAndLon(keyword, level, lat, lon);
		for(PlaceDto list : placeList) {
			String rating = placeMapper.findRatingByIdx(list.getPlaceIdx());
			if(rating == null) rating = "0";
			response.add(new PlaceResponseDto(list, rating));
			
		}
		return response;
	}

	@Override
	public PlaceDto findByIdx(String level, int placeIdx, int userIdx) {
		PlaceDto placeDto = placeMapper.findByIdx(level, placeIdx);
		placeDto.setIsLike(placeMapper.getLikes(userIdx, placeIdx));
		placeDto.setReviewList(placeMapper.getReview(placeIdx));
		return placeDto;
	}

}
