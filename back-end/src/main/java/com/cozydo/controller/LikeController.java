package com.cozydo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cozydo.model.BasicResponse;
import com.cozydo.model.likes.Likes;
import com.cozydo.service.LikesService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController()
public class LikeController {

	@Autowired
	LikesService service;

	@PostMapping(value = "/likes/save", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(httpMethod = "POST", 
				value = "likes 추가", 
				notes = "Save likes", 
				response = Likes.class, 
				responseContainer = "ArrayList")
    public ResponseEntity<Long> save(@RequestBody Likes likes) {
 
        Long savedLikesSeq = service.save(likes);
        return new ResponseEntity<Long>(savedLikesSeq, HttpStatus.CREATED);
    }
	
	@DeleteMapping(value = "/likes/delete", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(httpMethod = "DELETE", 
			value = "likes 삭제", 
			notes = "Delete likes", 
			response = Long.class)
	/** 게시글 - 삭제 */
    public ResponseEntity<Long> delete(@RequestParam("userIdx") int userIdx, @RequestParam("placeIdx") int placeIdx) {
 
    	service.delete(userIdx, placeIdx);
        return new ResponseEntity<Long>((long) placeIdx, HttpStatus.NO_CONTENT);
    }
}
