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

import com.cozydo.corona.crawling.Kakao;
import com.cozydo.dao.CoronaLevelDao;
import com.cozydo.mail.EmailUtil;
import com.cozydo.model.BasicResponse;
import com.cozydo.model.crawling.Coronalevel;
import com.cozydo.service.CoronaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class CrawlingController {

	@Autowired
	CoronaService CoronaLevelService;

	@GetMapping("/crawling/level")
	@ApiOperation(httpMethod = "GET", value = "크롤링 후 코로나단계 db에 저장", notes = "insert coronalevel")
	public Object SaveCrawling() throws IOException, ParseException {
		return CoronaLevelService.SaveAll();
	}

	@GetMapping("/crawling/location")
	@ApiOperation(httpMethod = "GET", value = "위도경도를 받아서 지역과 지역단계 뿌려주기", notes = "select city and level")
	public Object Location(@RequestParam(required = true) final String lat,
			@RequestParam(required = true) final String lon) throws IOException {
		return CoronaLevelService.Location(lat, lon);
	}

	@GetMapping("/crawling/confirmed")
	@ApiOperation(httpMethod = "GET", value = "당일,주별,시도별 코로나 확진자 수 전달", notes = "파라미터x, 주별 누적 수 + 당일 확진자 수  Object반환")
	public Object weekconfirmed() throws IOException {
		return CoronaLevelService.Confirmed();
	}
}