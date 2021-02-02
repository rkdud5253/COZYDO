package com.cozydo.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cozydo.dao.CronaLevelDao;
import com.cozydo.model.BasicResponse;
import com.cozydo.model.crawling.Coronalevel;
import com.web.corona.crowling.CoronaCrawling;
import com.web.corona.crowling.Kakao;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RequiredArgsConstructor
@RestController
public class CrawlingController {

	@Autowired
	private CronaLevelDao coronalevelDao;

	@GetMapping("/crawling/level")
	@ApiOperation(httpMethod = "GET", value = "크롤링 후 코로나단계 db에 저장", notes = "insert coronalevel")
	public Object savecrawling() throws IOException, ParseException {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		List<Coronalevel> list = new CoronaCrawling().SaveAll();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		if (list.size() > 0) {
			coronalevelDao.deleteAll();
			for (Coronalevel level : list) {
				coronalevelDao.save(level);
			}
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		} else {
			result.status = false;
			result.data = "크롤링 실패";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}
	}

	@GetMapping("/crawling/location")
	@ApiOperation(httpMethod = "GET", value = "위도경도를 받아서 지역과 지역단계 뿌려주기", notes = "select city and level")
	public Object location(@RequestParam(required = true) final String lat,
			@RequestParam(required = true) final String lon) throws IOException {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		Kakao kakao = new Kakao(lon, lat);
		String city = kakao.getSi();

		if (city != null) {// 성공일 경우
			if (city.contains("제주"))
				city = "제주";
			Coronalevel level = coronalevelDao.getCoronalevelByCity(city);
			result.status = true;
			result.data = "success";
			result.object = level;
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		} else {
			result.status = false;
			result.data = "false";
			result.object = "위치가 잘못되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}
	}
}