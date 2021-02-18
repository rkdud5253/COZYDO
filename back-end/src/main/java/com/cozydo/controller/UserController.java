package com.cozydo.controller;

import java.net.URLDecoder;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cozydo.jwt.JwtTokenProvider;
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

	@PostMapping(value = "/user/signup", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(httpMethod = "POST", value = "email,password,name,nickname을 받아 회원 가입", notes = "insert user information")
	public Object Signup(@Valid @RequestBody SignupRequest request, BindingResult bindingResult) {

		return userService.Signup(request, bindingResult);
	}

	@PostMapping("/user/login")
	@ApiOperation(httpMethod = "POST", value = "email,password을 받아 token과 idx,nickname,email을 반환", notes = "insert user information")
	public Object Login(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String password) {
		return userService.Login(email, password);
	}

	@PutMapping(value = "/user/update", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(httpMethod = "PUT", value = "회원정보를 받아 회원정보를 수정", notes = "update user information")
	public Object Update(@Valid @RequestBody SignupRequest request, BindingResult bindingResult) {

		return userService.Update(request, bindingResult);
	}

	@DeleteMapping("/user/delete")
	@ApiOperation(httpMethod = "DELETE", value = "회원 아이디를 받아 회원 삭제", notes = "delete user")
	public Object Delete(@RequestParam(required = true) final String email) {
		return userService.Delete(email);
	}

	@GetMapping("/user/findpw")
	@ApiOperation(httpMethod = "GET", value = "email과 name을 받아 임시비밀번호 이메일로 전송", notes = "비밀번호 이메일로 전송")
	public Object FindPassword(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String name) {
		return userService.FindPW(email, name);
	}

	@GetMapping("/user/authentication")
	@ApiOperation(httpMethod = "GET", value = "email과 email인증키를 받아 인증 요청", notes = "이메일인증 번호와 이메일을 받아 승인 요청")
	public Object Authentication(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String authkey) {
		return userService.FindAuthkey(email, authkey);
	}
}