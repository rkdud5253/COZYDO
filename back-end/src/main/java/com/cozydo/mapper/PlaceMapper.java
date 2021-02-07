package com.cozydo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cozydo.model.place.PlaceDto;
import com.cozydo.model.review.Review;

@Mapper
public interface PlaceMapper {

	List<PlaceDto> findByLatAndLon(@Param("keyword") String keyword, @Param("level") String level, @Param("lat") String lat, @Param("lon") String lon);

	PlaceDto findByIdx(@Param("level") String level, @Param("placeIdx") int placeIdx);

	List<Review> getReview(int placeIdx);

}
