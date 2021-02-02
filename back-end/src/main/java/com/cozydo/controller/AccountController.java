package com.cozydo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cozydo.dao.UserDao;
import com.cozydo.dao.PlaceDao;
import com.cozydo.model.BasicResponse;
import com.cozydo.model.place.Place;
import com.cozydo.model.user.AuthenticationRequest;
import com.cozydo.model.user.SignupRequest;
import com.cozydo.model.user.User;
import com.web.corona.crowling.Excel;

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
public class AccountController {

//	@Autowired
//	UserDao userDao;
//	final PlaceDao placeDao;
//	
//	@GetMapping("/account/login")
//
//	@ApiOperation(value = "로그인")
//	public Object login(@RequestParam(required = true) final String email,
//			@RequestParam(required = true) final String password) {
//
//		Optional<User> userOpt = userDao.findUserByEmailAndPassword(email, password);
//
//		ResponseEntity response = null;
//
//		if (userOpt.isPresent()) {
//			System.out.println("야옹이");
//			final BasicResponse result = new BasicResponse();
//			result.status = true;
//			result.data = "success";
//			response = new ResponseEntity<>(result, HttpStatus.OK);
//		} else {
//			System.out.println("냐옹이");
//			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//		}
//
//		return response;
//	}
//
//	@GetMapping("/logout")
//	public Object logout(HttpSession session) {
//		ResponseEntity response = null;
//		session.invalidate(); // 추후 jwt 공부 꼭! 필요
//
//		final BasicResponse result = new BasicResponse();
//		result.status = true;
//		result.data = "success";
//		response = new ResponseEntity<>(result, HttpStatus.OK);
//		return response;
//	}
//
//	@PostMapping("/account/signup")
//	@ApiOperation(value = "가입하기")
//	public Object signup(@Valid @RequestBody SignupRequest request) { // @valid 애너테이션 통해서 비밀번호 정규식이 먹히는거같아요..(킹리적갓심)
//		// 그리고 비밀번호가 정규식에 맞지 않게 오게되면 400에러가 납니다(bad request)
//		System.out.println(request.getEmail());
//		System.out.println(request.getNickname());
//		System.out.println(request.getPassword());
//		ResponseEntity response = null;
//		final BasicResponse result = new BasicResponse();
//
//		if (userDao.getUserByEmail(request.getEmail()) != null) { // userDao 에서 이메일 통해 찾아낸 user가 있으면 중복된거
//			result.status = false;
//			result.data = "이메일이 중복됩니다."; // 중복되었다고 해서 보냅니다
//			response = new ResponseEntity<>(result, HttpStatus.OK);
//		} else if (userDao.getOne(request.getNickname()) != null) { // userDao 에서 닉네임통해 찾아낸 user가 있으면 중복된 거 !
//			result.status = false;
//			result.data = "닉네임이 중복됩니다.";
//			response = new ResponseEntity<>(result, HttpStatus.OK);
//		} else { // 그게 아니면 가입 가능
//			User user = new User();
//			user.setUid(request.getNickname());
//			user.setPassword(request.getPassword());
//			user.setEmail(request.getEmail()); // user 에다가 다 셋팅해서
//			
//			userDao.save(user); // dao 통해 저장해줍니다
//
////    		return new ResponseEntity<>(result, HttpStatus.OK);
//			// 명세서에서 보면 이메일 인증으로 바로 보내라고 해서, 바로 여기 이메일 인증하는 함수로 리턴시켰습니다.(105줄)
//			return AuthEmail(
//					new AuthenticationRequest(request.getEmail(), request.getPassword(), request.getNickname()));
//		}
//
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
//
//	public Object AuthEmail(AuthenticationRequest request) {
//		/// email 연동 로직이 필요한 부분, 여기서 이메일 서버와 통신해서 실질적인 이메일을 보내야 할거같은데 여긴 안했습니다. 여기서 인증이
//		/// 잘 되었다고 치면 아래의 4줄을 써서 보내주면 될거같습니다.
//		final BasicResponse result = new BasicResponse();
//		result.status = true;
//		result.data = "인증 메일이 발송되었습니다. 이메일을 확인해주세요.";
//
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
//
//	@DeleteMapping("/user/{nickname}") // /user/{nickname}으로 delete로 오면 삭제하기 , 이 부분은 프론트 분들과 항상 합의하에 작성해야 할것
//	public Object deleteUser(@PathVariable("nickname") String nickname) {
//		User user = userDao.getUserByUid(nickname); // user에다가 닉네임을 통해서 유저 정보를 다 받아오기
//		System.out.println(user.toString());
//
//		userDao.delete(user); // 삭제
//
//		final BasicResponse result = new BasicResponse();
//		result.status = true;
//		result.data = "회원 탈퇴 완료되었습니다.";
//
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
//
//	@PutMapping("/user/{nickname}") // 닉네임을 통해 정보를 받고, 비밀번호를 바꾼다고 가정
//	public Object updateUser(@PathVariable("nickname") String nickname, String newPassword) { // 여기 user는 바꿀 정보들이 담겨있는 곳
//		final BasicResponse result = new BasicResponse();
//
//		User newUser = userDao.getUserByUid(nickname);
//		newUser.setPassword(newPassword);
//
//		userDao.save(newUser); // user가 존재할 시, 정보를 변경하고 나서 userDao 의 save를 사용하여 update한다.
//
//		result.status = true;
//		result.data = "비밀번호가 잘 수정되었습니다.";
//
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
}