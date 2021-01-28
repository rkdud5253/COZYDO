package com.cozydo.controller;

import java.util.List;

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
	ClinicDao clinicdao;

	@GetMapping("/clinic/list")
	@ApiOperation(httpMethod = "GET", value = "경도위도를 받아 구기준으로 먼저 Clinic 리스트 조회", notes = "Select Clinic list")
	public Object getClinic() {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
//		List<Clinic> list = clinicdao.findAll();
		String a = "강남구";
//		List<Clinic> list = clinicdao.findClinicOrderByGuDesc(a);
		result.status = true;
//		result.object = list;
		result.data = "success";
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@GetMapping("/clinic/detail")
	@ApiOperation(httpMethod = "GET", value = "Clinic 상세 조회", notes = "Select Clinic list")
	public Object getClinicdetail(@RequestParam(required = true) int idx) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		Clinic clinic = new Clinic();
		if (clinicdao.findClinicByClinicIdx(idx) == null) {
			result.status = false;
			result.data = "잘못된 정보입니다.";
		} else {
			result.status = true;
			result.object = clinic;
			result.data = "success";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
}
