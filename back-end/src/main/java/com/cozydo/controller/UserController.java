package com.cozydo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cozydo.model.BasicResponse;

import com.cozydo.model.user.SignupRequest;

import com.cozydo.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/user/signup")
	@ApiOperation(httpMethod = "POST", value = "email,password,name,nickname을 받아 회원 가입", notes = "insert user information")
	public Object Signup(@Valid @RequestBody SignupRequest request, BindingResult bindingResult) {

		return userService.Signup(request, bindingResult);
	}

	@PostMapping("/user/login")
	@ApiOperation(httpMethod = "POST", value = "email,password을 받아 로그인 성공여부 판단", notes = "insert user information")
	public Object Login(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String password) {

		return userService.Login(email, password);
	}

	@PutMapping("/user/update")
	@ApiOperation(httpMethod = "POST", value = "회원정보를 받아 회원정보를 수정", notes = "update user information")
	public Object Update(@Valid @RequestBody SignupRequest request, BindingResult bindingResult) {

		return userService.Update(request, bindingResult);
	}

	@DeleteMapping("/user/delete")
	@ApiOperation(httpMethod = "GET", value = "회원 아이디와 비밀번호를 받아 회원 삭제", notes = "delete user")
	public Object Delete(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String password) {

		return userService.Delete(email, password);
	}
}