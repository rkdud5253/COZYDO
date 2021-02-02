package com.cozydo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cozydo.model.BasicResponse;
import com.cozydo.model.place.Review;
import com.cozydo.service.ReviewService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController()
public class ReviewController {

	@Autowired
	ReviewService service;

	@PostMapping(value = "/review/save", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(httpMethod = "POST", 
				value = "Review 작성 조회", 
				notes = "Save review", 
				response = Review.class, 
				responseContainer = "ArrayList")
    public ResponseEntity<Long> save(@RequestBody Review review) {
 
        Long savedReviewSeq = service.save(review);
 
        return new ResponseEntity<Long>(savedReviewSeq, HttpStatus.CREATED);
    }
	
	@DeleteMapping(value = "/review/delete/{reviewIdx}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(httpMethod = "DELETE", 
			value = "Review 삭제", 
			notes = "Delete review", 
			response = Long.class, 
			responseContainer = "ArrayList")
	/** 게시글 - 삭제 */
    public ResponseEntity<Long> delete(@PathVariable("reviewIdx") Long reviewIdx) {
 
    	service.delete(reviewIdx);
 
        return new ResponseEntity<Long>(reviewIdx, HttpStatus.NO_CONTENT);
    }
}
