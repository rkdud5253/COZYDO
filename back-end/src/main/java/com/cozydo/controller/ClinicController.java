package com.cozydo.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cozydo.dao.ClinicDao;
import com.cozydo.model.BasicResponse;
import com.cozydo.model.place.Clinic;
import com.cozydo.model.place.ClinicDto;
import com.cozydo.service.ClinicDaompl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class ClinicController {

	@Autowired
	private ClinicDao ClinicDao;

	@GetMapping("/clinic/list")
	@ApiOperation(httpMethod = "GET", value = "경도위도를 받아 거리순으로  Clinic 리스트 조회", notes = "Select Clinic list")
	public Object getClinic(@RequestParam(required = true) final String lat, @RequestParam(required = true) final String lon) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		List<ClinicDto> clinic = ClinicDao.GetDistanceByLatandLon(lat, lon);
		if (clinic.size() <= 0) {
			result.status = false;
			result.data = "주변에 진료소가 존재하지 않습니다.";
		} else {
			result.status = true;
			result.object = clinic;
			result.data = "success";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@GetMapping("/clinic/detail")
	@ApiOperation(httpMethod = "GET", value = "Clinic 상세 조회", notes = "Select Clinic list")
	public Object getClinicdetail(@RequestParam(required = true) int idx) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();

		Clinic clinic = ClinicDao.findClinicByClinicIdx(idx);

		if (clinic.getClinicIdx() == 0) {
			result.status = false;
			result.data = "잘못된 Index정보 입니다.";
		} else {
			result.status = true;
			result.data = "success";
			result.object = clinic;
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
}
