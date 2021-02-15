package com.cozydo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cozydo.model.BasicResponse;
import com.cozydo.model.place.Place;
import com.cozydo.model.place.PlaceDto;
import com.cozydo.model.place.PlaceResponseDto;
import com.cozydo.service.PlaceService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class MapController {

	@Autowired
	PlaceService service;

	@GetMapping("/map/list")
	@ApiOperation(httpMethod = "GET", value = "Place 리스트 조회", notes = "Select Place List", response = Place.class, responseContainer = "ArrayList")
	public List<PlaceResponseDto> getPlaceListUsingMybatis(@RequestParam(required = true) final String keyword,
			@RequestParam(required = true) final String level, @RequestParam(required = true) final String lat,
			@RequestParam(required = true) final String lon) {

		List<PlaceResponseDto> response = null;

		response = service.findByLatAndLon(keyword, level, lat, lon);

		return response;
	}

	@GetMapping("/map/detail")
	@ApiOperation(httpMethod = "GET", value = "Place 상세 조회", notes = "Select Place detail")

	public PlaceDto getPlace(@RequestParam(required = true) final String level,
			@RequestParam(required = true) final int placeIdx, @RequestParam(required = true) final int userIdx) {

		PlaceDto response = service.findByIdx(level, placeIdx, userIdx);

		return response;
	}
}
