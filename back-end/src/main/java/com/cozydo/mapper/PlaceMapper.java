package com.cozydo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cozydo.model.place.PlaceDto;
import com.cozydo.model.review.ReviewResponseDto;

@Mapper
public interface PlaceMapper {

	List<PlaceDto> findByLatAndLon(@Param("keyword") String keyword, @Param("level") String level, @Param("lat") String lat, @Param("lon") String lon);

	PlaceDto findByIdx(@Param("level") String level, @Param("placeIdx") int placeIdx);
	
	int getLikes(@Param("userIdx") int userIdx, @Param("placeIdx") int placeIdx);

	List<ReviewResponseDto> getReview(int placeIdx);
	
	String findRatingByIdx(int placeIdx);

}
