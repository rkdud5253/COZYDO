package com.cozydo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cozydo.model.place.PlaceDto;

@Mapper
public interface PlaceMapper {

	List<PlaceDto> findByLatAndLon(@Param("keyword") String keyword, @Param("lat") String lat, @Param("lon") String lon);

	PlaceDto findByIdx(int placeIdx);
}
