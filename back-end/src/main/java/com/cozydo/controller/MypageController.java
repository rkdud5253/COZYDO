package com.cozydo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cozydo.model.BasicResponse;
import com.cozydo.model.likes.MyLikesResponseDto;
import com.cozydo.model.review.MyReviewResponseDto;
import com.cozydo.service.MypageService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController()
public class MypageController {

	@Autowired
	MypageService service;

	@GetMapping(value = "/mypage/review/list", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(httpMethod = "GET", 
				value = "My review list 조회", 
				notes = "My review list 조회", 
				response = MyReviewResponseDto.class, 
				responseContainer = "ArrayList")
    public List<MyReviewResponseDto> getMyReivewList(@RequestParam(required = true) final int userIdx) {
 
        return service.getMyReivewList(userIdx);
    }
	
	@GetMapping(value = "/mypage/likes/list", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(httpMethod = "GET", 
				value = "My likes list 조회", 
				notes = "My likes list 조회", 
				response = MyLikesResponseDto.class, 
				responseContainer = "ArrayList")
	/** 게시글 - 삭제 */
    public List<MyLikesResponseDto> getMyLikesList(@RequestParam(required = true) final int userIdx) {
 ;
        return service.getMyLikesList(userIdx);
    }
}
