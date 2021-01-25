package com.cozydo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cozydo.dao.PlaceDao;
import com.cozydo.model.BasicResponse;
import com.cozydo.model.place.Place;

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
    PlaceDao placeDao;

    @GetMapping("/map/list")
    
    @ApiOperation(httpMethod = "GET"
	,value = "Place 리스트 조회"
	,notes = "Select Place List"
	,response = Place.class
	,responseContainer = "ArrayList")
    public List<Place> getPlaceList(@RequestParam(required = true) final String lat,
            @RequestParam(required = true) final String lon) {

    	List<Place> response = null;
    	response = placeDao.findByPlaceLonAndPlaceLat(lon, lat);
    	
        return response;
    }

    @GetMapping("/map/detail")
    @ApiOperation(httpMethod = "GET"
	,value = "Place 상세 조회"
	,notes = "Select Place detail")
    public Place getPlace(@RequestParam(required = true) final int placeIdx) {
    	
    	Place response = placeDao.findByPlaceIdx(placeIdx);

    	return response;
    }
    
}
